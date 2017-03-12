package com.crap.sms.service;

import com.crap.sms.domain.model.RAN;
import com.crap.sms.domain.model.Session;
import com.crap.sms.domain.model.Subscriber;

/**
 * Created by Martin Geßenich on 10.03.2017.
 */
public class SessionService {

	public Session createPhoneSession(int seconds) {
		if (!(seconds >= 0 && seconds < Integer.MAX_VALUE)) {
			return null;
		}
		return new Session(seconds);
	}

	public Session createDataSession(int seconds, RAN conection) {
		if (!(seconds >= 0 && seconds < Integer.MAX_VALUE) || conection == null) {
			return null;
		}
		return new Session(seconds, conection, generateRandomConnectionRate());
	}

	private double generateRandomConnectionRate() {
		switch ((int) Math.random() * 4) {
		case 0:
			return 0;
		case 1:
			return .1;
		case 2:
			return .25;
		case 3:
			return .5;
		default:
			return 42;
		}

	}

	public boolean bookSession(Subscriber sub, Session session) {
		return false;
	}
}
