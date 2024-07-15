package com.app.chatapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Tag(name = "ConversationController", description = "Chat Conversation related APIs")
@AllArgsConstructor
@RestControllerAdvice
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ConversationController {

    @GetMapping(value = "/chat", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> authenticateUser() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
