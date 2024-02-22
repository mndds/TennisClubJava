package kz.nurimov.springcourse.web.controllers;


import jakarta.validation.Valid;
import kz.nurimov.springcourse.web.dto.ClubDTO;
import kz.nurimov.springcourse.web.models.Club;
import kz.nurimov.springcourse.web.models.UserEntity;
import kz.nurimov.springcourse.web.service.ClubService;
import kz.nurimov.springcourse.web.service.UserService;
import kz.nurimov.springcourse.web.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService clubService;
    private final UserService userService;

    @Autowired
    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        UserEntity user = new UserEntity();
        List<ClubDTO> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("clubs", clubs);
        model.addAttribute("user", user);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(@ModelAttribute("club") Club club) {
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDTO clubDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("club", clubDTO);
            return "clubs-create";
        }

        clubService.saveClub(clubDTO);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable("clubId") Long id, Model model) {
        ClubDTO club = clubService.findClubById(id);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("club") ClubDTO clubDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("club", clubDTO);
            return "clubs-edit";
        }

        clubService.updateClub(clubId, clubDTO);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") Long clubId, Model model) {
        UserEntity user = new UserEntity();
        ClubDTO clubDTO = clubService.findClubById(clubId);
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("club", clubDTO);
        return "clubs-detail";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDTO> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

}
