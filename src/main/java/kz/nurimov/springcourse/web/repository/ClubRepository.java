package kz.nurimov.springcourse.web.repository;

import kz.nurimov.springcourse.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    @Query("SELECT c FROM Club c WHERE lower(c.title) LIKE lower(concat('%', :query, '%'))")
    List<Club> searchClubs(String query);

}
