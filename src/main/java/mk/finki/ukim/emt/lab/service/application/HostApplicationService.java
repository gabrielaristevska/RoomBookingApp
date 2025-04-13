package mk.finki.ukim.emt.lab.service.application;

import mk.finki.ukim.emt.lab.dto.CreateHostDto;
import mk.finki.ukim.emt.lab.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto);

    Optional<DisplayHostDto> save(CreateHostDto hostDto);

    Optional<DisplayHostDto> findById(Long id);

    List<DisplayHostDto> findAll();

    void deleteById(Long id);

}
