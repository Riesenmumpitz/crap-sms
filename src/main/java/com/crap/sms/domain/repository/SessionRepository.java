package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Session;
import com.crap.sms.domain.model.Subscriber;

import java.io.File;
import java.io.IOException;

/**
 * Created by batkefe on 09.03.2017.
 */
public class SessionRepository extends AbstractRepository{

    private static SessionRepository sessionRepository = new SessionRepository();

    private SessionRepository() {
        try {
            File file = new File("Session.txt");
            file.createNewFile();
        }
        catch(IOException io) {
            io.printStackTrace();
        }
    }

    public static SessionRepository getInstance(){
        return sessionRepository;
    }

    public boolean save(Session session) {
        return true;
    }

    public Session getBySubscriber(Subscriber subscriber) {
        return new Session();
    }

    public boolean delete(Session session) {
        return true;
    }
}
