package com.crap.sms.domain.model;

import java.io.Serializable;

public class Session implements Serializable {
    private int seconds;
    private RAN connection;
    private double dataRate;

    /**
     * Only phone connection
     * @param seconds
     */
    public Session(int seconds) {
        this.seconds = seconds;
        this.connection = null;
    }

    /**
     * Data connection
     * @param seconds
     * @param connection
     */
    public Session(int seconds, RAN connection, double dataRate) {
        this.seconds = seconds;
        this.connection = connection;
        this.dataRate = dataRate;
    }
}
