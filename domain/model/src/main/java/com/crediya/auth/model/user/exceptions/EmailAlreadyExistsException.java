package com.crediya.auth.model.user.exceptions;

import com.crediya.auth.model.common.DomainException;
import com.crediya.auth.model.common.ErrorCode;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException(String email) {
        super("Email '" + email + "' already exists",
                ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
