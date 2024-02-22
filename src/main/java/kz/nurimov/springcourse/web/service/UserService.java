package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.RegistrationDTO;
import kz.nurimov.springcourse.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDTO registrationDTO);

    boolean isUserExists(RegistrationDTO registrationDTO);

    UserEntity findByUsername(String username);
}
