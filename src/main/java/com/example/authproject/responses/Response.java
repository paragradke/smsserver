package com.example.authproject.responses;

import java.util.List;

public class Response {

    public Response(final String message, final List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrorr(List<String> errors) {
        this.errors = errors;
    }

    private String message;
    private List<String> errors;
}
