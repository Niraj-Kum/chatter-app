package com.app.auth.request;

import lombok.Data;

@Data
public class UserAuthRequest {
    private String username;
    private String password;
}
