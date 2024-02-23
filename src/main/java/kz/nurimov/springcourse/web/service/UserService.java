package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.UserDTO;
import kz.nurimov.springcourse.web.dto.UserRegistrationDTO;
import kz.nurimov.springcourse.web.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllUsers();

    UserDTO findUserById(Long userId);

    void registerNewUser(UserRegistrationDTO registrationDTO);

    void updateUser(Long userId, UserUpdateDTO updateDTO);

    void deleteUser(Long userId);

    boolean isUserExists(UserRegistrationDTO user);

    UserDTO findByUsername(String username);
}
