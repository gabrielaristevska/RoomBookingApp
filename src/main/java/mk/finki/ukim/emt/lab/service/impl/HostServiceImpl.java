package mk.finki.ukim.emt.lab.service.impl;

import mk.finki.ukim.emt.lab.model.Host;
import mk.finki.ukim.emt.lab.model.dto.HostDto;
import mk.finki.ukim.emt.lab.repository.HostRepository;
import mk.finki.ukim.emt.lab.service.CountryService;
import mk.finki.ukim.emt.lab.service.HostService;
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
    public Optional<Host> update(Long id, HostDto host) {
        return hostRepository.findById(id)
                .map(existingProduct -> {
                    if (host.getName() != null) {
                        existingProduct.setName(host.getName());
                    }
                    if (host.getSurname() != null) {
                        existingProduct.setSurname(host.getSurname());
                    }
                    if (host.getCountryId() != null && countryService.findById(host.getCountryId()).isPresent()) {
                        existingProduct.setCountry(countryService.findById(host.getCountryId()).get());
                    }
                    return hostRepository.save(existingProduct);
                });
    }

    @Override
    public Optional<Host> save(HostDto host) {
        if (host.getCountryId() != null &&
                countryService.findById(host.getCountryId()).isPresent()) {
            return Optional.of(
                    hostRepository.save(new Host(host.getName(), host.getSurname(),
                            countryService.findById(host.getCountryId()).get())));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
