package com.example.authproject.daos.impl;

import com.example.authproject.daos.AccountDAO;
import com.example.authproject.mappers.AccountMapper;
import com.example.authproject.mappers.models.Account;
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
