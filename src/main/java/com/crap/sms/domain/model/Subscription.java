package com.crap.sms.domain.model;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class Subscription {

	private int freeMinutes;
	private int dataVolume;
	private int costPerExtraMinute;
	private int basicFee;

	public Subscription(int freeMinutes, int dataVolume, int costPerExtraMinute, int basicFee) {
		this.freeMinutes = freeMinutes;
		this.dataVolume = dataVolume;
		this.costPerExtraMinute = costPerExtraMinute;
		this.basicFee = basicFee;
	}

	public int getFreeMinutes() {
		return freeMinutes;
	}

	public int getDataVolume() {
		return dataVolume;
	}

	public int getCostPerExtraMinute() {
		return costPerExtraMinute;
	}

	public int getBasicFee() {
		return basicFee;
	}
}
