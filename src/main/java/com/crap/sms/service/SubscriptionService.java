package com.crap.sms.service;

import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.repository.SubscriptionRepository;

import java.util.*;

/**
 * Created by Martin Geßenich on 10.03.2017.
 */
public class SubscriptionService {

	private static SubscriptionRepository subscriptionRepository = SubscriptionRepository.getInstance();

	public static ArrayList<String> getSubscriptionTypes() {
		ArrayList<String> ret = new ArrayList<String>();
		List<Subscription> all = subscriptionRepository.getAll();
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
		return subscriptionRepository.save(subscription);
	}
}
