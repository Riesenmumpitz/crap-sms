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

	public static boolean saveSubscription(Subscription subscription) {
		//TODO validate
		return subscriptionRepository.save(subscription);
	}

	public static Subscription getSubscription(String name) {
		return subscriptionRepository.getByUniqueName(name);
	}

	public static boolean removeSubscription(Subscription subscription) {
		return subscriptionRepository.delete(subscription);
	}

	public static List<Subscription> getAllSubscription() {
		return subscriptionRepository.getAll();
	}
}
