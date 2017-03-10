package com.crap.sms.service;

import java.util.List;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.repository.SubscriberRepository;

public class InvoiceService {
	private SubscriberRepository sr;
	private List<Subscriber> subscribers;

	public InvoiceService() {
		//fetch all subscribers from data storage
		sr = SubscriberRepository.getInstance();
		subscribers = sr.getAll();
	}
/**
 * Method to get Invoice for all subscribers
 * @return String 
 */
	public String work(){
		String invoice = "INVOICE\n";
		for(Subscriber s : subscribers){
			//add the invoice for each subscriber
			invoice+=getSubscriberInvoice(s);
			//reset the used data volume and minutes
			s.setDataVolume(0);
			s.setUsedMinutes(0);
			//save changes to data storage
			sr.save(s);
		}
		return invoice;
	}
	
	/**
	 * 
	 * @param usedMin
	 * @param sub
	 * @return total charge for subscriber
	 */
	private double getCharge(int usedMin, Subscription sub){
		double charge = 0;
		// add basic fee
		charge += sub.getBasicFee();
		//charged used extra minutes
		if(usedMin>sub.getFreeMinutes()){
			charge+=sub.getCostPerExtraMinute()*(usedMin-sub.getFreeMinutes());
		}
		return charge;
	}
	
/**
 * 
 * @param s
 * @return String with Invoice for a single subscriber
 */
	private String getSubscriberInvoice(Subscriber s) {
		String invoice = "";
		invoice+="\n"+"Name: "+s.getSurName()+", "+s.getForeName()+"\n";
		invoice+="Consumed data volume: "+s.getDataVolume()+"\n";
		invoice+="Consumed minutes: "+s.getUsedMinutes()+"\n";
		invoice+="Total charges: "+getCharge(s.getUsedMinutes(), s.getSubscription())+"\n\n";
		return invoice;
	}
}
