package com.example.authproject.services;


import com.example.authproject.mappers.models.Account;
import com.example.authproject.mappers.models.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public interface PhoneNumberService {

    public PhoneNumber get(final Account account, final String number);
}
