package com.example.sms.services;


import com.example.sms.mappers.models.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    public Account getAccount(int accountId);

    public Account getAccount(final String username, final String authId);
}
