package com.crap.sms.service;

import com.crap.sms.domain.model.RAN;
import com.crap.sms.domain.model.Session;
import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.repository.SubscriberRepository;
import com.crap.sms.domain.repository.SubscriptionRepository;
import com.crap.sms.domain.repository.UserRepository;

public class SessionService {

	public static Session createPhoneSession(int seconds) {
		if (!(seconds >= 0 && seconds < Integer.MAX_VALUE)) {
			return null;
		}
		return new Session(seconds);
	}

	public static Session createDataSession(int seconds, RAN connection) {
		if (!(seconds >= 0 && seconds < Integer.MAX_VALUE)
				|| connection == null) {
			return null;
		}
		return new Session(seconds, connection, generateRandomConnectionRate());
	}

	private static double generateRandomConnectionRate() {
		switch ((int) (Math.random() * 4)) {
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

	public static boolean bookSession(Subscriber sub, Session session) {
        String print = "";
		boolean usedUp = false;
		int usedSec = 0;
		// add subscriber info
		print += "\n" + sub.toString() + "\n";

		if (session.getConnection() == null) {
			// voice call service

			sub.setUsedMinutes(sub.getUsedMinutes() + (int)(session.getSeconds()/60));
			print += "Service: Voice Call\n";
			usedSec += session.getSeconds();
		} else {
			// data service

			print += "Service: Data service\n";
			int achievedDataRate;
			int usedVolume = 0;
			RAN connection = session.getConnection();
			Subscription s = SubscriptionRepository.getInstance()
					.getByUniqueName(sub.getSubscription());

			// calculate achieved data rate
			if (connection == RAN.G4) {
				achievedDataRate = (int) (300 * session.getDataRate());
			} else if (connection == RAN.G3) {
				achievedDataRate = (int) (20 * session.getDataRate());
			} else {
				return false;
			}

			if (achievedDataRate*session.getSeconds() + sub.getDataVolume() > s.getDataVolume()){
                System.out.println("Data volume used up!");
                usedVolume = s.getDataVolume() - sub.getDataVolume();
                sub.setDataVolume(s.getDataVolume());
            } else {
                usedVolume = (int) (achievedDataRate * session.getSeconds());
                sub.setDataVolume(sub.getDataVolume() + (int)(usedVolume/8));
            }

//			// use data volume as long as requested and possible
//			while (usedSec < session.getSeconds()) {
//				//check if data volume is used up
//				if (s.getDataVolume() - sub.getDataVolume() <= 0
//						|| s.getDataVolume()
//						- (sub.getDataVolume() + achievedDataRate) <= 0) {
//					usedUp = true;
//					break;
//				}
//				usedSec += 1;
//				usedVolume += (int)(achievedDataRate / 8);
//				//update total used data volume of subscriber
//				sub.setDataVolume(sub.getDataVolume() + (int)(achievedDataRate/8));
//			}

			print += "Achieved data-rate: " + achievedDataRate + " Mbit/s\n";
			print += "Used data volume: " + (int)(usedVolume/8) + " MB\n";

		}
		if (usedUp) {
			print += "DATA VOLUME is USED UP\n";
		}
		System.out.println(print);
        SubscriberRepository.getInstance().save(sub);
        return true;
	}
}
