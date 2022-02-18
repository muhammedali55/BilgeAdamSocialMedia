package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class AuthServiceException extends RuntimeException {
    private final ErrorType errorType;

    public AuthServiceException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AuthServiceException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

}
