package mk.finki.ukim.emt.lab.repository;

import mk.finki.ukim.emt.lab.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
}
