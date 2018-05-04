package com.example.sms.services;

import com.example.sms.SMSServerApplicationTests;
import com.example.sms.mappers.models.Account;
import com.example.sms.mappers.models.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.util.Assert.isNull;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class PhoneNumberServiceTests extends SMSServerApplicationTests {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private AccountService accountService;

    @Test
    public void getPhoneNumberTest() {
        Account account = accountService.getAccount("plivo1", "20S0KPNOIM");
        notNull(account, "Account object with id 1 is null");
        isTrue(StringUtils.equals(account.getAuthId(), "20S0KPNOIM"), "Auth id doesnt' match for account id 1");
        isTrue(StringUtils.equals(account.getUsername(), "plivo1"), "Auth id doesnt' match for account id 1");

        //4924195509198
        PhoneNumber phoneNumber = phoneNumberService.get(account,"4924195509197");
        notNull(phoneNumber, "phoneNumber object for number 4924195509197 is null");
        isTrue(StringUtils.equals(phoneNumber.getNumber(), "4924195509197"), "Phone number doesnt' match");
        isTrue(phoneNumber.getAccountId()==account.getId(), "Account id doesn't match for phone number 4924195509197");
    }

    @Test
    public void getInvalidPhoneNumberTest() {
        Account account = accountService.getAccount(1);
        notNull(account, "Account object with id 1 is null");
        isTrue(StringUtils.equals(account.getAuthId(), "20S0KPNOIM"), "Auth id doesnt' match for account id 1");
        isTrue(StringUtils.equals(account.getUsername(), "plivo1"), "Auth id doesnt' match for account id 1");

        //4924195509198
        PhoneNumber phoneNumber = phoneNumberService.get(account, "invalid0909090");
        isNull(phoneNumber, "phoneNumber object for number invalid0909090 is not null");
    }
}
