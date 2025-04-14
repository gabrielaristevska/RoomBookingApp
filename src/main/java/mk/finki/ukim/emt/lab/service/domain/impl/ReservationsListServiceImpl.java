package mk.finki.ukim.emt.lab.service.domain.impl;

import mk.finki.ukim.emt.lab.model.domain.Accommodation;
import mk.finki.ukim.emt.lab.model.domain.ReservationsList;
import mk.finki.ukim.emt.lab.model.domain.User;
import mk.finki.ukim.emt.lab.model.exceptions.AccommodationAlreadyInListException;
import mk.finki.ukim.emt.lab.model.exceptions.AccommodationNotAvailableException;
import mk.finki.ukim.emt.lab.model.exceptions.AccommodationNotFoundException;
import mk.finki.ukim.emt.lab.model.exceptions.ReservationsListNotFoundException;
import mk.finki.ukim.emt.lab.repository.ReservationsListRepository;
import mk.finki.ukim.emt.lab.service.domain.AccommodationService;
import mk.finki.ukim.emt.lab.service.domain.ReservationsListService;
import mk.finki.ukim.emt.lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationsListServiceImpl implements ReservationsListService {
    private final ReservationsListRepository reservationsListRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public ReservationsListServiceImpl(ReservationsListRepository reservationsListRepository, UserService userService, AccommodationService accommodationService) {
        this.reservationsListRepository = reservationsListRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Accommodation> listAllAccommodationsInReservationList(Long reservationsListId) {
        if (reservationsListRepository.findById(reservationsListId).isEmpty()) {
            throw new ReservationsListNotFoundException(reservationsListId);
        }
        return reservationsListRepository.findById(reservationsListId).get().getReservations();
    }

    @Override
    public Optional<ReservationsList> getActiveReservationsList(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(reservationsListRepository.findByUser(user)
                .orElseGet(() -> reservationsListRepository.save(new ReservationsList(user))));
    }

    @Override
    public Optional<ReservationsList> addAccommodationToReservationsList(String username, Long accommodationId) {
        if (getActiveReservationsList(username).isPresent()) {
            ReservationsList list = getActiveReservationsList(username).get();

            Accommodation accommodation = accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            if (!list.getReservations().stream().filter(i -> i.getId().equals(accommodationId)).toList().isEmpty())
                throw new AccommodationAlreadyInListException(username, accommodationId);
            if (accommodation.getNumRooms() <= 0) {
                throw new AccommodationNotAvailableException(accommodationId);
            }

            list.getReservations().add(accommodation);
            return Optional.of(reservationsListRepository.save(list));
        }
        return Optional.empty();
    }

    @Override
    public void confirmReservations(String username) {
        ReservationsList list = getActiveReservationsList(username)
                .orElseThrow(() -> new ReservationsListNotFoundException(username));

        for (Accommodation accommodation : list.getReservations()) {
            if (accommodation.getNumRooms() <= 0) {
                throw new AccommodationNotAvailableException(accommodation.getId());
            }
            accommodationService.rentById(accommodation.getId());
        }
        list.setReservations(new ArrayList<>());
        reservationsListRepository.save(list);
    }
}
