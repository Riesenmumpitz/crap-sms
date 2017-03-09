package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Session;
import com.crap.sms.domain.model.Subscriber;

/**
 * Created by batkefe on 09.03.2017.
 */
public class SessionRepository extends AbstractRepository{

    public boolean save(Session session) {
        return true;
    }

    public Session getBySubscriber(Subscriber subscriber) {
        return new Session();
    }
}
