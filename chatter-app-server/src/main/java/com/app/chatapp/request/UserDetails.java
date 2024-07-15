package com.app.chatapp.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetails {
    @NotBlank
    private String username;
    @NotBlank
    private String mobile;
    @NotBlank
    private String pin;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private LocalDate birthDate;
    private String displayPictureUrl;
}
