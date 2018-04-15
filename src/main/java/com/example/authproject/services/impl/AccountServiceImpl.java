package com.example.authproject.services.impl;

import com.example.authproject.daos.AccountDAO;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.requests.SMSRequest;
import com.example.authproject.services.AccountService;
import com.example.authproject.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private RedisService redisService;

    @Override
    public Account getAccount(int accountId) {
        return accountDAO.getAccount(accountId);
    }

    @Override
    public Account getAccount(String username, String authId) {
        return accountDAO.getAccount(username, authId);
    }


}
