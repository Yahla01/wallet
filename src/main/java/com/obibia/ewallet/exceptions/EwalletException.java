package com.obibia.ewallet.exceptions;

import lombok.Getter;

@Getter
public class EwalletException extends RuntimeException{
    private int status;

    public EwalletException(String message, int status) {
        super(message);
        this.status = status;
    }
}
