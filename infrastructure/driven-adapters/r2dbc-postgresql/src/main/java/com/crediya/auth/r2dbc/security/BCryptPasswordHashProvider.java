package com.crediya.auth.r2dbc.security;


import com.crediya.auth.model.jwt.PasswordHashProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHashProvider implements PasswordHashProvider {

    private final PasswordEncoder delegate;

    public BCryptPasswordHashProvider() {
        this.delegate = new BCryptPasswordEncoder();
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }

    @Override
    public String encode(String rawPassword) {
        return delegate.encode(rawPassword);
    }
}
