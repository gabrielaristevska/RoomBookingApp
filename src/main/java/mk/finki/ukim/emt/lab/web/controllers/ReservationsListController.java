package mk.finki.ukim.emt.lab.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.emt.lab.dto.ReservationsListDto;
import mk.finki.ukim.emt.lab.model.domain.User;
import mk.finki.ukim.emt.lab.model.exceptions.AccommodationNotAvailableException;
import mk.finki.ukim.emt.lab.model.exceptions.ReservationsListNotFoundException;
import mk.finki.ukim.emt.lab.service.application.ReservationsListApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations-list")
@Tag(name = "Reservations list API", description = "Endpoints for managing the list of reservations")
public class ReservationsListController {
    private final ReservationsListApplicationService reservationsListApplicationService;

    public ReservationsListController(ReservationsListApplicationService reservationsListApplicationService) {
        this.reservationsListApplicationService = reservationsListApplicationService;
    }

    @Operation(
            summary = "Get active reservations list",
            description = "Retrieves the active reservations list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "List retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "List not found")}
    )
    @GetMapping
    public ResponseEntity<ReservationsListDto> getActiveReservationsList(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return reservationsListApplicationService.getActiveReservationsList(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add an accommodation to list",
            description = "Adds an accommodation to the list of reservations for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Accommodation added to list successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Accommodation already in list or not available"
            ), @ApiResponse(responseCode = "404", description = "Accommodation not found")}
    )
    @PostMapping("/add/{id}")
    public ResponseEntity<ReservationsListDto> addAccommodationToList(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationsListApplicationService.addAccommodationToReservationsList(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Confirm reservations",
            description = "Confirms the reservations for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Confirmed successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request")}
    )
    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmReservations(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            reservationsListApplicationService.confirmReservations(user.getUsername());
            return ResponseEntity.ok().build();
        } catch (ReservationsListNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (AccommodationNotAvailableException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
