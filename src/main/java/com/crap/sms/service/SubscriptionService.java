package com.crap.sms.service;

import com.crap.sms.domain.model.Subscription;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Martin Geßenich on 10.03.2017.
 */
public class SubscriptionService {
	private static Map<String, Subscription> subscriptions = new HashMap<String, Subscription>();
	// GreenMobileS, GreenMobileM, GreenMobileL

	public static Set<String> getSubscriptionTypes() {
		return subscriptions.keySet();
	}

	public static String[] getSubscriptionTypesArray() {
		Set<String> set = getSubscriptionTypes();
		String[] result = new String[set.size()];
		int i = 0;
		for (String string : set) {
			result[i] = string;
			i++;
		}
		return result;
	}

	public static boolean addSubscription(String name, Subscription sub) {
		if (subscriptions.containsKey(name)) {
			return false;
		}
		subscriptions.put(name, sub);
		return true;
	}
}
