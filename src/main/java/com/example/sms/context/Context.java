package com.example.sms.context;

import com.example.sms.mappers.models.Account;

public class Context {

    public Account getAccount() {
        return account;
    }

    private Account account = null;

    public Context(final Account account) {
        this.account = account;
    }
}
