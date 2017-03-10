package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Subscriber;

import java.io.File;
import java.io.IOException;
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
        return true;
    }

    public Subscriber getByImsi(String Imsi) {
        return null;
    }

    public List<Subscriber> getAll () {
        return null;
    }

    public List<Subscriber> getAllBySubscription () {
        return null;
    }

    public boolean delete(Subscriber subscriber) {
        return true;
    }
}
