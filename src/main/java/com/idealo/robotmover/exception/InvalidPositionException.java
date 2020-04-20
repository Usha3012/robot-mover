package com.idealo.robotmover.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException(String msg) {
        super(msg);
    }
}
