package kz.nurimov.springcourse.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private Long id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 2, max = 100, message = "Username should be more than 2 and less than 100 charachters")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    //Role Update ?
    // Добавляем список идентификаторов ролей для обновления
    private List<String> roleNames; // Используем имена ролей для упрощения примера
}
