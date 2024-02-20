package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.models.Club;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClubs();

    Club saveClub(ClubDTO clubDTO);

    ClubDTO findClubById(long cludId);

    void updateClub(ClubDTO clubDTO);

    void delete(Long clubId);

    List<ClubDTO> searchClubs(String query);
}
