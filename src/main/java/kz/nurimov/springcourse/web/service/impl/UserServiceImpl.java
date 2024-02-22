package kz.nurimov.springcourse.web.service.impl;

import kz.nurimov.springcourse.web.dto.RegistrationDTO;
import kz.nurimov.springcourse.web.models.Role;
import kz.nurimov.springcourse.web.models.UserEntity;
import kz.nurimov.springcourse.web.repository.RoleRepository;
import kz.nurimov.springcourse.web.repository.UserRepository;
import kz.nurimov.springcourse.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDTO registrationDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDTO.getUsername());
        userEntity.setEmail(registrationDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Collections.singletonList(role));
        userRepository.save(userEntity);
    }


    @Override
    public boolean isUserExists(RegistrationDTO registrationDTO) {
        UserEntity existingUserEmail = userRepository.findByEmail(registrationDTO.getEmail());

        return existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
