package kz.nurimov.springcourse.web.service.impl;

import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.models.Club;
import kz.nurimov.springcourse.web.repository.ClubRepository;
import kz.nurimov.springcourse.web.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDTO> findAllClubs() {
        return clubRepository.findAll().stream().map((club -> convertToClubDTO(club))).collect(Collectors.toList());
    }

    private ClubDTO convertToClubDTO(Club club) {

        ClubDTO clubDTO = ClubDTO.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdAt(club.getCreatedAt())
                .updatedAt(club.getUpdatedAt())
                .build();

        return clubDTO;
    }

}
