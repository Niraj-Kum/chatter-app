package com.app.chatapp.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserAuthResponse {
    private Integer userId;
    private String username;
    private String mobile;
    private String firstName;
    private String lastName;
    private String token;
    private LocalDate birthdate;
}
