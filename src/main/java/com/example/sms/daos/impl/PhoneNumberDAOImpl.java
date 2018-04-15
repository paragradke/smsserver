package com.example.sms.daos.impl;

import com.example.sms.daos.PhoneNumberDAO;
import com.example.sms.mappers.PhoneNumberMapper;
import com.example.sms.mappers.models.Account;
import com.example.sms.mappers.models.PhoneNumber;
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
