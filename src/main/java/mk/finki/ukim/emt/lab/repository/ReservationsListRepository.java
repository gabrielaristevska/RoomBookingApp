package mk.finki.ukim.emt.lab.repository;

import mk.finki.ukim.emt.lab.model.domain.ReservationsList;
import mk.finki.ukim.emt.lab.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationsListRepository extends JpaRepository<ReservationsList,Long> {
    Optional<ReservationsList> findByUser(User user);
}
