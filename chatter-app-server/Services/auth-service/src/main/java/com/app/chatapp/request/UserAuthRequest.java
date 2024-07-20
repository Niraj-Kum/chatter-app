package com.app.chatapp.request;

import lombok.Data;

@Data
public class UserAuthRequest {
    private String username;
    private String password;
}
