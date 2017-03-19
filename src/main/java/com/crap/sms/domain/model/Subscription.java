package com.crap.sms.domain.model;

import java.io.Serializable;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class Subscription implements Serializable {

	private String uniqueName;
	private int freeMinutes;
	private int dataVolume;
	private int costPerExtraMinute;
	private int basicFee;

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public void setFreeMinutes(int freeMinutes) {
		this.freeMinutes = freeMinutes;
	}

	public void setDataVolume(int dataVolume) {
		this.dataVolume = dataVolume;
	}

	public void setCostPerExtraMinute(int costPerExtraMinute) {
		this.costPerExtraMinute = costPerExtraMinute;
	}

	public void setBasicFee(int basicFee) {
		this.basicFee = basicFee;
	}

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
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Subscription that = (Subscription) o;

		if (freeMinutes != that.freeMinutes)
			return false;
		if (dataVolume != that.dataVolume)
			return false;
		if (costPerExtraMinute != that.costPerExtraMinute)
			return false;
		return basicFee == that.basicFee;
	}

	@Override
	public String toString() {
		return String.format("%s: Free Min: %d; Data Volume: %d MB; Cost per min: %d ct; Basic fee: %d ct",
				getUniqueName(), freeMinutes, dataVolume, costPerExtraMinute, basicFee);
	}
}
