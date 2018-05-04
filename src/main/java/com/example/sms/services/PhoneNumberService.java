package com.example.sms.services;


import com.example.sms.mappers.models.Account;
import com.example.sms.mappers.models.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhoneNumberService {

    public PhoneNumber get(final Account account, final String number);

}
