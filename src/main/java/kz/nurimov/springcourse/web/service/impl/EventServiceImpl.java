package kz.nurimov.springcourse.web.service.impl;

import kz.nurimov.springcourse.web.dto.EventDTO;
import kz.nurimov.springcourse.web.models.Club;
import kz.nurimov.springcourse.web.models.Event;
import kz.nurimov.springcourse.web.repository.ClubRepository;
import kz.nurimov.springcourse.web.repository.EventRepository;
import kz.nurimov.springcourse.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static kz.nurimov.springcourse.web.mapper.EventMapper.convertToEvent;
import static kz.nurimov.springcourse.web.mapper.EventMapper.convertToEventDTO;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDTO eventDTO) {
        Club club = clubRepository.findById(clubId).get();
        Event event = convertToEvent(eventDTO);

        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDTO> findAllEvents() {
        return eventRepository.findAll().stream().map(event -> convertToEventDTO(event)).collect(Collectors.toList());
    }
}
