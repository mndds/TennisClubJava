package kz.nurimov.springcourse.web.repository;

import kz.nurimov.springcourse.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
