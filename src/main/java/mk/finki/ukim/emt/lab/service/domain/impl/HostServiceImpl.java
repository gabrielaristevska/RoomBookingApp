package mk.finki.ukim.emt.lab.service.domain.impl;

import mk.finki.ukim.emt.lab.events.HostEvent;
import mk.finki.ukim.emt.lab.model.domain.Host;
import mk.finki.ukim.emt.lab.model.projections.HostProjection;
import mk.finki.ukim.emt.lab.model.views.HostsByCountryView;
import mk.finki.ukim.emt.lab.repository.HostRepository;
import mk.finki.ukim.emt.lab.repository.HostsByCountryViewRepository;
import mk.finki.ukim.emt.lab.service.domain.CountryService;
import mk.finki.ukim.emt.lab.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostsByCountryViewRepository hostsByCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostsByCountryViewRepository hostsByCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsByCountryViewRepository = hostsByCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id)
                .map(existingProduct -> {
                    if (host.getName() != null) {
                        existingProduct.setName(host.getName());
                    }
                    if (host.getSurname() != null) {
                        existingProduct.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
                        existingProduct.setCountry(countryService.findById(host.getCountry().getId()).get());
                    }
                    Host updatedHost = hostRepository.save(existingProduct);
                    this.applicationEventPublisher.publishEvent(new HostEvent(host));
                    return updatedHost;
                });
    }

    @Override
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();
        if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
            savedHost = Optional.of(
                    hostRepository.save(new Host(host.getName(), host.getSurname(),
                            countryService.findById(host.getCountry().getId()).get())));
            this.applicationEventPublisher.publishEvent(new HostEvent(host));
        }
        return savedHost;
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
        this.applicationEventPublisher.publishEvent(new HostEvent(findById(id).get()));
    }

    @Override
    public void refreshMaterializedView() {
        hostsByCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostsByCountryView> findHostsByCountry() {
        return hostsByCountryViewRepository.findAll();
    }

    @Override
    public List<HostProjection> getNamesAndSurnames() {
        return hostRepository.getNameAndSurnameProjection();
    }
}
