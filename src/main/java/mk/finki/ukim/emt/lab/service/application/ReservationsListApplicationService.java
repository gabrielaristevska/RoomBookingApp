package mk.finki.ukim.emt.lab.service.application;

import mk.finki.ukim.emt.lab.dto.DisplayAccommodationDto;
import mk.finki.ukim.emt.lab.dto.ReservationsListDto;
import mk.finki.ukim.emt.lab.model.domain.Accommodation;
import mk.finki.ukim.emt.lab.model.domain.ReservationsList;

import java.util.List;
import java.util.Optional;

public interface ReservationsListApplicationService {
    List<DisplayAccommodationDto> listAllAccommodationsInReservationList(Long reservationsListId);

    Optional<ReservationsListDto> getActiveReservationsList(String username);

    Optional<ReservationsListDto> addAccommodationToReservationsList(String username, Long accommodationId);

    void confirmReservations(String username);
}
