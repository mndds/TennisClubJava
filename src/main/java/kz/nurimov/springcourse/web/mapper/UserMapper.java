package kz.nurimov.springcourse.web.mapper;

import kz.nurimov.springcourse.web.dto.UserDTO;
import kz.nurimov.springcourse.web.dto.UserRegistrationDTO;
import kz.nurimov.springcourse.web.dto.UserUpdateDTO;
import kz.nurimov.springcourse.web.models.Role;
import kz.nurimov.springcourse.web.models.UserEntity;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roleNames", source = "roles", qualifiedByName = "rolesToRoleNames")
    UserDTO userToUserDTO(UserEntity userEntity);

    @Mapping(target = "roles", ignore = true)
    UserEntity userRegistrationDTOToUser(UserRegistrationDTO registrationDTO);

    @Named("rolesToRoleNames")
    static List<String> rolesToRoleNames(List<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
