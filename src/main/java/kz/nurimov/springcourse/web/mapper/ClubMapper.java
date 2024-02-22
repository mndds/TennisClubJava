package kz.nurimov.springcourse.web.mapper;

import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.models.Club;
import kz.nurimov.springcourse.web.models.Event;
import kz.nurimov.springcourse.web.service.ClubService;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//public class ClubMapper {
//
//    public static ClubDTO convertToClubDTO(Club club) {
//
//        return ClubDTO.builder()
//                .id(club.getId())
//                .title(club.getTitle())
//                .photoUrl(club.getPhotoUrl())
//                .content(club.getContent())
//                .createdAt(club.getCreatedAt())
//                .updatedAt(club.getUpdatedAt())
//                .createdById(club.getCreatedBy() != null ? club.getCreatedBy().getId() : null)
//                .events(club.getEvents() != null ? club.getEvents().stream()
//                        .map(EventMapper::convertToEventDTO)
//                        .collect(Collectors.toList()) : null)
//                .build();
//    }
//
//    public static Club convertToClub(ClubDTO clubDTO) {
//        Club club = new Club();
//        club.setId(clubDTO.getId());
//        club.setTitle(clubDTO.getTitle());
//        club.setPhotoUrl(clubDTO.getPhotoUrl());
//        club.setContent(clubDTO.getContent());
//        club.setCreatedAt(clubDTO.getCreatedAt());
//        club.setUpdatedAt(clubDTO.getUpdatedAt());
//        return club;
//    }
//
//}

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface ClubMapper {
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(target = "events", source = "events")
    ClubDTO clubToClubDTO(Club club);

    @Mapping(source = "createdById", target = "createdBy.id")
    @InheritInverseConfiguration(name = "clubToClubDTO")
    Club clubDTOToClub(ClubDTO clubDTO);

    List<ClubDTO> clubsToClubDTOs(List<Club> clubs);

    List<Club> clubDTOsToClubs(List<ClubDTO> clubDTOs);
}