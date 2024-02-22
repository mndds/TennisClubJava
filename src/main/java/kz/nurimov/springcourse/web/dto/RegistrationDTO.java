package kz.nurimov.springcourse.web.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import kz.nurimov.springcourse.web.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationDTO {
    private Long id;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 100, message = "Username should be more than 2 and less than 100 charachters")
    private String username;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;
}
