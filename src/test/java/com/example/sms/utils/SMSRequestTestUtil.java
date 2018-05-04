package com.example.sms.utils;

import com.example.sms.requests.SMSRequest;

public class SMSRequestTestUtil {

    public static SMSRequest getValidSMSRequest() {
        return new SMSRequest("4924195509198", "4924195509196", "Hello World");
    }


    public static SMSRequest getInvalidToSMSRequest() {
        return new SMSRequest("1234", "4924195509196", "Hello World");
    }

    public static SMSRequest getInvalidFromSMSRequest() {
        return new SMSRequest("4924195509198", "1234", "Hello World");
    }

}
