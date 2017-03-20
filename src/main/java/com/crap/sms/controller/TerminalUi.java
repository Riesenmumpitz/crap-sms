package com.crap.sms.controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.crap.sms.domain.model.RAN;
import com.crap.sms.domain.model.Terminal;
import com.crap.sms.service.SubscriberService;
import com.crap.sms.service.TerminalService;

public class TerminalUi {

	public static void addTerminal() {
		String name = getValidTerminalName();
		if (name.isEmpty()) {
			return;
		}
		Set<RAN> connections = getValidConnections();
		if (connections.isEmpty()) {
			return;
		}
		boolean active = getBoolean("Is the Terminal active (available for new subscribers)");

		Terminal terminal = new Terminal(name, connections, active);
		if (TerminalService.saveTerminal(terminal)) {
			System.out.println("Created terminal type: " + terminal);
		} else {
			System.out.println("An error occured, could not save your changes.");
		}
	}

	public static void removeTerminal() {
		Terminal terminal = null;
		while (true) {
			terminal = getValidTerminal("Choose the terminal type you want to delete");
			if (terminal == null) {
				return;
			}
			if (SubscriberService.existsSubscriberWithTerminal(terminal.getUniqueName())) {
				System.out.printf(
						"The terminal \"%s\" can not be deleted. There are still subscribers using this terminal.\n",
						terminal.getUniqueName());
			} else {
				break;
			}
		}

		if (TerminalService.removeTerminal(terminal)) {
			System.out.println("Removed terminal type.");
		} else {
			System.out.println("An error occured, could not remove the terminal type.");
		}
	}

	public static void editTerminal() {
		Terminal terminal = getValidTerminal("Choose the terminal type you want to edit");
		if (terminal == null) {
			return;
		}
		boolean active = getBoolean("Is the Terminal active (available for new subscribers)", terminal.isActive());

		Set<RAN> connections = getValidConnections();
		if (connections.isEmpty()) {
			connections = terminal.getConnections();
		}

		Terminal newTerminal = new Terminal(terminal.getUniqueName(), connections, active);
		// TODO edit terminal
		if ((TerminalService.removeTerminal(terminal)) && (TerminalService.saveTerminal(newTerminal))) {
			System.out.println("Changed terminal type: " + newTerminal);
		} else {
			System.out.println("An error occured, could not save your changes.");
		}
	}

	private static Set<RAN> getValidConnections() {
		Set<RAN> result = new TreeSet<RAN>();
		ArrayList<RAN> all = new ArrayList<RAN>();
		all.add(RAN.G2);
		all.add(RAN.G3);
		all.add(RAN.G4);

		while (all.size() > 0) {
			String prompt = "";
			for (int i = 0; i < all.size(); i++) {
				prompt += "(" + i + ") " + RAN.toString(all.get(i)) + "\n";
			}
			String input;
			int index;
			System.out.println(
					"Which RAN types does your terminal type use? (empty if you are finished / no RAN types to abort)");
			System.out.println(prompt);
			input = new Scanner(System.in).nextLine();
			if (input.isEmpty()) {
				break;
			}
			try {
				index = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("Not a number.");
				continue;
			}
			if ((index >= 0) && (index < all.size())) {
				result.add(all.get(index));
				all.remove(index);
			} else {
				System.out.println("Invalid index.");
			}
		}
		return result;
	}

	public static Terminal getValidActiveTerminal(String message) {
		return getValidTerminal(message, TerminalService.getActivTerminalTypesArray());
	}

	public static Terminal getValidTerminal(String message) {
		return getValidTerminal(message, TerminalService.getTerminalTypesArray());
	}

	private static Terminal getValidTerminal(String message, String[] terminalTypes) {
		String prompt = message + ": (empty for abort)\n";
		String[] types = TerminalService.getTerminalTypesArray();
		if (types.length == 0) {
			System.out.println("There are no terminal types in the system.");
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
				return TerminalService.getTerminal(types[index]);
			} else {
				System.out.println("Invalid index.");
			}
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

	private static String getValidTerminalName() {
		String result;
		do {
			System.out.println("Please enter the terminal name (empty value for abort)");
			result = new Scanner(System.in).nextLine();
			if (TerminalService.getTerminal(result) != null) {
				System.out.println("Terminal with the name \"" + result + "\" already exists. Use Edit to change it.");
			} else {
				break;
			}
		} while (true);
		return result;
	}

}
