package com.crap.sms.controller;

import java.util.List;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.model.Terminal;
import com.crap.sms.domain.repository.SubscriptionRepository;
import com.crap.sms.domain.repository.TerminalRepository;

public class SubscriberStringHelper {

	public static String formatSubscribers(List<Subscriber> subscribers) {
		if ((subscribers == null) || (subscribers.isEmpty())) {
			return "There are no subscribers in the system.\n";
		}
			
		StringBuilder result = new StringBuilder();
		for (Subscriber subscriber : subscribers) {
			result.append(subscriber.getForeName()).append(" ").append(subscriber.getSurName()).append(" (");
			result.append(subscriber.getIMSI()).append("): ");
			result.append(getTerminalString(TerminalRepository.getInstance().getByUniqueName(subscriber.getTerminal()))).append(", ");
			result.append(getSubscriptionString(SubscriptionRepository.getInstance().getByUniqueName(subscriber.getSubscription())));
			result.append("\n");
		}
		return result.toString();
	}

	private static String getSubscriptionString(Subscription subscription) {
		return "undefined Subscription";
	}

	private static String getTerminalString(Terminal terminal) {
		return "undefined Terminal";
	}
}
