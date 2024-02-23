package kz.nurimov.springcourse.web.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.nurimov.springcourse.web.dto.UserDTO;
import kz.nurimov.springcourse.web.dto.UserRegistrationDTO;
import kz.nurimov.springcourse.web.dto.UserUpdateDTO;
import kz.nurimov.springcourse.web.mapper.UserMapper;
import kz.nurimov.springcourse.web.models.Role;
import kz.nurimov.springcourse.web.models.UserEntity;
import kz.nurimov.springcourse.web.repository.RoleRepository;
import kz.nurimov.springcourse.web.repository.UserRepository;
import kz.nurimov.springcourse.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        return userMapper.userToUserDTO(user);
    }

    @Transactional
    @Override
    public void registerNewUser(UserRegistrationDTO registrationDTO) {
        if(userRepository.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new EntityNotFoundException("Username already taken");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registrationDTO.getUsername());
        newUser.setEmail(registrationDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new EntityNotFoundException("Not found user role while creating"));
        newUser.setRoles(Collections.singletonList(role));

        userRepository.save(newUser);
    }

    @Transactional
    @Override
    public void updateUser(Long userId, UserUpdateDTO updateDTO) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        user.setUsername(updateDTO.getUsername());
        user.setEmail(updateDTO.getEmail());
        user.setUpdatedAt(LocalDateTime.now());

        if (updateDTO.getRoleNames() != null && !updateDTO.getRoleNames().isEmpty()) {
            List<Role> updatedRoles = updateDTO.getRoleNames().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new EntityNotFoundException("Role not found: " + roleName)))
                    .collect(Collectors.toList());
            user.setRoles(updatedRoles);
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        userRepository.deleteById(user.getId());
    }

    @Override
    public boolean isUserExists(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = userRepository.findByUsername(userRegistrationDTO.getUsername()).orElse(null);
        return user != null && user.getUsername() != null && !user.getUsername().isEmpty();
    }

    @Override
    public UserDTO findByUsername(String username) {
        UserEntity user =  userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));

        return userMapper.userToUserDTO(user);
    }
}
