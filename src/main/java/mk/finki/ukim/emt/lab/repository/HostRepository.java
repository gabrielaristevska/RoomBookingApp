package mk.finki.ukim.emt.lab.repository;

import mk.finki.ukim.emt.lab.model.domain.Host;
import mk.finki.ukim.emt.lab.model.projections.HostProjection;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    @Query("select h.name AS name, h.surname AS surname from Host h")
    List<HostProjection> getNameAndSurnameProjection();
}
