package com.example.sms.services;

import com.example.sms.SMSServerApplicationTests;
import com.example.sms.exceptions.NumberNotFoundException;
import com.example.sms.exceptions.SLAException;
import com.example.sms.exceptions.StopException;
import com.example.sms.mappers.models.Account;
import com.example.sms.requests.SMSRequest;
import com.example.sms.services.impl.SMSServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static com.example.sms.utils.SMSRequestTestUtil.getValidSMSRequest;
import static com.example.sms.utils.SMSRequestTestUtil.getInvalidToSMSRequest;
import static com.example.sms.utils.SMSRequestTestUtil.getInvalidFromSMSRequest;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;


public class SMSServiceTests extends SMSServerApplicationTests {

    @Autowired
    private SMSService smsService;

    @Autowired
    private AccountService accountService;

    private static final Logger logger = LogManager.getLogger(SMSServiceTests.class);

    @Test
    public void processInboundSMSTest() {
        Account account = accountService.getAccount("plivo1", "20S0KPNOIM");
        notNull(account, "Account object with id 1 is null");
        isTrue(StringUtils.equals(account.getAuthId(), "20S0KPNOIM"), "Auth id doesnt' match for account id 1");
        isTrue(StringUtils.equals(account.getUsername(), "plivo1"), "Auth id doesnt' match for account id 1");

        SMSRequest smsRequest = getValidSMSRequest();
        try {
            smsService.processInboundSMS(account, smsRequest);
            isTrue(true, "Inbound SMS didn't process successfully");
        } catch (NumberNotFoundException e) {
            isTrue(false, "Inbound SMS didn't process successfully");
        }
    }

    @Test
    public void processInvalidToInboundSMSTest() {
        Account account = accountService.getAccount("plivo1", "20S0KPNOIM");
        notNull(account, "Account object with id 1 is null");
        isTrue(StringUtils.equals(account.getAuthId(), "20S0KPNOIM"), "Auth id doesnt' match for account id 1");
        isTrue(StringUtils.equals(account.getUsername(), "plivo1"), "Auth id doesnt' match for account id 1");

        SMSRequest smsRequest = getInvalidToSMSRequest();
        try {
            smsService.processInboundSMS(account, smsRequest);
            isTrue(false, "Inbound SMS didn't process successfully");
        } catch (NumberNotFoundException e) {
            isTrue(true, "Inbound SMS didn't process successfully");
        }
    }

    @Test
    public void processInvalidFromOutboundSMSTest() {
        Account account = accountService.getAccount("plivo1", "20S0KPNOIM");
        notNull(account, "Account object with id 1 is null");
        isTrue(StringUtils.equals(account.getAuthId(), "20S0KPNOIM"), "Auth id doesnt' match for account id 1");
        isTrue(StringUtils.equals(account.getUsername(), "plivo1"), "Auth id doesnt' match for account id 1");

        SMSRequest smsRequest = getInvalidFromSMSRequest();
        try {
            smsService.processOutboundSMS(account, smsRequest);
            isTrue(false, "Inbound SMS didn't process successfully");
        } catch (NumberNotFoundException | StopException| SLAException e) {
            isTrue(true, "Inbound SMS didn't process successfully :"+ e.getLocalizedMessage());
        }

    }

    @Test
    public void processOutboundSMSTest() {
        Account account = accountService.getAccount("plivo1", "20S0KPNOIM");
        notNull(account, "Account object with id 1 is null");
        isTrue(StringUtils.equals(account.getAuthId(), "20S0KPNOIM"), "Auth id doesnt' match for account id 1");
        isTrue(StringUtils.equals(account.getUsername(), "plivo1"), "Auth id doesnt' match for account id 1");

        SMSRequest smsRequest = getValidSMSRequest();
        logger.info(smsRequest.getFrom());
        logger.info(smsRequest.getTo());
        logger.info(smsRequest.getText());
        try {
            smsService.processOutboundSMS(account, smsRequest);
            isTrue(true, "Outbound SMS didn't process successfully");
        } catch (NumberNotFoundException | StopException| SLAException e) {
            isTrue(false, "Outbound SMS didn't process successfully :"+ e.getLocalizedMessage());
        }
    }

}
