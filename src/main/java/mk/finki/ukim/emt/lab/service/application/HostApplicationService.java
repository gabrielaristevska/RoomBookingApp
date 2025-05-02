package mk.finki.ukim.emt.lab.service.application;

import mk.finki.ukim.emt.lab.dto.CreateHostDto;
import mk.finki.ukim.emt.lab.dto.DisplayHostDto;
import mk.finki.ukim.emt.lab.model.projections.HostProjection;
import mk.finki.ukim.emt.lab.model.views.AccommodationsByHostView;
import mk.finki.ukim.emt.lab.model.views.HostsByCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto);

    Optional<DisplayHostDto> save(CreateHostDto hostDto);

    Optional<DisplayHostDto> findById(Long id);

    List<DisplayHostDto> findAll();

    void deleteById(Long id);

    List<HostsByCountryView> findHostsByCountry();

    List<HostProjection> getNamesAndSurnames();
}
