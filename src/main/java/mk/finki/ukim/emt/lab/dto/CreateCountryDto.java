package mk.finki.ukim.emt.lab.dto;

import mk.finki.ukim.emt.lab.model.domain.Country;

public record CreateCountryDto(String name, String continent) {

    public Country toCountry(){
        return new Country(name,continent);
    }
}
