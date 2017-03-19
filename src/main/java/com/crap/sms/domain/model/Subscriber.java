package com.crap.sms.domain.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class Subscriber implements Serializable {
    private String IMSI;
    private String terminal;
    private String subscription;
    private String foreName;
    private String surName;
    private Integer usedMinutes;
    private Integer dataVolume;

    private Subscriber(String IMSI, String terminal, String subscription, String foreName, String surName, Integer usedMinutes, Integer dataVolume) {
        this.IMSI = IMSI;
        this.terminal = terminal;
        this.subscription = subscription;
        this.foreName = foreName;
        this.surName = surName;
        this.usedMinutes = usedMinutes;
        this.dataVolume = dataVolume;
    }

    public Subscriber (String IMSI, String terminal, String subscription, String foreName, String surName){
        this(IMSI, terminal, subscription, foreName, surName, 0, 0);
    }

    public String getIMSI() {
        return IMSI;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getUsedMinutes() {
        return usedMinutes;
    }

    public void setUsedMinutes(Integer usedMinutes) {
        this.usedMinutes = usedMinutes;
    }

    public Integer getDataVolume() {
        return dataVolume;
    }

    public void setDataVolume(Integer dataVolume) {
        this.dataVolume = dataVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscriber that = (Subscriber) o;

        if (IMSI != null ? !IMSI.equals(that.IMSI) : that.IMSI != null) return false;
        if (terminal != null ? !terminal.equals(that.terminal) : that.terminal != null) return false;
        if (subscription != null ? !subscription.equals(that.subscription) : that.subscription != null) return false;
        if (foreName != null ? !foreName.equals(that.foreName) : that.foreName != null) return false;
        if (surName != null ? !surName.equals(that.surName) : that.surName != null) return false;
        if (usedMinutes != null ? !usedMinutes.equals(that.usedMinutes) : that.usedMinutes != null) return false;
        return dataVolume != null ? dataVolume.equals(that.dataVolume) : that.dataVolume == null;
    }
    
    @Override
    public String toString() {
    	return String.format("%s %s (%s): subscription: %s; terminal: %s", foreName, surName, IMSI, subscription, terminal);
    }
}
