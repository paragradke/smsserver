package com.example.sms.context;

public class LocalContext {

    public static final ThreadLocal userThreadLocal = new ThreadLocal();

    public static void set(Context account) {
        userThreadLocal.set(account);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static Context get() {
        return (Context) userThreadLocal.get();
    }
}
