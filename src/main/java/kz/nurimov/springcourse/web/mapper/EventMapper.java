package kz.nurimov.springcourse.web.mapper;

import kz.nurimov.springcourse.web.dto.EventDTO;
import kz.nurimov.springcourse.web.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

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
