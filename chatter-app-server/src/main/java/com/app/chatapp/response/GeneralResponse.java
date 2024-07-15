package com.app.chatapp.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
public class GeneralResponse<T> {
    private T body;
    private HttpStatus status;
    private ErrorResponse error;
}
