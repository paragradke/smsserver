package com.example.authproject.services.impl;


import com.example.authproject.daos.PhoneNumberDAO;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.mappers.models.PhoneNumber;
import com.example.authproject.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    private PhoneNumberDAO phoneNumberDAO;

    @Override
    public PhoneNumber get(Account account, String number) {
        return phoneNumberDAO.getPhoneNumber(account, number);
    }
}
