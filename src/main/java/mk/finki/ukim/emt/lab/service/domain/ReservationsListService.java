package mk.finki.ukim.emt.lab.service.domain;

import mk.finki.ukim.emt.lab.model.domain.Accommodation;
import mk.finki.ukim.emt.lab.model.domain.ReservationsList;

import java.util.List;
import java.util.Optional;

public interface ReservationsListService {
    List<Accommodation> listAllAccommodationsInReservationList(Long reservationsListId);

    Optional<ReservationsList> getActiveReservationsList(String username);

    Optional<ReservationsList> addAccommodationToReservationsList(String username, Long accommodationId);

    void confirmReservations(String username);
}
