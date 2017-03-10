package com.crap.sms.service;

import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.repository.SubscriptionRepository;

import java.util.*;

/**
 * Created by Martin Geßenich on 10.03.2017.
 */
public class SubscriptionService {

	public static ArrayList<String> getSubscriptionTypes() {
		ArrayList<String> ret = new ArrayList<String>();
		List<Subscription> all = SubscriptionRepository.getInstance().getAll();
		for (Subscription s : all) {
			ret.add(s.getUniqueName());
		}
		return ret;
	}

	public static String[] getSubscriptionTypesArray() {
		ArrayList<String> subscriptionTypes = getSubscriptionTypes();
		return subscriptionTypes.toArray(new String[0]);
	}

	public static boolean addSubscription(Subscription subscription) {
		return SubscriptionRepository.getInstance().save(subscription);
	}
}
