package kz.nurimov.springcourse.web.controllers;


import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDTO> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
}
