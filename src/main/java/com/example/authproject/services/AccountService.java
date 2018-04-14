package com.example.authproject.services;


import com.example.authproject.mappers.models.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    public Account getAccount(int accountId);
}
