package com.example.authproject.services;


import com.example.authproject.mappers.models.Account;
import com.example.authproject.requests.SMSRequest;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    public Account getAccount(int accountId);

    public Account getAccount(final String username, final String authId);
}
