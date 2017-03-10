package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class UserRepository extends AbstractRepository{

    private static UserRepository userRepository = new UserRepository();

    private UserRepository() {
        super("User.txt");
        try {
            File file = new File(super.storage);
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
        return super.save(user);
    }

    public User getByUsername(String username) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            User user = (User)o;
            if(user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            users.add((User)o);
        }
        return users;
    }

    public boolean delete(User user) {
        return super.delete(user);
    }
}
