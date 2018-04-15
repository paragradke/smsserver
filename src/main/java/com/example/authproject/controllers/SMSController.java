package com.example.authproject.controllers;


import com.example.authproject.context.Context;
import com.example.authproject.context.LocalContext;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.requests.SMSRequest;
import com.example.authproject.services.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class SMSController {

    private static final Logger logger = LogManager.getLogger(SMSController.class);

    @Autowired
    private AccountService accountService;

    @Deprecated
    @RequestMapping(value="/accounts/{id}",method = RequestMethod.GET)
    public Account getAccount(@PathVariable int id) {
        logger.info("Request received for account id :"+ id);
        Account account = accountService.getAccount(id);
        logger.info("Response : "+ account);
        return account;
    }


    @RequestMapping(value="/inbound/sms",method = RequestMethod.POST)
    public Account inboundSMS(@RequestBody SMSRequest smsRequest) {
        Context context = LocalContext.get();
        Account account = context.getAccount();
        logger.info("inboundSMS request received for account name:"+ account.getUsername()
                + " to:" + smsRequest.getTo() + " from: "+ smsRequest.getFrom()+ " text: "+ smsRequest.getText());
        logger.info("Response : "+ account);
        return account;
    }

    @RequestMapping(value="/outbound/sms",method = RequestMethod.POST)
    public Account outboundSMS(@RequestBody SMSRequest smsRequest) {
        Context context = LocalContext.get();
        Account account = context.getAccount();
        logger.info("outboundSMS request received for account name:"+ account.getUsername()
                + " to:" + smsRequest.getTo() + " from: "+ smsRequest.getFrom()+ " text: "+ smsRequest.getText());
        logger.info("Response : "+ account);
        return account;
    }
}
