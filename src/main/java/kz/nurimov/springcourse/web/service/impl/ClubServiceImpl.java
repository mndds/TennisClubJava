package kz.nurimov.springcourse.web.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.dto.UserDTO;
import kz.nurimov.springcourse.web.mapper.ClubMapper;
import kz.nurimov.springcourse.web.models.Club;
import kz.nurimov.springcourse.web.models.UserEntity;
import kz.nurimov.springcourse.web.repository.ClubRepository;
import kz.nurimov.springcourse.web.repository.UserRepository;
import kz.nurimov.springcourse.web.service.ClubService;
import kz.nurimov.springcourse.web.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;
    private final UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, ClubMapper clubMapper, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.clubMapper = clubMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDTO> findAllClubs() {
        return clubRepository.findAll().stream().map(clubMapper::clubToClubDTO).collect(Collectors.toList());
    }

    @Override
    public ClubDTO findClubById(long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id " + clubId));
        return clubMapper.clubToClubDTO(club);
    }

    @Transactional
    @Override
    public void createClub(ClubDTO clubDTO) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        Club club = clubMapper.clubDTOToClub(clubDTO);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Transactional
    @Override
    public void updateClub(Long id,ClubDTO clubDTO) {

        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id " + id));

        club.setTitle(clubDTO.getTitle());
        club.setPhotoUrl(clubDTO.getPhotoUrl());
        club.setContent(clubDTO.getContent());
        club.setUpdatedAt(LocalDateTime.now());

        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));;
        club.setCreatedBy(user);

        clubRepository.save(club);
    }

    @Transactional
    @Override
    public void deleteClub(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id " + clubId));
        clubRepository.deleteById(club.getId());
    }

    @Override
    public List<ClubDTO> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(clubMapper::clubToClubDTO).collect(Collectors.toList());
    }

    @Override
    public boolean isUserClubOwner(Long clubId, String username) {
        return clubRepository.findById(clubId)
                .map(Club::getCreatedBy)
                .map(UserEntity::getUsername)
                .filter(ownerUsername -> ownerUsername.equals(username))
                .isPresent();
    }

}
