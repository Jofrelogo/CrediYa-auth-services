package com.crediya.auth.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private String dni;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String email;
    private Double baseSalary;

    private String passwordHash;
    private String role;           // "ADMIN", "ASESOR", "CLIENTE"
    private Boolean enabled;


    @Override
    public String toString() {
        return "User{" +
                "dni='"+ dni + '\'' +
                " firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
