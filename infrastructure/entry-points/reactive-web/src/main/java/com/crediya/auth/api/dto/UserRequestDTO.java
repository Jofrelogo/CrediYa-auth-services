package com.crediya.auth.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "UserRequestDTO", description = "Data transfer object for creating a new user")
public class UserRequestDTO {

    @NotBlank(message = "Dni is required")
    @Schema(description = "Unique identifier of the user", example = "12345")
    private String dni;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Past(message = "Birthdate must be in the past")
    @Schema(description = "Date of birth of the user", example = "1990-05-20")
    private LocalDate birthDate;

    @Schema(description = "Residential address of the user", example = "CLL 34 # 28 - 49")
    private String address;

    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Phone number must be valid")
    @Schema(description = "Phone number of the user", example = "+573001234567")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @NotNull
    @Positive(message = "Base salary must be positive")
    @Max(value = 15000000, message = "The maximum salary base is 15000000")
    @Schema(description = "Base salary of the user", example = "2500")
    private Double baseSalary;

    @NotNull
    @Schema(description = "Password of the user", example = "********")
    private String passwordHash;

    @NotNull
    @Schema(description = "Role of the user", example = "ADMIN")
    private String role;// "ADMIN", "ASESOR", "CLIENTE"

    @NotNull
    @Schema(description = "Enabled User ", example = "True")
    private Boolean enabled;

    public String getDni() {
        return dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

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
