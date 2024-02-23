package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.dto.UserDTO;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClubs();

    ClubDTO findClubById(long clubId);

    void createClub(ClubDTO clubDTO);

    void updateClub(Long id, ClubDTO clubDTO);

    void deleteClub(Long clubId);

    List<ClubDTO> searchClubs(String query);

    boolean isUserClubOwner(Long clubId, String username);
}
