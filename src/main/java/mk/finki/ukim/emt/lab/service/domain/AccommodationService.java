package mk.finki.ukim.emt.lab.service.domain;

import mk.finki.ukim.emt.lab.model.domain.Accommodation;
import mk.finki.ukim.emt.lab.model.views.AccommodationsByHostView;
import mk.finki.ukim.emt.lab.repository.AccommodationsByHostViewRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> save(Accommodation accommodation);

    void deleteById(Long id);

    Optional<Accommodation> rentById(Long id);

    void refreshMaterializedView();

    List<AccommodationsByHostView> findAccommodationsByHost();
}
