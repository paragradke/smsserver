package com.example.sms.services.impl;

import com.example.sms.daos.AccountDAO;
import com.example.sms.mappers.models.Account;
import com.example.sms.services.AccountService;
import com.example.sms.services.RedisService;
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
