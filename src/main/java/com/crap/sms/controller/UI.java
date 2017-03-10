package com.crap.sms.controller;

import java.util.List;
import java.util.Scanner;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.model.Terminal;
import com.crap.sms.domain.repository.SubscriberRepository;
import com.crap.sms.helper.SubscriberValidator;
import com.crap.sms.service.InvoiceService;
import com.crap.sms.service.SubscriberService;
import com.crap.sms.service.SubscriptionService;

public class UI {

	private static final int NONE = -1;
	private static final int EXIT = 0;
	private static final int SUBSCRIBERS_ADD = 1;
	private static final int SUBSCRIBERS_REMOVE = 2;
	private static final int LIST_ENTRIES = 3;
	private static final int CREATE_SESSION = 4;
	private static final int CREATE_INVOICE = 5;
	private static final int SUBSCRIPTIONS_ADD = 6;
	private static final int SUBSCRIPTIONS_REMOVE = 7;
	private static final int SUBSCRIPTIONS_EDIT = 8;

	public UI() {

	}

	private String buildPrompt(int function, String message) {
		return " (" + function + ") " + message + "\n";
	}

	private String getPrompt(int function) {
		switch (function) {
		case 42:
			return "";
		case EXIT:
			return buildPrompt(function, "Quit application.");
		case SUBSCRIBERS_ADD:
			return buildPrompt(function, "Add new subscriber.");
		case SUBSCRIBERS_REMOVE:
			return buildPrompt(function, "Remove subscriber.");
		case LIST_ENTRIES:
			return buildPrompt(function, "List all subscribers.");
		case CREATE_SESSION:
			return buildPrompt(function, "Create new session.");
		case CREATE_INVOICE:
			return buildPrompt(function, "Send invoices for all subscribers.");
		case SUBSCRIPTIONS_ADD:
			return buildPrompt(function, "Add new subscription type.");
		case SUBSCRIPTIONS_REMOVE:
			return buildPrompt(function, "Remove a subscription type.");
		case SUBSCRIPTIONS_EDIT:
			return buildPrompt(function, "Edit a subscription type.");
		default:
			return "";
		}
	}

	private int readAction() {
		String input = new Scanner(System.in).nextLine();
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			return NONE;
		}
	}

	/*
	 * main routine, waiting for User Input & distributing Work
	 */
	public void work() {
		int action = NONE;
		while (true) {
			System.out.println("Please enter an action:\n" + getPrompt(EXIT) + getPrompt(SUBSCRIBERS_ADD)
					+ getPrompt(SUBSCRIBERS_REMOVE) + getPrompt(LIST_ENTRIES) + getPrompt(CREATE_SESSION)
					+ getPrompt(CREATE_INVOICE) + getPrompt(SUBSCRIPTIONS_ADD) + getPrompt(SUBSCRIPTIONS_REMOVE)
					+ getPrompt(SUBSCRIPTIONS_EDIT));
			action = readAction();
			switch (action) {
			case EXIT:
				System.out.println("Terminating.\n");
				return;
			case SUBSCRIBERS_ADD:
				addSubscriber();
				break;
			case SUBSCRIBERS_REMOVE:
				removeSubscriber();
				break;
			case LIST_ENTRIES:
				listEntries();
				break;
			case CREATE_SESSION:
				createNewSession();
				break;
			case CREATE_INVOICE:
				createInvoices();
				break;
			case SUBSCRIPTIONS_ADD:
				addNewSubscription();
				break;
			case SUBSCRIPTIONS_REMOVE:
				removeSubscription();
				break;
			case SUBSCRIPTIONS_EDIT:
				editSubscription();
				break;
			default:
				System.out.println("Invalid input!\n");
				break;
			}
		}
	}

	private void editSubscription() {
		Subscription subscription = getValidSubscription();
		String name = getValidSubscriptionName();
		if (name.isEmpty()) {
			return;
		}
		int freeMinutes = getValidFreeMinutes();
		int dataVolume= getValidDataVolume();
		int costPerExtraMin = getValidCostPerExtraMin();
		int basicFee= getValidBasicFee();
		//TODO edit subscriber
	}

	private void removeSubscription() {
		Subscription subscription = getValidSubscription();
		if (subscription == null) {
			return;
		}
		// TODO remove subscription
		// if (SubscriptionService.)
	}

	private void addNewSubscription() {
		String name = getValidSubscriptionName();
		if (name.isEmpty()) {
			return;
		}
		int freeMinutes = getValidFreeMinutes();
		int dataVolume= getValidDataVolume();
		int costPerExtraMin = getValidCostPerExtraMin();
		int basicFee= getValidBasicFee();
		
		//TODO create subscription
	}

	private void createInvoices() {
		InvoiceService invoice = new InvoiceService();
		System.out.println(invoice.work());
	}

	private void createNewSession() {
		// TODO Auto-generated method stub
		System.out.println("TODO: Creating new Session");
	}

	private void addSubscriber() {
		String imsi = getValidImsi();
		if ((imsi == null) || (imsi.isEmpty())) {
			return;
		}
		String forename = getValidForename();
		if ((forename == null) || (forename.isEmpty())) {
			return;
		}
		String surname = getValidSurname();
		if ((surname == null) || (surname.isEmpty())) {
			return;
		}
		Terminal terminal = getValidTerminal();
		Subscription subscription = getValidSubscription();

		Subscriber subscriber = SubscriberService.addSubscriber(imsi, terminal, subscription, forename, surname);
		if (subscriber == null) {
			System.out.println("Internal problem. Could not save the subscriber.");
		}
	}

	private void removeSubscriber() {
		Subscriber subscriber;
		do {
			String imsi = getValidImsi();
			if ((imsi == null) || (imsi.isEmpty())) {
				return;
			}
			subscriber = SubscriberService.findSubscriberBy(imsi);
			if (subscriber != null) {
				break;
			}
			System.out.println("Could not find subscriber with IMSI \"" + imsi + "\"");
		} while (true);

		if (!SubscriberService.removeSubScriber(subscriber)) {
			System.out.println("Internal error. Could not remove the subscriber.");
		}
	}

	private void listEntries() {
		List<Subscriber> entries = SubscriberRepository.getInstance().getAll();
		String output = SubscriberStringHelper.formatSubscribers(entries);
		System.out.println(output);
	}

	private String getValidImsi() {
		String result;
		do {
			System.out.println("Please enter the MSIN (empty value for abort)");
			result = SubscriberValidator.MMC_MNC + new Scanner(System.in).nextLine();
			if (!SubscriberValidator.isValidImsi(result)) {
				System.out.println("Invalid MSIN. The MSIN must consist of 10 digits.");
			} else {
				break;
			}
		} while (true);
		return result;
	}

	private String getValidForename() {
		String result;
		do {
			System.out.println("Please enter the Forename (empty value for abort)");
			result = new Scanner(System.in).nextLine();
			if (!SubscriberValidator.isValidForename(result)) {
				System.out.println("Invalid Forename. The forename must not be empty.");
			} else {
				break;
			}
		} while (true);
		return result;
	}

	private String getValidSurname() {
		String result;
		do {
			System.out.println("Please enter the Surname (empty value for abort)");
			result = new Scanner(System.in).nextLine();
			if (!SubscriberValidator.isValidSurname(result)) {
				System.out.println("Invalid Surname. The surname must not be empty.");
			} else {
				break;
			}
		} while (true);
		return result;
	}

	private Subscription getValidSubscription() {
		Subscription result;
		String input;
		int index;
		do {
			System.out.println("Choose a subscription type: (empty for abort)");
			String[] types = SubscriptionService.getSubscriptionTypesArray();
			for (int i = 0; i < types.length; i++) {
				System.out.println("(" + i + ") " + types[i]);
			}
			input = new Scanner(System.in).nextLine();
			if (input.isEmpty()) {
				return null;
			}
			try {
				index = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("Not a number!");
				continue;
			}
			if ((index >= 0) && (index < types.length)) {
				result = null; //TODO subscriptionService.getSubscription(types[i]);
				break;
			}
		} while (true);

		return result;
	}

	private Terminal getValidTerminal() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getValidSubscriptionName() {
		String result;
		do {
			System.out.println("Please enter the subscription name (empty value for abort)");
			result = new Scanner(System.in).nextLine();
			if (!result.isEmpty()) {
				System.out.println("Invalid subscription name. The subscription name must not be empty.");
			} else {
				break;
			}
		} while (true);
		return result;
	}

	private int getValidBasicFee() {
		return readIntMin(0, "Please enter the basic fee (in Cent):");
	}

	private int getValidCostPerExtraMin() {
		return readIntMin(0, "Please enter the extra cost per minute (in Cent):");
	}

	private int getValidDataVolume() {
		return readIntMin(0, "Please enter the data volume (in Mbit/s):");
	}

	private int getValidFreeMinutes() {
		return readIntMin(0, "Please enter the amount of free minutes:");
	}

	private int readIntMin(int min, String message) {
		int result;
		String input;
		do {
			System.out.println(message);
			input = new Scanner(System.in).nextLine();
			try {
				result = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("\"" + input + "\" is not a number.");
				continue;
			}
			if (result >= min) {
				break;
			}
			else {
				System.out.println("The number has to be bigger than " + min + ".");
			}
		} while (true);
		return result;
	}

}
