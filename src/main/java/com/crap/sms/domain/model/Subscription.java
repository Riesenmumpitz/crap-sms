package com.crap.sms.domain.model;

import java.io.Serializable;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class Subscription implements Serializable{

	private String uniqueName;
	private int freeMinutes;
	private int dataVolume;
	private int costPerExtraMinute;
	private int basicFee;

	public Subscription(String uniqueName, int freeMinutes, int dataVolume, int costPerExtraMinute, int basicFee) {
		this.uniqueName = uniqueName;
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

	public String getUniqueName() {
		return uniqueName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Subscription that = (Subscription) o;

		if (freeMinutes != that.freeMinutes) return false;
		if (dataVolume != that.dataVolume) return false;
		if (costPerExtraMinute != that.costPerExtraMinute) return false;
		return basicFee == that.basicFee;
	}
}
