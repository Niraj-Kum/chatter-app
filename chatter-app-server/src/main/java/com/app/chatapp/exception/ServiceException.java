package com.app.chatapp.exception;

public class ServiceException extends Exception {
    private String message;

    public ServiceException(String s) {
        super(s);
    }
}
