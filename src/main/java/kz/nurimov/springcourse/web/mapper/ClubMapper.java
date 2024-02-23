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

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface ClubMapper {
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(target = "events", source = "events")
    ClubDTO clubToClubDTO(Club club);

    @Mapping(source = "createdById", target = "createdBy.id")
    @InheritInverseConfiguration(name = "clubToClubDTO")
    Club clubDTOToClub(ClubDTO clubDTO);
}