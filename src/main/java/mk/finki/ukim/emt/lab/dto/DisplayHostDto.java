package mk.finki.ukim.emt.lab.dto;

import mk.finki.ukim.emt.lab.model.domain.Country;
import mk.finki.ukim.emt.lab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(Long id, String name, String surname, Long countryId) {

    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

}
