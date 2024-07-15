package com.app.chatapp.controller;

import com.app.chatapp.exception.ServiceException;
import com.app.chatapp.request.UserAuthRequest;
import com.app.chatapp.request.UserDetails;
import com.app.chatapp.response.ErrorResponse;
import com.app.chatapp.response.GeneralResponse;
import com.app.chatapp.response.RefreshTokenResponse;
import com.app.chatapp.response.UserAuthResponse;
import com.app.chatapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@Tag(name = "UserController", description = "User CRUD related APIs")
@AllArgsConstructor
@RestControllerAdvice
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    private UserService userService;

    @ExceptionHandler(value = {ServiceException.class})
    @PostMapping(value = "/auth/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserAuthResponse> authenticateUser(@RequestBody UserAuthRequest userAuthRequest) throws ServiceException, AuthenticationException {
            UserAuthResponse userAuthResponse = userService.authenticateUser(userAuthRequest);
            return new ResponseEntity<>(userAuthResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Save User Data",
            description = "Save User data on Signup")
    @PostMapping(value = "/auth/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> saveUserDetails(@Valid @RequestBody UserDetails userDetails) {
        try {
            String result = userService.saveUserDetails(userDetails);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (ServiceException ex) {
            log.error("Exception Occurred", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/refresh-token", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> refreshToken(@RequestHeader(value = "Authorization") String bearerToken) {
        GeneralResponse<RefreshTokenResponse> response = userService.refreshToken(bearerToken);
        if(Objects.nonNull(response.getBody())) {
            return new ResponseEntity<>(response.getBody(), response.getStatus());
        }
        return new ResponseEntity<>(response.getError().getErrorMessage(), response.getStatus());
    }

}
