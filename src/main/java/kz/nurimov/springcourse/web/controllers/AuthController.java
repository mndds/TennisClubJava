package kz.nurimov.springcourse.web.controllers;

import jakarta.validation.Valid;
import kz.nurimov.springcourse.web.dto.RegistrationDTO;
import kz.nurimov.springcourse.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //TODO Сделать так везде в контроллерах а не создавать модель
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") RegistrationDTO registrationDTO) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid RegistrationDTO registrationDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", registrationDTO);
            return "/auth/registration";
        }

        if (userService.isUserExists(registrationDTO)) {
            bindingResult.rejectValue("email","There is already a user with this email/username");
            model.addAttribute("user", registrationDTO);
            return "/auth/registration";
        }

        userService.saveUser(registrationDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        //d9d30719-7647-46cc-bcd1-04ade12a1f12
        //TODO add CSRF functional
        return "auth/login";
    }

}
