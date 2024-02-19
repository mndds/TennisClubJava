package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.ClubDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClubs();

}
