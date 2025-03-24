package mk.finki.ukim.emt.lab.service;

import mk.finki.ukim.emt.lab.model.Accommodation;
import mk.finki.ukim.emt.lab.model.Country;
import mk.finki.ukim.emt.lab.model.dto.AccommodationDto;
import mk.finki.ukim.emt.lab.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> update(Long id, CountryDto country);
    Optional<Country> save(CountryDto country);
    void deleteById(Long id);
}
