package mk.finki.ukim.emt.lab.service.impl;

import mk.finki.ukim.emt.lab.model.Country;
import mk.finki.ukim.emt.lab.model.dto.CountryDto;
import mk.finki.ukim.emt.lab.repository.CountryRepository;
import mk.finki.ukim.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> update(Long id, CountryDto country) {
        return countryRepository.findById(id)
                .map(existingProduct -> {
                    if (country.getName() != null) {
                        existingProduct.setName(country.getName());
                    }
                    if (country.getContinent() != null) {
                        existingProduct.setContinent(country.getContinent());
                    }
                    return countryRepository.save(existingProduct);
                });
    }

    @Override
    public Optional<Country> save(CountryDto country) {
        return Optional.of(
                countryRepository.save(new Country(country.getName(), country.getContinent())));
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
