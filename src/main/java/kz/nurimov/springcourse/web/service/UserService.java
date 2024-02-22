package kz.nurimov.springcourse.web.service;

import kz.nurimov.springcourse.web.dto.RegistrationDTO;

public interface UserService {
    void saveUser(RegistrationDTO registrationDTO);

    boolean isUserExists(RegistrationDTO registrationDTO);
}
