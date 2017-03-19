package com.crap.sms.domain.model;

import java.util.Set;

/**
 * Created by Martin Geßenich on 10.03.2017.
 */
public enum RAN {
    G2,G3,G4;
	
	public static String toString(Set<RAN> rans) {
		String result = "";
		if ((rans == null) || (rans.isEmpty())) {
			return "empty RAN set";
		}
		for (RAN ran : rans) {
			result += toString(ran) + ", ";
		}
		return result.substring(0, result.length() - 2);
	}
		public static String toString(RAN ran) {
				switch (ran) {
		case G2:
			return "G2";
		case G3:
			return "G3";
		case G4:
			return "G4";
		default:
			return "unknown ran type";
		}
	}
}
