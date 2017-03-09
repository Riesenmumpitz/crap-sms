package com.crap.sms.controllers;

import java.util.List;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.model.Terminal;

public class SubscriberStringHelper {

	public static String formatSubscribers(List<Subscriber> subscribers) {
		StringBuilder result = new StringBuilder();
		for (Subscriber subscriber : subscribers) {
			result.append(subscriber.getForeName()).append(" ").append(subscriber.getSurName()).append(" (");
			result.append(subscriber.getIMSI()).append("): ");
			result.append(getTerminalString((subscriber.getTerminal()))).append(", ");
			result.append(getSubscriptionString((subscriber.getSubscription())));
			result.append("\n");
		}
		return result.toString();
	}

	private static String getSubscriptionString(Subscription subscription) {
		switch (subscription) {
		case GreenMobileS:
			return "GreenMobile S";
		case GreenMobileM:
			return "GreenMobile M";
		case GreenMobileL:
			return "GreenMobile L";
		default:
			return "undefined Subscription";
		}
	}

	private static String getTerminalString(Terminal terminal) {
		switch (terminal) {
		// TODO Terminals nachtragen
		default:
			return "undefined Terminal";
		}
	}
}
