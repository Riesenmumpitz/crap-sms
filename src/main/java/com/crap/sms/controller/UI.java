package com.crap.sms.controller;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.model.Subscription;
import com.crap.sms.domain.model.Terminal;
import com.crap.sms.domain.repository.SubscriberRepository;
import com.crap.sms.helper.SubscriberValidator;
import com.crap.sms.service.InvoiceService;
import com.crap.sms.service.SubscriberService;
import com.crap.sms.service.TerminalService;

import java.util.List;
import java.util.Scanner;

public class UI {

	private static final int NONE = -1;
	private static final int EXIT = 0;
	private static final int LIST_ENTRIES = 1;
	private static final int SUBSCRIBERS_ADD = 2;
	private static final int SUBSCRIBERS_REMOVE = 3;
	private static final int CREATE_SESSION = 4;
	private static final int CREATE_INVOICE = 5;
	private static final int SUBSCRIPTIONS_ADD = 7;
	private static final int SUBSCRIPTIONS_REMOVE = 8;
	private static final int SUBSCRIPTIONS_EDIT = 6;
	private static final int TERMINALS_ADD = 10;
	private static final int TERMINALS_REMOVE = 11;
	private static final int TERMINALS_EDIT = 9;

	public UI() {

	}

	private String buildPrompt(int function, String message) {
		return String.format("(%2d) %-36s|\n", function, message);
//				"(" + function + ") " + message + "\n";
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
			return buildPrompt(function, "Add a new subscription type.");
		case SUBSCRIPTIONS_REMOVE:
			return buildPrompt(function, "Remove a subscription type.");
		case SUBSCRIPTIONS_EDIT:
			return buildPrompt(function, "Edit a subscription type.");
		case TERMINALS_ADD:
			return buildPrompt(function, "Add a new terminal type.");
		case TERMINALS_REMOVE:
			return buildPrompt(function, "Remove a terminal type.");
		case TERMINALS_EDIT:
			return buildPrompt(function, "Edit a terminal type.");
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
		String prompt = buildPrompt();
		int action = NONE;
		while (true) {
			System.out.println(prompt);
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
				SubscriptionUi.addSubscription();
				break;
			case SUBSCRIPTIONS_REMOVE:
				SubscriptionUi.removeSubscription();
				break;
			case SUBSCRIPTIONS_EDIT:
				SubscriptionUi.editSubscription();
				break;
			case TERMINALS_ADD:
				TerminalUi.addTerminal();
				break;
			case TERMINALS_REMOVE:
				TerminalUi.removeTerminal();
				break;
			case TERMINALS_EDIT:
				TerminalUi.editTerminal();
				break;
			default:
				System.out.println("Invalid input!\n");
				break;
			}
		}
	}

	private String buildPrompt() {
		final int width = 45;
		final String boxLeft = "| ";			
		final String boxFoot = String.format("+%0" + (width -3) + "d+\n", 0).replace("0", "-");//"+------------------------------------------+\n";

		StringBuilder result = new StringBuilder();
		result.append("Please enter an action:\n");
		result.append(buildBoxHeader("Manage subscribers", width));
		result.append(boxLeft).append(getPrompt(LIST_ENTRIES));
		result.append(boxLeft).append(getPrompt(SUBSCRIBERS_ADD));
		result.append(boxLeft).append(getPrompt(SUBSCRIBERS_REMOVE));
		result.append(boxFoot);
		result.append(buildBoxHeader("Manage subscriptions", width));
		result.append(boxLeft).append(getPrompt(SUBSCRIPTIONS_EDIT));
		result.append(boxLeft).append(getPrompt(SUBSCRIPTIONS_ADD));
		result.append(boxLeft).append(getPrompt(SUBSCRIPTIONS_REMOVE));
		result.append(boxFoot);
		result.append(buildBoxHeader("Manage terminals", width));
		result.append(boxLeft).append(getPrompt(TERMINALS_EDIT));
		result.append(boxLeft).append(getPrompt(TERMINALS_ADD));
		result.append(boxLeft).append(getPrompt(TERMINALS_REMOVE));
		result.append(boxFoot);
		result.append(buildBoxHeader("Manage sessions", width));
		result.append(boxLeft).append(getPrompt(CREATE_SESSION));
		result.append(boxLeft).append(getPrompt(CREATE_INVOICE));
		result.append(boxFoot);
		result.append(boxLeft).append(getPrompt(EXIT));
		result.append(boxFoot);
		return result.toString();
		// return + getPrompt(EXIT) + getPrompt(SUBSCRIBERS_ADD)
		// + getPrompt(SUBSCRIBERS_REMOVE) + getPrompt(LIST_ENTRIES) +
		// getPrompt(CREATE_SESSION)
		// + getPrompt(CREATE_INVOICE) + getPrompt(SUBSCRIPTIONS_ADD) +
		// getPrompt(SUBSCRIPTIONS_REMOVE)
		// + getPrompt(SUBSCRIPTIONS_EDIT);
	}

	private String buildBoxHeader(String boxHeader, int width) {
		int i = width - 6 - boxHeader.length(); 
		String result = String.format("+- %s ", boxHeader);
		result += String.format("%0" + i + "d", 0).replace("0", "-") + "+\n";
		return result;
	}
	
	private void createInvoices() {
		InvoiceService invoice = new InvoiceService();
		System.out.println(invoice.work());
	}

	private void createNewSession() {
		// TEL: seconds
		// DATA: seconds & RAN of the terminal
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
		Terminal terminal = getValidTerminal("Choose a terminal type");
		if (terminal == null) {
			return;
		}
		Subscription subscription = SubscriptionUi.getValidSubscription("Choose a subscription type");
		if (subscription == null) {
			return;
		}

		Subscriber subscriber = SubscriberService.addSubscriber(imsi, terminal, subscription, forename, surname);
		if (subscriber != null) {
			System.out.println("Created subscriber: " + subscriber);
		}
		else {
			System.out.println("Internal problem. Could not add the subscriber.");
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

		if (SubscriberService.removeSubScriber(subscriber)) {
			System.out.println("Removed subscriber.");
		}
		else {
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
            String input = new Scanner(System.in).nextLine();
            if (input == null || input.length() == 0) {
                // Exit point!!!
                System.out.println("Abort.\n");
                return null;
            }
            String imsi = SubscriberValidator.MMC_MNC + input;
            if (SubscriberValidator.isValidImsi(imsi)) {
                // valid imsi
                return imsi;
            } else {
                // invalid imsi
                System.out.println("Invalid MSIN. The MSIN must consist of 10 digits.");
            }
		} while (true);
	}

	private String getValidForename() {
		String result;
		do {
			System.out.println("Please enter the Forename (empty value for abort)");
			result = new Scanner(System.in).nextLine();
			if (!SubscriberValidator.isValidForename(result)) {
				System.out.println("Invalid Forename.");
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

	private Terminal getValidTerminal(String message) {
		String prompt = message + ": (empty for abort)\n";
		String[] types = TerminalService.getTerminalTypesArray();
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
				return TerminalService.getTerminal(types[index]);
			} else {
				System.out.println("Invalid index.");
			}
		}
	}

}
