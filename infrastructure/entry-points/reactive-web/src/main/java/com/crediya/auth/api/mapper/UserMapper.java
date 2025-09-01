package com.crediya.auth.api.mapper;

import com.crediya.auth.api.dto.UserRequestDTO;
import com.crediya.auth.model.user.User;

public class UserMapper {
    public static User toDomain(UserRequestDTO dto) {
        return new User(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getAddress(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getBaseSalary()
        );
    }
}

