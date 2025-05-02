package mk.finki.ukim.emt.lab.repository;

import mk.finki.ukim.emt.lab.model.views.AccommodationsByHostView;
import mk.finki.ukim.emt.lab.model.views.HostsByCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HostsByCountryViewRepository extends JpaRepository<HostsByCountryView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.hosts_by_country", nativeQuery = true)
    void refreshMaterializedView();

    List<HostsByCountryView> findAll();
}
