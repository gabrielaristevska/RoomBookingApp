package mk.finki.ukim.emt.lab.service.domain.impl;

import mk.finki.ukim.emt.lab.model.domain.Accommodation;
import mk.finki.ukim.emt.lab.model.views.AccommodationsByHostView;
import mk.finki.ukim.emt.lab.repository.AccommodationRepository;
import mk.finki.ukim.emt.lab.repository.AccommodationsByHostViewRepository;
import mk.finki.ukim.emt.lab.service.domain.AccommodationService;
import mk.finki.ukim.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final AccommodationsByHostViewRepository accommodationsByHostViewRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, AccommodationsByHostViewRepository accommodationsByHostViewRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.accommodationsByHostViewRepository = accommodationsByHostViewRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id)
                .map(existingProduct -> {
                    if (accommodation.getName() != null) {
                        existingProduct.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingProduct.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingProduct.setNumRooms(accommodation.getNumRooms());
                    }
                    if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
                        existingProduct.setHost(hostService.findById(accommodation.getHost().getId()).get());
                    }
                    return accommodationRepository.save(existingProduct);
                });

    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getHost() != null &&
                hostService.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            hostService.findById(accommodation.getHost().getId()).get(), accommodation.getNumRooms())));
        }
        return Optional.empty();

    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> rentById(Long id) {
        Accommodation accommodation = findById(id).orElseThrow();
        accommodation.setNumRooms(accommodation.getNumRooms() - 1);
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsByHostViewRepository.refreshMaterializedView();
    }

    @Override
    public List<AccommodationsByHostView> findAccommodationsByHost() {
        return accommodationsByHostViewRepository.findAll();
    }

}
