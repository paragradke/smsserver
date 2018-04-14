package com.example.authproject.controllers;


import com.example.authproject.context.Context;
import com.example.authproject.context.LocalContext;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.services.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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


    @RequestMapping(value="/inbound/sms",method = RequestMethod.GET)
    public Account inboundSMS(@PathVariable int id) {
        logger.info("Request received for account id :"+ id);
        Context context = LocalContext.get();
        Account account = context.getAccount();
        logger.info("Response : "+ account);
        return account;
    }
}
