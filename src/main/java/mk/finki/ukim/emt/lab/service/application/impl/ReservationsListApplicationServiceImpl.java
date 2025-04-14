package mk.finki.ukim.emt.lab.service.application.impl;

import mk.finki.ukim.emt.lab.dto.DisplayAccommodationDto;
import mk.finki.ukim.emt.lab.dto.ReservationsListDto;
import mk.finki.ukim.emt.lab.service.application.ReservationsListApplicationService;
import mk.finki.ukim.emt.lab.service.domain.ReservationsListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationsListApplicationServiceImpl implements ReservationsListApplicationService {
    private final ReservationsListService reservationsListService;

    public ReservationsListApplicationServiceImpl(ReservationsListService reservationsListService) {
        this.reservationsListService = reservationsListService;
    }

    @Override
    public List<DisplayAccommodationDto> listAllAccommodationsInReservationList(Long reservationsListId) {
        return DisplayAccommodationDto.from(reservationsListService.listAllAccommodationsInReservationList(reservationsListId));
    }

    @Override
    public Optional<ReservationsListDto> getActiveReservationsList(String username) {
        return reservationsListService.getActiveReservationsList(username).map(ReservationsListDto::from);
    }

    @Override
    public Optional<ReservationsListDto> addAccommodationToReservationsList(String username, Long accommodationId) {
        return reservationsListService.addAccommodationToReservationsList(username, accommodationId).map(ReservationsListDto::from);
    }

    @Override
    public void confirmReservations(String username) {
        reservationsListService.confirmReservations(username);
    }
}
