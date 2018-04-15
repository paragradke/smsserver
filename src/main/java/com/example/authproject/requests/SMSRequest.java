package com.example.authproject.requests;

import com.example.authproject.validators.Phone;

import javax.validation.constraints.Size;

public class SMSRequest {

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Size(min=6, max=16, message = "to is invalid")
    @Phone
    private String to;
    @Size(min=6, max=16, message = "from is invalid")
    @Phone
    private String from;
    @Size(min=1, max=120)
    private String text;
}
