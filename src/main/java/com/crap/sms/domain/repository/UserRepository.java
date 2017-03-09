package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class UserRepository extends AbstractRepository{

    private static UserRepository userRepository = new UserRepository();

    private UserRepository() {
        try {
            File file = new File("User.txt");
            file.createNewFile();
        }
        catch(IOException io) {
            io.printStackTrace();
        }
    }

    public static UserRepository getInstance(){
        return userRepository;
    }

    public boolean save(User user) {
        return true;
    }

    public User getByUsername(String username) {
        return new User();
    }

    public List<User> getAll() {
        return null;
    }

    public boolean delete(User user) {
        return true;
    }
}
