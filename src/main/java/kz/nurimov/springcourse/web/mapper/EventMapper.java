package kz.nurimov.springcourse.web.mapper;

import kz.nurimov.springcourse.web.dto.EventDTO;
import kz.nurimov.springcourse.web.models.Event;

public class EventMapper {
    public static Event convertToEvent(EventDTO eventDTO) {
        return Event.builder()
                .id(eventDTO.getId())
                .name(eventDTO.getName())
                .startTime(eventDTO.getStartTime())
                .endTime(eventDTO.getEndTime())
                .type(eventDTO.getType())
                .photoUrl(eventDTO.getPhotoUrl())
                .createdAt(eventDTO.getCreatedAt())
                .updatedAt(eventDTO.getUpdatedAt())
                .build();
    }

    public static EventDTO convertToEventDTO(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }
}
