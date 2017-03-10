package com.crap.sms.domain.model;

import java.math.BigInteger;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class Subscriber {
    private BigInteger IMSI;
    private Terminal terminal;
    private Subscription subscription;
    private String foreName;
    private String surName;
    private Integer usedMinutes;
    private Integer dataVolume;
    private Integer charge;

    public Subscriber(BigInteger IMSI, Terminal terminal, Subscription subscription, String foreName, String surName, Integer freeMinutes, Integer dataVolume) {
        this.IMSI = IMSI;
        this.terminal = terminal;
        this.subscription = subscription;
        this.foreName = foreName;
        this.surName = surName;
        this.usedMinutes = freeMinutes;
        this.dataVolume = dataVolume;
    }

    public BigInteger getIMSI() {
        return IMSI;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
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
    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }
}
