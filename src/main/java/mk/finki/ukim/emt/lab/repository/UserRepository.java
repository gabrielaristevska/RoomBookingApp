package mk.finki.ukim.emt.lab.repository;

import mk.finki.ukim.emt.lab.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
    Optional<User> findByUsernameAndPasswordQuery(@Param("username") String username, @Param("password") String password);

    Optional<User> findByUsername(String username);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {}
    )
    @Query("select u from User u")
    List<User> fetchAllWithoutLists();

}
