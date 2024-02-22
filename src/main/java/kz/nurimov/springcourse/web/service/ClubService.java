package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.ClubDTO;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClubs();

    ClubDTO findClubById(long clubId);

    void createClub(ClubDTO clubDTO);

    void updateClub(Long id, ClubDTO clubDTO);

    void deleteClub(Long clubId);

    List<ClubDTO> searchClubs(String query);
}
