package kz.nurimov.springcourse.web.mapper;

import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.models.Club;
import kz.nurimov.springcourse.web.models.Event;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.nurimov.springcourse.web.mapper.EventMapper.convertToEvent;
import static kz.nurimov.springcourse.web.mapper.EventMapper.convertToEventDTO;

public class ClubMapper {
    public static ClubDTO convertToClubDTO(Club club) {

        ClubDTO clubDTO = ClubDTO.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdAt(club.getCreatedAt())
                .updatedAt(club.getUpdatedAt())
                .events(club.getEvents().stream().map(event -> convertToEventDTO(event)).collect(Collectors.toList()))
                .build();

        return clubDTO;
    }

//    public static Club convertToClub(ClubDTO clubDTO) {
//        Club club = Club.builder()
//                .id(clubDTO.getId())
//                .title(clubDTO.getTitle())
//                .photoUrl(clubDTO.getPhotoUrl())
//                .content(clubDTO.getContent())
//                //.createdAt(clubDTO.getCreatedAt())
//                //.updatedAt(clubDTO.getUpdatedAt())
//                .events(clubDTO.getEvents().stream().map(eventDTO -> convertToEvent(eventDTO)).collect(Collectors.toList()))
//                .build();
//
//        return club;
//    }

    public static Club convertToClub(ClubDTO clubDTO) {
        List<Event> events = Optional.ofNullable(clubDTO.getEvents())
                .orElse(Collections.emptyList())
                .stream()
                .map(EventMapper::convertToEvent)
                .collect(Collectors.toList());

        return Club.builder()
                .id(clubDTO.getId())
                .title(clubDTO.getTitle())
                .photoUrl(clubDTO.getPhotoUrl())
                .content(clubDTO.getContent())
                .createdAt(clubDTO.getCreatedAt())
                .updatedAt(clubDTO.getUpdatedAt())
                .events(events)
                .build();
    }

}
