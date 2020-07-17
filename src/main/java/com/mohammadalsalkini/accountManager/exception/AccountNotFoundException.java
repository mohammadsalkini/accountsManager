package com.mohammadalsalkini.accountManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 16:47
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message, null, false, false);
    }
}
