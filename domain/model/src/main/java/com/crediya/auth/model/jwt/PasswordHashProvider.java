package com.crediya.auth.model.jwt;

public interface PasswordHashProvider {
    boolean matches(String rawPassword, String encodedPassword);
    String encode(String rawPassword);
}
