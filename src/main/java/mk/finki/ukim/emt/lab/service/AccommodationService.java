package mk.finki.ukim.emt.lab.service;

import mk.finki.ukim.emt.lab.model.Accommodation;
import mk.finki.ukim.emt.lab.model.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> update(Long id, AccommodationDto accommodation);
    Optional<Accommodation> save(AccommodationDto accommodation);
    void deleteById(Long id);
    Optional<Accommodation> rentById(Long id);
}
