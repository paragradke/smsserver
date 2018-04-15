package com.example.authproject.daos.impl;

import com.example.authproject.daos.PhoneNumberDAO;
import com.example.authproject.mappers.PhoneNumberMapper;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.mappers.models.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberDAOImpl implements PhoneNumberDAO {

    @Autowired
    private PhoneNumberMapper phoneNumberMapper;


    public PhoneNumber getPhoneNumber(final Account account, final String number) {
        return phoneNumberMapper.getPhoneNumber(account.getId(), number);
    }
}
