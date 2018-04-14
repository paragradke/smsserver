package com.example.authproject.context;

import com.example.authproject.mappers.models.Account;

public class Context {

    public Account getAccount() {
        return account;
    }

    private Account account = null;

    public Context(final Account account) {
        this.account = account;
    }
}
