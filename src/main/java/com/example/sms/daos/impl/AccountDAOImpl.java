package com.example.sms.daos.impl;

import com.example.sms.daos.AccountDAO;
import com.example.sms.mappers.AccountMapper;
import com.example.sms.mappers.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccount(int accountId) {
        return accountMapper.get(accountId);
    }

    @Override
    public Account getAccount(String username, String authId) {
        return accountMapper.getAccount(username, authId);
    }
}
