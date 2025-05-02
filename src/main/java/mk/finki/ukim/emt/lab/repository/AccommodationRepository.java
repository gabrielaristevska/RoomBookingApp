package mk.finki.ukim.emt.lab.repository;

import mk.finki.ukim.emt.lab.model.domain.Accommodation;
import mk.finki.ukim.emt.lab.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
    Optional<Accommodation> findByCategoryAndIdNot(Category category, Long id);
}
