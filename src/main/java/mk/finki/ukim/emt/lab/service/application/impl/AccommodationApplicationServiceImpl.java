package mk.finki.ukim.emt.lab.service.application.impl;

import mk.finki.ukim.emt.lab.dto.CreateAccommodationDto;
import mk.finki.ukim.emt.lab.dto.DisplayAccommodationDto;
import mk.finki.ukim.emt.lab.model.domain.Host;
import mk.finki.ukim.emt.lab.service.application.AccommodationApplicationService;
import mk.finki.ukim.emt.lab.service.domain.AccommodationService;
import mk.finki.ukim.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto) {
        Optional<Host> host = hostService.findById(accommodationDto.hostId());

        return accommodationService.update(id,
                        accommodationDto.toAccommodation(
                                host.orElse(null)
                        )
                )
                .map(DisplayAccommodationDto::from);

    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto) {
        Optional<Host> host = hostService.findById(accommodationDto.hostId());

        if (host.isPresent()) {
            return accommodationService.save(accommodationDto.toAccommodation(host.get()))
                    .map(DisplayAccommodationDto::from);
        }
        return Optional.empty();

    }

    @Override
    public Optional<DisplayAccommodationDto> rentById(Long id) {
        return accommodationService.rentById(id).map(DisplayAccommodationDto::from);
    }
}
