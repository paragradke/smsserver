package com.example.sms.requests;

import com.example.sms.validators.Phone;

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


    public SMSRequest() {

    }

    public SMSRequest(final String to, final String from, final String text) {
        this.to = to;
        this.from = from;
        this.text = text;
    }

    @Size(min=6, max=16, message = "to number is invalid, length should be in the range [6-16]")
    @Phone
    @NotBlank(message = "to number is blank")
    @NotNull(message = "to number is mandatory param it's not present")
    private String to;
    @Size(min=6, max=16, message = "from number is invalid, length should be in the range [6-16]")
    @Phone
    @NotBlank(message = "from number is blank")
    @NotNull(message = "from number is mandatory param it's not present")
    private String from;
    @Size(min=1, max=120, message = "text length is invalid, length should be in the range [1-120]")
    @NotBlank(message = "text is blank")
    @NotNull(message = "text is mandatory param it's not present")
    private String text;
}
