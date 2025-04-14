package mk.finki.ukim.emt.lab.dto;

import mk.finki.ukim.emt.lab.model.domain.ReservationsList;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationsListDto(Long id, LocalDateTime dateCreated, DisplayUserDto userDto,
                                  List<DisplayAccommodationDto> accommodationDtoList) {

    public static ReservationsListDto from(ReservationsList reservationsList) {
        return new ReservationsListDto(
                reservationsList.getId(),
                reservationsList.getDateCreated(),
                DisplayUserDto.from(reservationsList.getUser()),
                DisplayAccommodationDto.from(reservationsList.getReservations())
        );
    }
}
