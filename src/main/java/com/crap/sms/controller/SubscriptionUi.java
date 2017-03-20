package com.crap.sms.controller;

import java.util.Scanner;

import com.crap.sms.domain.model.Subscription;
import com.crap.sms.service.SubscriberService;
import com.crap.sms.service.SubscriptionService;

public class SubscriptionUi {

	public static void addSubscription() {
		String name = getValidSubscriptionName();
		if (name.isEmpty()) {
			return;
		}
		int freeMinutes = getValidFreeMinutes();
		if (freeMinutes < 0) {
			return;
		}
		int dataVolume = getValidDataVolume();
		if (dataVolume < 0) {
			return;
		}
		int costPerExtraMin = getValidCostPerExtraMin();
		if (costPerExtraMin < 0) {
			return;
		}
		int basicFee = getValidBasicFee();
		if (basicFee < 0) {
			return;
		}
		boolean active = getBoolean("Is the subscription active (available for new subscribers)");

		Subscription subscription = new Subscription(name, freeMinutes, dataVolume, costPerExtraMin, basicFee, active);
		if (SubscriptionService.saveSubscription(subscription)) {
			System.out.println("Created subscription: " + subscription);
		} else {
			System.out.println("An error occured, could not save your changes.");
		}

	}

	public static void removeSubscription() {
		Subscription subscription = null;
		while (true) {
			subscription = getValidSubscription("Choose the subscription type you want to delete");
			if (subscription == null) {
				return;
			}
			if (SubscriberService.existsSubscriberWithSubscription(subscription.getUniqueName())) {
				System.out.printf(
						"The subscription \"%s\" can not be deleted. There are still subscribers using this subscription.\n",
						subscription.getUniqueName());
			} else {
				break;
			}
		}

		if (SubscriptionService.removeSubscription(subscription)) {
			System.out.println("Removed subscription.");
		} else {
			System.out.println("An error occured, could not remove the subscription.");
		}
	}

	public static void editSubscription() {
		Subscription subscription = getValidSubscription("Choose the subscription type you want to edit");
		if (subscription == null) {
			return;
		}
		int freeMinutes = getChangeValidFreeMinutes(subscription.getFreeMinutes());
		int dataVolume = getChangeValidDataVolume(subscription.getDataVolume());
		int costPerExtraMin = getChangeValidCostPerExtraMin(subscription.getCostPerExtraMinute());
		int basicFee = getChangeValidBasicFee(subscription.getBasicFee());
		boolean active = getBoolean("Is the subscription active (available for new subscribers)",
				subscription.isActive());

		Subscription newSubscription = new Subscription(subscription.getUniqueName(), freeMinutes, dataVolume, costPerExtraMin, basicFee,
				active);
		if ((SubscriptionService.removeSubscription(subscription))
				&& (SubscriptionService.saveSubscription(newSubscription))) {
			System.out.println("Change subscription: " + newSubscription);
		} else {
			System.out.println("An error occured, could not save your changes.");
		}
	}

	private static int getChangeValidBasicFee(int oldValue) {
		final int min = 0;
		int result = readIntMin(min,
				String.format(
						"Please enter the new basic fee (in Cent), if you want to keep the current value %d ct press enter.",
						oldValue));
		if (result < min) {
			return oldValue;
		} else {
			return result;
		}
	}

	private static int getChangeValidCostPerExtraMin(int oldValue) {
		final int min = 0;
		int result = readIntMin(min,
				String.format(
						"Please enter the new cost per extra minute (in Cent), if you want to keep the current value %d ct press enter.",
						oldValue));
		if (result < min) {
			return oldValue;
		} else {
			return result;
		}
	}

	private static int getChangeValidDataVolume(int oldValue) {
		final int min = 0;
		int result = readIntMin(min,
				String.format(
						"Please enter the new data volume (in MB), if you want to keep the current value %d MB press enter.",
						oldValue));
		if (result < min) {
			return oldValue;
		} else {
			return result;
		}
	}

	private static boolean getBoolean(String message) {
		while (true) {
			System.out.println(message + ": (Y/N)");
			String input = new Scanner(System.in).nextLine();
			if (input.toUpperCase().equals("Y")) {
				return true;
			} else if (input.toUpperCase().equals("N")) {
				return false;
			} else {
				System.out.println("Invalid input.");
			}
		}
	}

	private static boolean getBoolean(String message, boolean defaultResult) {
		while (true) {
			if (defaultResult) {
				System.out.println(message + ": (Y/N) (empty to keep the current value Yes)");
			} else {
				System.out.println(message + ": (Y/N) (empty to keep the current value No)");
			}
			String input = new Scanner(System.in).nextLine();
			if (input.toUpperCase().equals("Y")) {
				return true;
			} else if (input.toUpperCase().equals("N")) {
				return false;
			} else {
				return defaultResult;
			}
		}
	}

	private static int getChangeValidFreeMinutes(int oldValue) {
		final int min = 0;
		int result = readIntMin(min,
				String.format(
						"Please enter the new number of free minutes, if you want to keep the current value %d min press enter.",
						oldValue));
		if (result < min) {
			return oldValue;
		} else {
			return result;
		}
	}

	public static Subscription getValidActiveSubscription(String message) {
		return getValidSubscription(message, SubscriptionService.getSubscriptionTypesArray());
	}

	private static Subscription getValidSubscription(String message) {
		return getValidSubscription(message, SubscriptionService.getSubscriptionTypesArray());
	}

	private static Subscription getValidSubscription(String message, String[] types) {
		String prompt = message + ": (empty for abort)\n";
		if (types.length == 0) {
			System.out.println("There are no subscriptions in the system.");
			return null;
		}

		for (int i = 0; i < types.length; i++) {
			prompt += "(" + i + ") " + types[i] + "\n";
		}

		String input;
		int index;
		while (true) {
			System.out.println(prompt);
			input = new Scanner(System.in).nextLine();
			if (input.isEmpty()) {
				return null;
			}
			try {
				index = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("Not a number.");
				continue;
			}
			if ((index >= 0) && (index < types.length)) {
				return SubscriptionService.getSubscription(types[index]);
			} else {
				System.out.println("Invalid index.");
			}
		}
	}

	// private static int getChangeValidName(String oldValue) {
	//
	// }

	private static String getValidSubscriptionName() {
		String result;
		do {
			System.out.println("Please enter the subscription name (empty value for abort)");
			result = new Scanner(System.in).nextLine();
			if (SubscriptionService.getSubscription(result) != null) {
				System.out.println(
						"Subscription with the name \"" + result + "\" already exists. Use Edit to change it.");
			} else {
				break;
			}
		} while (true);
		return result;
	}

	private static int getValidBasicFee() {
		return readIntMin(0, "Please enter the basic fee (in Cent): (empty value for abort)");
	}

	private static int getValidCostPerExtraMin() {
		return readIntMin(0, "Please enter the extra cost per minute (in Cent): (empty value for abort)");
	}

	private static int getValidDataVolume() {
		return readIntMin(0, "Please enter the data volume (in MB): (empty value for abort)");
	}

	private static int getValidFreeMinutes() {
		return readIntMin(0, "Please enter the amount of free minutes: (empty value for abort)");
	}

	private static int readIntMin(int min, String message) {
		int result;
		String input;
		do {
			System.out.println(message);
			input = new Scanner(System.in).nextLine();
			// if (input.isEmpty()) {
			// return min - 1;
			// }
			try {
				result = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("\"" + input + "\" is not a number.");
				continue;
			}
			if (result >= min) {
				break;
			} else {
				System.out.println("The number has to be bigger than " + min + ".");
			}
		} while (true);
		return result;
	}

}
