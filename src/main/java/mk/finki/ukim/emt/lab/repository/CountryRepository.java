package mk.finki.ukim.emt.lab.repository;

import mk.finki.ukim.emt.lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
