package mk.finki.ukim.emt.lab.service;

import mk.finki.ukim.emt.lab.model.Host;
import mk.finki.ukim.emt.lab.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> update(Long id, HostDto host);
    Optional<Host> save(HostDto host);
    void deleteById(Long id);
}
