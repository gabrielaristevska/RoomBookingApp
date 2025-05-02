package mk.finki.ukim.emt.lab.repository;

import mk.finki.ukim.emt.lab.model.views.AccommodationsByHostView;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationsByHostViewRepository extends JpaRepository<AccommodationsByHostView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodations_by_host", nativeQuery = true)
    void refreshMaterializedView();

    List<AccommodationsByHostView> findAll();
}
