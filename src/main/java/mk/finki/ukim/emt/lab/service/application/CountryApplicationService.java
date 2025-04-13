package mk.finki.ukim.emt.lab.service.application;

import mk.finki.ukim.emt.lab.dto.CreateCountryDto;
import mk.finki.ukim.emt.lab.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto);

    void deleteById(Long id);

    Optional<DisplayCountryDto> save(CreateCountryDto countryDto);

}
