package com.crap.sms.domain.model;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class User {
    private String userName;
    private int passwordHash;

    public User(String userName, int password) {
        this.userName = userName;
        this.passwordHash = password;
    }

    public String getUserName() {
        return userName;
    }

    public int getPasswordHash() {
        return passwordHash;
    }
}
