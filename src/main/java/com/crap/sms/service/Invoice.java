package com.crap.sms.service;

import java.util.List;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.repository.SubscriberRepository;

public class Invoice {
	private SubscriberRepository sr;
	private List<Subscriber> subscribers;

	public Invoice() {
		sr = SubscriberRepository.getInstance();
		subscribers = sr.getAll();
	}

	public String work(){
		String invoice = "INVOICE\n";
		for(Subscriber s : subscribers){
			invoice+=getSubscriberInvoice(s);
			s.setDataVolume(0);
			s.setUsedMinutes(0);
			sr.save(s);
		}
		return invoice;
	}
	
	private double getCharge(int usedMin, Subscription sub){
		double charge = 0;
		switch(sub){
		case GreenMobileS:
			charge+=8;
			charge+=0.08*usedMin;
			break;
		case GreenMobileM:
			charge+=22;
			if(usedMin>100){
				charge+= 0.06*(usedMin-100);
			}
			break;
		case GreenMobileL:
			charge+=42;
			if(usedMin>150){
				charge+= 0.04*(usedMin-150);
			}
			break;
		}
		return charge;
	}
	

	private String getSubscriberInvoice(Subscriber s) {
		String invoice = "";
		invoice+="\n"+"Name: "+s.getSurName()+", "+s.getForeName()+"\n";
		invoice+="Consumed data volume: "+s.getDataVolume()+"\n";
		invoice+="Consumed minutes: "+s.getUsedMinutes()+"\n";
		invoice+="Total charges: "+getCharge(s.getUsedMinutes(), s.getSubscription())+"\n\n";
		return invoice;
	}
}
