package com.example.sms.controllers;


import com.example.sms.context.Context;
import com.example.sms.context.LocalContext;
import com.example.sms.exceptions.NumberNotFoundException;
import com.example.sms.exceptions.SLAException;
import com.example.sms.exceptions.StopException;
import com.example.sms.mappers.models.Account;
import com.example.sms.requests.SMSRequest;
import com.example.sms.responses.Response;
import com.example.sms.services.AccountService;
import com.example.sms.services.SMSService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping
public class SMSController {

    private static final Logger logger = LogManager.getLogger(SMSController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private SMSService smsService;

    @Deprecated
    @RequestMapping(value="/accounts/{id}",method = RequestMethod.GET)
    public Account getAccount(@PathVariable int id) {
        logger.info("Request received for account id :"+ id);
        Account account = accountService.getAccount(id);
        logger.info("Response : "+ account);
        return account;
    }


    @RequestMapping(value="/inbound/sms",method = RequestMethod.POST)
    public Response inboundSMS(@Valid @RequestBody SMSRequest smsRequest) throws NumberNotFoundException {
        Context context = LocalContext.get();
        Account account = context.getAccount();
        logger.info("inboundSMS request received for account name:"+ account.getUsername()
                + " to:" + smsRequest.getTo() + " from: "+ smsRequest.getFrom()+ " text: "+ smsRequest.getText());
        smsService.processInboundSMS(account, smsRequest);
        Response response = new Response("inbound sms ok", new ArrayList<>());
        logger.info("Response : "+ response);
        return response;
    }

    @RequestMapping(value="/outbound/sms",method = RequestMethod.POST)
    public Response outboundSMS(@Valid @RequestBody SMSRequest smsRequest) throws StopException,
            SLAException, NumberNotFoundException {
        Context context = LocalContext.get();
        Account account = context.getAccount();
        logger.info("outboundSMS request received for account name:"+ account.getUsername()
                + " to:" + smsRequest.getTo() + " from: "+ smsRequest.getFrom()+ " text: "+ smsRequest.getText());
        smsService.processOutboundSMS(account, smsRequest);
        Response response = new Response("outbound sms ok", new ArrayList<>());
        logger.info("Response : "+ response);
        return response;
    }
}
