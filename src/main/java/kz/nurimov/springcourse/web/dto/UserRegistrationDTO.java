package kz.nurimov.springcourse.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 2, max = 100, message = "Username should be more than 2 and less than 100 charachters")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4, message = "Password must be at least 8 characters long")
    private String password;
}
