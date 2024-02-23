package kz.nurimov.springcourse.web.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import kz.nurimov.springcourse.web.dto.UserRegistrationDTO;
import kz.nurimov.springcourse.web.mapper.UserMapper;
import kz.nurimov.springcourse.web.security.PersonDetails;
import kz.nurimov.springcourse.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal PersonDetails currentUser) {
        model.addAttribute("username", currentUser.getUsername());
        return "home";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") UserRegistrationDTO registrationDTO) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid UserRegistrationDTO registrationDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", registrationDTO);
            return "/auth/registration";
        }

        if (userService.isUserExists(registrationDTO)) {
            bindingResult.rejectValue("username","There is already a user with this username");
            model.addAttribute("user", registrationDTO);
            return "/auth/registration";
        }

        userService.registerNewUser(registrationDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
