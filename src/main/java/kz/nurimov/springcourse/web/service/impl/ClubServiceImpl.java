package kz.nurimov.springcourse.web.service.impl;

import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.models.Club;
import kz.nurimov.springcourse.web.repository.ClubRepository;
import kz.nurimov.springcourse.web.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.nurimov.springcourse.web.mapper.ClubMapper.convertToClub;
import static kz.nurimov.springcourse.web.mapper.ClubMapper.convertToClubDTO;

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

    @Override
    public Club saveClub(ClubDTO clubDTO) {
        return clubRepository.save(convertToClub(clubDTO));
    }

    @Override
    public ClubDTO findClubById(long clubId) {
        Optional<Club> club = clubRepository.findById(clubId);
        return convertToClubDTO(club.get());
    }

    @Override
    public void updateClub(ClubDTO clubDTO) {
        Club club = convertToClub(clubDTO);
        clubRepository.save(club);
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDTO> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> convertToClubDTO(club)).collect(Collectors.toList());
    }

}
