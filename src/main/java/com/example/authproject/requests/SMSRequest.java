package com.example.authproject.requests;

import com.example.authproject.validators.Phone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "to is invalid")
    @NotNull(message = "to is invalid")
    private String to;
    @Size(min=6, max=16, message = "from is invalid")
    @Phone
    @NotBlank(message = "from is invalid")
    @NotNull(message = "from is invalid")
    private String from;
    @Size(min=1, max=120)
    @NotBlank(message = "text is invalid")
    @NotNull(message = "text is invalid")
    private String text;
}
