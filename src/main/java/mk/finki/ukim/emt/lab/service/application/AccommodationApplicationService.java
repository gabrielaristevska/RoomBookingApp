package mk.finki.ukim.emt.lab.service.application;


import mk.finki.ukim.emt.lab.dto.CreateAccommodationDto;
import mk.finki.ukim.emt.lab.dto.DisplayAccommodationDto;
import mk.finki.ukim.emt.lab.model.views.AccommodationsByHostView;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto);

    void deleteById(Long id);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto);

    Optional<DisplayAccommodationDto> rentById(Long id);

    List<AccommodationsByHostView> findAccommodationsByHost();
}
