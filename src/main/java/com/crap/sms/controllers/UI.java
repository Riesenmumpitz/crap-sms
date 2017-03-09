package com.crap.sms.controllers;

import java.util.List;
import java.util.Scanner;

import com.crap.sms.domain.model.Subscriber;
import com.crap.sms.domain.repository.SubscriberRepository;
import com.crap.sms.service.Invoice;

public class UI {

	private static final int NONE = -1;
	private static final int EXIT = 0;
	private static final int SUBSCRIBERS_ADD = 1;
	private static final int SUBSCRIBERS_REMOVE = 2;
	private static final int LIST_ENTRIES = 3;
	private static final int CREATE_SESSION = 4;
	private static final int CREATE_INVOICE = 5;

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
					+ getPrompt(CREATE_INVOICE));
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
			default:
				System.out.println("Invalid input!\n");
			}
		}
	}

	private void createInvoices() {
		Invoice invoice = new Invoice();
		System.out.println(invoice.work());
	}

	private void createNewSession() {
		// TODO Auto-generated method stub
		System.out.println("TODO: Creating new Session");
	}

	private void addSubscriber() {
		// TODO Auto-generated method stub
		System.out.println("TODO: Adding Subscriber");
	}

	private void removeSubscriber() {
		// TODO Auto-generated method stub
		System.out.println("TODO: Removing Subscriber");
	}

	private void listEntries() {
		List<Subscriber> entries = SubscriberRepository.getInstance().getAll();
		String output = SubscriberStringHelper.formatSubscribers(entries);
		System.out.println(output);
	}

}
