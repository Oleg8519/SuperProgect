package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserGroupNotFoundException extends RuntimeException {
    public UserGroupNotFoundException() {
        super();
    }

    public UserGroupNotFoundException(String user) {
        super(user);
    }

    public UserGroupNotFoundException(String user, Throwable cause) {
        super(user, cause);
    }
}

