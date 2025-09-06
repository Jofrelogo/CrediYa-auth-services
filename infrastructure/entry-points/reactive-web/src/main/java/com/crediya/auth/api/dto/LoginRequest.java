package com.crediya.auth.api.dto;


public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest() {
        // Constructor vacío requerido por frameworks como Jackson
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

