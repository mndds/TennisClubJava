package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.EventDTO;

import java.util.List;

public interface EventService {

    List<EventDTO> findAllEvents();

    EventDTO findEventById(Long eventId);

    void createEvent(Long clubId,EventDTO eventDTO);

    void updateEvent(Long id, EventDTO eventDTO);

    void deleteEvent(Long eventId);

    boolean isUserEventOwner(Long eventId, String username);
}
