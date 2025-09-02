package com.crediya.auth.model.user.exceptions;

import com.crediya.auth.model.common.DomainException;
import com.crediya.auth.model.common.ErrorCode;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String email) {
        super("User with email '" + email + "' not found",
                ErrorCode.USER_NOT_FOUND);
    }
}
