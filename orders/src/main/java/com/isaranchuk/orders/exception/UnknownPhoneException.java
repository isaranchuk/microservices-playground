package com.isaranchuk.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class UnknownPhoneException extends RuntimeException {

    public UnknownPhoneException(String message) {
        super(message);
    }
}
