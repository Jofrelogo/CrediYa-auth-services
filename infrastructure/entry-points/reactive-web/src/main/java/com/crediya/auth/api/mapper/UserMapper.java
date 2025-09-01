package com.crediya.auth.api.mapper;

import com.crediya.auth.api.dto.UserRequestDTO;
import com.crediya.auth.api.dto.UserResponseDTO;
import com.crediya.auth.model.user.User;

public class UserMapper {

    public static User requestToDomain(UserRequestDTO dto) {
        return new User(
                dto.getDni(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getAddress(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getBaseSalary()
        );
    }

    public static UserResponseDTO DomainToRespons(User user) {
        return new UserResponseDTO(
                user.getDni(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getBaseSalary()
        );
    }
}

