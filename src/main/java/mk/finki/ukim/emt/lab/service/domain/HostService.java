package mk.finki.ukim.emt.lab.service.domain;

import mk.finki.ukim.emt.lab.model.domain.Host;
import mk.finki.ukim.emt.lab.model.projections.HostProjection;
import mk.finki.ukim.emt.lab.model.views.AccommodationsByHostView;
import mk.finki.ukim.emt.lab.model.views.HostsByCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> update(Long id, Host host);

    Optional<Host> save(Host host);

    void deleteById(Long id);

    void refreshMaterializedView();

    List<HostsByCountryView> findHostsByCountry();

    List<HostProjection> getNamesAndSurnames();
}
