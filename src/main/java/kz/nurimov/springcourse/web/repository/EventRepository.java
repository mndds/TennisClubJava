package kz.nurimov.springcourse.web.repository;

import kz.nurimov.springcourse.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
