package com.app.chatapp.exception;

import com.app.chatapp.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class CotrollerExceptionHandler {

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> resourseNotFound(ServiceException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), "403"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> unauthorisedAccess(AuthenticationException ex, WebRequest request) {
        log.error("Exception ", ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), "401"), HttpStatus.NOT_FOUND);
    }
}
