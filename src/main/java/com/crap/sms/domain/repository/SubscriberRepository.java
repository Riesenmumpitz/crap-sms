package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Subscriber;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class SubscriberRepository extends AbstractRepository{

    private static SubscriberRepository subscriberRepository = new SubscriberRepository();

    private SubscriberRepository() {
        super("Subscriber.txt");
        try {
            File file = new File(super.storage);
            file.createNewFile();
        }
        catch(IOException io) {
            io.printStackTrace();
        }
    }

    public static SubscriberRepository getInstance(){
        return subscriberRepository;
    }

    public boolean save(Subscriber subscriber) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            Subscriber subscriberAlt = (Subscriber)o;
            if(subscriber.getIMSI().equals(subscriberAlt.getIMSI())) {
                if(!super.delete(subscriberAlt)) {
                    return false;
                }
            }
        }
        return super.save(subscriber);
    }

    public Subscriber getByImsi(String imsi) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            Subscriber subscriber = (Subscriber)o;
            if(subscriber.getIMSI().equals(imsi)) {
                return subscriber;
            }
        }
        return null;
    }

    public List<Subscriber> getAll () {
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            subscribers.add((Subscriber)o);
        }
        return subscribers;
    }

    public List<Subscriber> getAllBySubscription (String subscriptionName) {
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            Subscriber subscriber = (Subscriber)o;
            if(subscriber.getSubscription().equals(subscriptionName)) {
                subscribers.add(subscriber);
            }
        }
        return subscribers;
    }

    public List<Subscriber> getAllByTerminal (String terminalName) {
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            Subscriber subscriber = (Subscriber)o;
            if(subscriber.getTerminal().equals(terminalName)) {
                subscribers.add(subscriber);
            }
        }
        return subscribers;
    }

    public boolean delete(Subscriber subscriber) {
        return super.delete(subscriber);
    }
}
