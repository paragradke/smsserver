package com.example.authproject.controllers;


import com.example.authproject.models.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SMSController {

    @RequestMapping(value="/",method = RequestMethod.GET)
    public Object getAccount(){
        return new Account();
    }

}
