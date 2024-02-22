package kz.nurimov.springcourse.web.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.nurimov.springcourse.web.dto.EventDTO;
import kz.nurimov.springcourse.web.mapper.EventMapper;
import kz.nurimov.springcourse.web.models.Club;
import kz.nurimov.springcourse.web.models.Event;
import kz.nurimov.springcourse.web.repository.ClubRepository;
import kz.nurimov.springcourse.web.repository.EventRepository;
import kz.nurimov.springcourse.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    private final ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.clubRepository = clubRepository;
    }


    @Override
    public List<EventDTO> findAllEvents() {
        return eventRepository.findAll().stream().map(eventMapper::eventToEventDTO).collect(Collectors.toList());
    }

    @Override
    public EventDTO findEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + eventId));
        return eventMapper.eventToEventDTO(event);
    }

    @Transactional
    @Override
    public void createEvent(Long clubId, EventDTO eventDTO) {
        Event event = eventMapper.eventDTOToEvent(eventDTO);

        Club club = clubRepository.findById(event.getClub().getId())
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id " + eventDTO.getClubId()));

        event.setClub(club);
        eventRepository.save(event);
    }

    @Transactional
    @Override
    public void updateEvent(Long id, EventDTO eventDTO) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id" + id));

        if (eventDTO.getClubId() != null) {
            Club club = clubRepository.findById(eventDTO.getClubId())
                    .orElseThrow(() -> new EntityNotFoundException("Club not found with id: " + eventDTO.getClubId()));
            event.setClub(club);
        } else {
            throw new IllegalArgumentException("Club ID must not be null for the event");
        }

        event.setName(eventDTO.getName());
        event.setStartTime(eventDTO.getStartTime());
        event.setEndTime(eventDTO.getEndTime());
        event.setType(eventDTO.getType());
        event.setPhotoUrl(eventDTO.getPhotoUrl());
        event.setUpdatedAt(LocalDateTime.now());
        eventRepository.save(event);
    }

    @Transactional
    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + id));
        eventRepository.delete(event);
    }


}
