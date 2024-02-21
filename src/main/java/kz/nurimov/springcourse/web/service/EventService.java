package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.EventDTO;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDTO eventDTO);
    List<EventDTO> findAllEvents();

    EventDTO findEventById(Long eventId);

    void updateEvent(EventDTO eventDTO);

    void deleteEvent(Long eventId);
}
