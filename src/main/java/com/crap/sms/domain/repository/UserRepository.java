package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.User;

import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class UserRepository extends AbstractRepository{

    public boolean save(User user) {
        return true;
    }

    public User getByUsername(String username) {
        return new User();
    }

    public List<User> getAll() {
        return null;
    }
}
