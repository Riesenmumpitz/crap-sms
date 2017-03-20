
package com.crap.sms.controller;

import java.io.Console;
import java.util.Scanner;

import com.crap.sms.domain.model.User;
import com.crap.sms.domain.repository.ConfigurationRepository;
import com.crap.sms.service.UserManagementService;
import com.crap.sms.domain.model.Configuration;

public class Login {
	public static boolean login() {
		// handle login or creation of users
		Scanner sc = new Scanner(System.in);

		if (ConfigurationRepository.getInstance().getConfig(Configuration.MASTERPASSWORD) == null) {
			setNewMasterpassword();
		} else {
			boolean isCorrect = false;
			int wrongTries = 0;
			while (!isCorrect) {
				String hashedMasterPassword = readInvisible("Please enter the master password!").hashCode() + "";
				if (hashedMasterPassword
						.equals(ConfigurationRepository.getInstance().getConfig(Configuration.MASTERPASSWORD))) {
					isCorrect = true;
				} else {
					System.out.println("Master password wrong, Access denied");
					wrongTries++;
					if (wrongTries > 5) {
						System.out.println("You entered too many wrong passwords. The Application will shut down.");
						return false;
					}
				}
			}
		}
		System.out.println("Access granted");
		boolean active = true;
		while (active) {
			System.out.println("What do you want to do next?\n" + " (0) Quit application.\n" + " (1) Add new user.\n"
					+ " (2) Login to your user account.\n");
			String input = sc.nextLine();
			if (!input.equals("0") && !input.equals("1") && !input.equals("2"))
				System.out.println("Input not valid");
			else {
				if (input.equals("0")) {
					System.out.println("Terminating.");
					active = false;
				}
				if (input.equals("1"))
					createNewUser();
				if (input.equals("2")) {
					System.out.println("Please enter your user name!");
					String userName = sc.nextLine();
					int hashedPassword = readInvisible("Please enter your password!").hashCode();
					User user = UserManagementService.findUser(userName);
					if (user == null || hashedPassword != user.getPasswordHash()) {
						System.out.println("Access denied. Wrong name or password.");
					} else {
						System.out.println("Access granted. Welcome, " + userName + "!");
						return true;
					}
				}
			}
		}
		return false;
	}

	private static String readInvisible(String message) {
		Console console = System.console();
		if (console != null) {
			char[] input = console.readPassword(message);
			return String.copyValueOf(input);
		}
		else {
			/// if running in eclipse
			System.out.println(message);
			return new Scanner(System.in).nextLine();			
		}
	}

	public static void createNewUser() {
		Scanner sc = new Scanner(System.in);
		boolean nameAvailable = false;
		String userName = "";
		while (!nameAvailable) {
			System.out.println("Please enter a new user name!");
			userName = sc.nextLine();
			if (UserManagementService.findUser(userName) != null) {
				System.out.println("User name not available.");
			} else {
				nameAvailable = true;
			}
		}
		boolean isCorrect = false;
		String password = "";
		while (!isCorrect) {
			password = readInvisible("Please enter a password! It should have between 8 and 32 characters.");
			if (password.length() < 8)
				System.out.println("Your password is to short, you have to do it again.");
			else if (password.length() > 32)
				System.out.println("Your password is to long, you have to do it again.");
			else {
				String confirm = readInvisible("Please confirm your password!");
				if (confirm.equals(password)) {
					isCorrect = true;
				} else {
					System.out.println("The passwords do not match!");
				}
			}
		}
		int hashedPassword = password.hashCode();
		if (UserManagementService.createUser(userName, hashedPassword) == null)
			System.out.println("Creation of user failed for unknown reasons, please contact your admin!");
		else
			System.out.println("User successfully created.");
	}

	public static void setNewMasterpassword() {
		String newMasterPassword = null;
		boolean isCorrect = false;
		while (!isCorrect) {
			newMasterPassword = readInvisible(
					"Please set a master password! It should have between 8 and 32 characters.");

			if (newMasterPassword.length() < 8)
				System.out.println("Your password is to short, you have to do it again.");
			else if (newMasterPassword.length() > 32)
				System.out.println("Your password is to long, you have to do it again.");
			else {
				String confirm = readInvisible("Please confirm your master password!");
				if (confirm.equals(newMasterPassword)) {
					isCorrect = true;
				} else {
					System.out.println("The passwords do not match!");
				}
			}
		}
		String hashedMasterPassword = "" + newMasterPassword.hashCode();
		ConfigurationRepository configRepository = ConfigurationRepository.getInstance();
		configRepository.setConfig(Configuration.MASTERPASSWORD, hashedMasterPassword);
		System.out.println("The master password has been set.");
	}
}