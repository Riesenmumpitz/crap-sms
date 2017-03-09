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
}
