package com.example.sms.daos;

import com.example.sms.mappers.models.Account;

public interface AccountDAO {

    public Account getAccount(int accountId);

    public Account getAccount(final String username, final String authId);
}
