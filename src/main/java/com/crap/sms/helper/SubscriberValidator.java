package com.crap.sms.helper;

public class SubscriberValidator {

	public static final String MMC_MNC = "26242";
	private static final String REGEX_NAME_PATTERN = "[a-zA-Z\\-üäöÜÄÖ]+";
	private static final String IMSI_PATTERN = MMC_MNC + "[\\d]{10}";

	public static boolean isValidForename(String foreName) {
		return foreName.matches(REGEX_NAME_PATTERN);
	}

	public static boolean isValidSurname(String surName) {
		return surName.matches(REGEX_NAME_PATTERN);
	}

	public static boolean isValidImsi(String value) {
		return value.matches(IMSI_PATTERN);
	}
}
