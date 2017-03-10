package com.crap.sms.helper;

public class SubscriptionValidator {

    public boolean isValidFreeMinutes(int minutes) {
        return between0andinfi(minutes);
    }

    public boolean isValidDataVolume(int dataVolume) {
        return between0andinfi(dataVolume);
    }

    public boolean isValidcostPerExtraMinute(int minutes) {
        return between0andinfi(minutes);
    }

    public boolean isValidbasicFee(int fee) {
        return between0andinfi(fee);
    }

    private boolean between0andinfi(int value) {
        return value >= 0 && value < Integer.MAX_VALUE;
    }

}
