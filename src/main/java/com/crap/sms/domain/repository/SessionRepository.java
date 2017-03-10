package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Session;
import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class SessionRepository extends AbstractRepository{

    private static SessionRepository sessionRepository = new SessionRepository();

    private SessionRepository() {
        super("Session.txt");
        try {
            File file = new File(super.storage);
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
        return super.save(session);
    }

    public Session getBySubscriber(Subscriber subscriber) {
        return null;
    }

    public boolean delete(Session session) {
        return super.delete(session);
    }
}
