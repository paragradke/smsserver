package com.example.sms.daos;


import com.example.sms.mappers.models.Account;
import com.example.sms.mappers.models.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public interface PhoneNumberDAO {

    public PhoneNumber getPhoneNumber(final Account account, final String number);
}
