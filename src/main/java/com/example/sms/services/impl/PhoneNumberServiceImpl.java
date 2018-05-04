package com.example.sms.services.impl;


import com.example.sms.daos.PhoneNumberDAO;
import com.example.sms.mappers.models.Account;
import com.example.sms.mappers.models.PhoneNumber;
import com.example.sms.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    private PhoneNumberDAO phoneNumberDAO;

    @Override
    public PhoneNumber get(Account account, String number) {
        return phoneNumberDAO.getPhoneNumber(account, number);
    }

}
