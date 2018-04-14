package com.example.authproject.daos;

import com.example.authproject.mappers.models.Account;

public interface AccountDAO {

    public Account getAccount(int accountId);

    public Account getAccount(final String username, final String authId);
}
