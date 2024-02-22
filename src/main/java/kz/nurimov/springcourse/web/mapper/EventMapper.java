package kz.nurimov.springcourse.web.mapper;

import kz.nurimov.springcourse.web.dto.EventDTO;
import kz.nurimov.springcourse.web.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

//public class EventMapper {
//    public static EventDTO convertToEventDTO(Event event) {
//        return EventDTO.builder()
//                .id(event.getId())
//                .name(event.getName())
//                .startTime(event.getStartTime())
//                .endTime(event.getEndTime())
//                .type(event.getType())
//                .photoUrl(event.getPhotoUrl())
//                .createdAt(event.getCreatedAt())
//                .updatedAt(event.getUpdatedAt())
//                .clubId(event.getClub() != null ? event.getClub().getId() : null)
//                .build();
//    }
//
//    public static Event convertToEvent(EventDTO eventDTO) {
//        Event event = new Event();
//        event.setId(eventDTO.getId());
//        event.setName(eventDTO.getName());
//        event.setStartTime(eventDTO.getStartTime());
//        event.setEndTime(eventDTO.getEndTime());
//        event.setType(eventDTO.getType());
//        event.setPhotoUrl(eventDTO.getPhotoUrl());
//        // Связь с club требует дополнительной логики для обработки
//        return event;
//    }
//}

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "club.id", target = "clubId")
    @Mapping(source = "club.createdBy.id", target = "clubCreatedById")
    EventDTO eventToEventDTO(Event event);

    @Mapping(source = "clubId", target = "club.id")
    @Mapping(source = "clubCreatedById", target = "club.createdBy.id")
    Event eventDTOToEvent(EventDTO eventDTO);

    List<EventDTO> eventsToEventDTOs(List<Event> events);
    List<Event> eventDTOsToEvents(List<EventDTO> eventDTOs);
}
