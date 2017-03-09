package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Subscriber;

import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class SubscriberRepository extends AbstractRepository{

    public boolean save(Subscriber subscriber) {
        return true;
    }

    public Subscriber getByImsi(String Imsi) {
        return new Subscriber();
    }

    public List<Subscriber> getAll () {
        return null;
    }

    public List<Subscriber> getAllBySubscription () {
        return null;
    }
}
