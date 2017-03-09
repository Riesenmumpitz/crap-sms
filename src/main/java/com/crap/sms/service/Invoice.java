package com.crap.sms.service;

import java.util.List;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.repository.AbstractRepository;
import com.crap.sms.domain.repository.SubscriberRepository;

public class Invoice {
	private SubscriberRepository sr;
	private List<Subscriber> subscribers;

	public Invoice() {
		sr = SubscriberRepository.getInstance();
		subscribers = sr.getAll();
	}

	private String work(){
		String invoice = "";
		for(Subscriber s : subscribers){
			//TODO reset
			Subscription e = s.getSubscription();
			switch(e){
			case GreenMobileS:
				invoice+=getSubscriberInvoice(s)+"\n";
				break;
			case GreenMobileM:
				break;
			case GreenMobileL:
				break;
			
			}
		}
		return invoice;
	}
	

	private String getSubscriberInvoice(Subscriber s) {
		String invoice = "";
		invoice+="\n"+"Name: "+s.getSurName()+", "+s.getForeName()+"\n";
		invoice+="";
		return "";
	}
}
