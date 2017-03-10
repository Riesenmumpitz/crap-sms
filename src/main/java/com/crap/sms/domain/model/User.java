package com.crap.sms.domain.model;

import java.io.Serializable;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class User implements Serializable{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (passwordHash != user.passwordHash) return false;
        return userName.equals(user.userName);
    }
}
