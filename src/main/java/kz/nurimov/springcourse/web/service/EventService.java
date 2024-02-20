package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.EventDTO;

public interface EventService {
    void createEvent(Long clubId, EventDTO eventDTO);
}
