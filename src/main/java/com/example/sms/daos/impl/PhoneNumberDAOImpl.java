package com.example.sms.daos.impl;

import com.example.sms.controllers.SMSController;
import com.example.sms.daos.PhoneNumberDAO;
import com.example.sms.mappers.PhoneNumberMapper;
import com.example.sms.mappers.models.Account;
import com.example.sms.mappers.models.PhoneNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhoneNumberDAOImpl implements PhoneNumberDAO {

    @Autowired
    private PhoneNumberMapper phoneNumberMapper;

    private static final Logger logger = LogManager.getLogger(PhoneNumberDAOImpl.class);


    public PhoneNumber getPhoneNumber(final Account account, final String number) {
        logger.info("Account : "+account.getUsername() + " number :"+ number);
        PhoneNumber pn = phoneNumberMapper.getPhoneNumber(account.getId(), number);
        return pn;
    }
}
