package com.example.sms.services;

import com.example.sms.SMSServerApplicationTests;
import com.example.sms.mappers.models.Account;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static  org.springframework.util.Assert.isNull;

import static  org.springframework.util.Assert.notNull;
import static  org.springframework.util.Assert.isTrue;
public class AccountServiceTests extends SMSServerApplicationTests {

    @Autowired
    private AccountService accountService;


    @Test
    public void getAccountByIdTest() {
        Account account = accountService.getAccount(1);
        notNull(account, "Account object with id 1 is null");
        isTrue(StringUtils.equals(account.getAuthId(), "20S0KPNOIM"), "Auth id doesnt' match for account id 1");
        isTrue(StringUtils.equals(account.getUsername(), "plivo1"), "Auth id doesnt' match for account id 1");
    }


    @Test
    public void getAccountByUsernameTest() {
        Account account = accountService.getAccount("plivo1","20S0KPNOIM");
        notNull(account, "Account object with username plivo1 is null");
        isTrue(StringUtils.equals(account.getAuthId(), "20S0KPNOIM"), "Auth id doesnt' match for account id 1");
        isTrue(StringUtils.equals(account.getUsername(), "plivo1"), "Auth id doesnt' match for account id 1");
    }


    @Test
    public void getAccountByInvalidIdTest() {
        Account account = accountService.getAccount(100);
        isNull(account, "Account object with id 100 is not null");
    }

    @Test
    public void getAccountByInvalidUsernameTest() {
        Account account = accountService.getAccount("pliv0RTest","20S0KPNOIM");
        isNull(account, "Account object with username pliv0RTest is not null");
    }
}
