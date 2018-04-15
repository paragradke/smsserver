package com.example.authproject.daos;


import com.example.authproject.mappers.PhoneNumberMapper;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.mappers.models.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public interface PhoneNumberDAO {

    public PhoneNumber getPhoneNumber(final Account account, final String number);
}
