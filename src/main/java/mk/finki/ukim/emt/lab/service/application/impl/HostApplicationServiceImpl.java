package mk.finki.ukim.emt.lab.service.application.impl;

import mk.finki.ukim.emt.lab.dto.CreateHostDto;
import mk.finki.ukim.emt.lab.dto.DisplayHostDto;
import mk.finki.ukim.emt.lab.model.domain.Country;
import mk.finki.ukim.emt.lab.model.projections.HostProjection;
import mk.finki.ukim.emt.lab.model.views.HostsByCountryView;
import mk.finki.ukim.emt.lab.service.application.HostApplicationService;
import mk.finki.ukim.emt.lab.service.domain.CountryService;
import mk.finki.ukim.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto) {
        Optional<Country> country=countryService.findById(hostDto.countryId());
        return hostService.update(id, hostDto.toHost(country.orElse(null))).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto hostDto) {
        Optional<Country> country=countryService.findById(hostDto.countryId());

        if (country.isPresent()) {
            return hostService.save(hostDto.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();

    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public List<HostsByCountryView> findHostsByCountry() {
        return hostService.findHostsByCountry();
    }

    @Override
    public List<HostProjection> getNamesAndSurnames() {
        return hostService.getNamesAndSurnames();
    }
}
