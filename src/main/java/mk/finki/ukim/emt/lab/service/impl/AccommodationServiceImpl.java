package mk.finki.ukim.emt.lab.service.impl;

import mk.finki.ukim.emt.lab.model.Accommodation;
import mk.finki.ukim.emt.lab.model.dto.AccommodationDto;
import mk.finki.ukim.emt.lab.repository.AccommodationRepository;
import mk.finki.ukim.emt.lab.service.AccommodationService;
import mk.finki.ukim.emt.lab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
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
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
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
                    if (accommodation.getHostId() != null && hostService.findById(accommodation.getHostId()).isPresent()) {
                        existingProduct.setHost(hostService.findById(accommodation.getHostId()).get());
                    }
                    return accommodationRepository.save(existingProduct);
                });

    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if (accommodation.getHostId() != null &&
                hostService.findById(accommodation.getHostId()).isPresent()) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            hostService.findById(accommodation.getHostId()).get(), accommodation.getNumRooms())));
        }
        return Optional.empty();

    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> rentById(Long id) {
        Accommodation accommodation=findById(id).orElseThrow();
        accommodation.setNumRooms(accommodation.getNumRooms()-1);
        return Optional.of(accommodationRepository.save(accommodation));
    }
}
