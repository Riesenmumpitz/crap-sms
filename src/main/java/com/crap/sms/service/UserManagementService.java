package com.crap.sms.service;

import com.crap.sms.domain.model.User;
import com.crap.sms.domain.repository.UserRepository;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class UserManagementService {

    private static UserRepository userRepository = UserRepository.getInstance();

    public static User createUser(String name, int password) {
        if (name == null || name.length() == 0) {
            return null;
        }
        User user = new User(name, password);
        if (userRepository.save(user)) {
            return user;
        }
        return null;
    }

    public static User findUser(String name) {
        return userRepository.getByUsername(name);
    }

}
