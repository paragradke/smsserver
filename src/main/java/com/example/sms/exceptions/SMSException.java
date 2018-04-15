package com.example.sms.exceptions;

public class SMSException extends Exception {

    public SMSException(final String error) {
        super(error);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    private String error;
}
