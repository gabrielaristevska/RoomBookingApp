package mk.finki.ukim.emt.lab.dto;

import mk.finki.ukim.emt.lab.model.domain.Accommodation;
import mk.finki.ukim.emt.lab.model.domain.Country;
import mk.finki.ukim.emt.lab.model.domain.Host;
import mk.finki.ukim.emt.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHostDto(String name, String surname, Long countryId) {
    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
}
