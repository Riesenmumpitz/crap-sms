package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Subscription;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class SubscriptionRepository extends AbstractRepository{

    private static SubscriptionRepository subscriptionRepository = new SubscriptionRepository();

    private SubscriptionRepository() {
        super("Subsription.txt");
        try {
            File file = new File(super.storage);
            file.createNewFile();
        }
        catch(IOException io) {
            io.printStackTrace();
        }
    }

    public static SubscriptionRepository getInstance(){
        return subscriptionRepository;
    }

    public boolean save(Subscription subscription) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            Subscription subscriptionAlt = (Subscription)o;
            if(subscription.getUniqueName().equals(subscriptionAlt.getUniqueName())) {
                if(!super.delete(subscriptionAlt)) {
                    return false;
                }
            }
        }
        return super.save(subscription);
    }

    public List<Subscription> getAll () {
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            subscriptions.add((Subscription)o);
        }
        return subscriptions;
    }

    public Subscription getByUniqueName(String uniqueName) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            Subscription subscription = (Subscription)o;
            if(subscription.getUniqueName().equals(uniqueName)) {
                return subscription;
            }
        }
        return null;
    }

    public boolean delete(Subscription subscription) {
        return super.delete(subscription);
    }
}
