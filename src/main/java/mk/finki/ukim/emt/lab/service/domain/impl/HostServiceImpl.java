package mk.finki.ukim.emt.lab.service.domain.impl;

import mk.finki.ukim.emt.lab.model.domain.Host;
import mk.finki.ukim.emt.lab.model.dto.HostDto;
import mk.finki.ukim.emt.lab.repository.HostRepository;
import mk.finki.ukim.emt.lab.service.domain.CountryService;
import mk.finki.ukim.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
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
                    return hostRepository.save(existingProduct);
                });
    }

    @Override
    public Optional<Host> save(Host host) {
        if (host.getCountry() != null &&
                countryService.findById(host.getCountry().getId()).isPresent()) {
            return Optional.of(
                    hostRepository.save(new Host(host.getName(), host.getSurname(),
                            countryService.findById(host.getCountry().getId()).get())));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
