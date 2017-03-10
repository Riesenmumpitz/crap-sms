package com.crap.sms.service;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.model.Terminal;
import com.crap.sms.domain.repository.SubscriberRepository;

/**
 * Created by Martin Geßenich on 10.03.2017.
 */
public class SubscriberService {

    private SubscriberRepository subscriberRepository = SubscriberRepository.getInstance();

    public Subscriber addSubscriber(String imsi, Terminal terminal, Subscription subscription,
                                    String foreName, String surName) {
        Subscriber sub = new Subscriber(imsi, terminal, subscription, foreName, surName);
        if (subscriberRepository.save(sub)) {
            return sub;
        }
        return null;
    }

    public Subscriber findSubscriberBy(String imsi) {
        return subscriberRepository.getByImsi(imsi);
    }

    public boolean removeSubScriber(Subscriber subscriber) {
        return subscriberRepository.delete(subscriber);
    }
}
