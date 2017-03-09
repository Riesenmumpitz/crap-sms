package com.crap.sms.controllers;

import java.util.Scanner;

import com.crap.sms.service.UserManagement;

public class Login {
	public static boolean login() {
		// handle login or creation of users
		Scanner sc = new Scanner(System.in);

		if (false) {
			setNewMasterpassword();
		} else {
			System.out.println("Please enter the master password!");
			int hashedMasterPassword = (sc.nextLine()).hashCode();
		}
		System.out.println("Access granted");
		boolean active = true;
		while (active) {
			System.out.println("What do you want to do next?\n" + " (0) end Application\n" + " (1) add a new user\n"
					+ " (2) login\n");
			String input = sc.nextLine();
			if (!input.equals("0") && !input.equals("1") && !input.equals("2"))
				System.out.println("Input not valid");
			else {
				if (input.equals(0))
					active = false;
				if (input.equals(1))
					createNewUser();
				if (input.equals(2)) {
					System.out.println("Please enter your user name!");
					String userName = sc.nextLine();
					System.out.println("Please enter your password!");
					int hashedPassword = (sc.nextLine()).hashCode();
					UserManagement userManagement= new UserManagement();
				}
			}
		}
		return false;
	}

	public static void createNewUser() {

	}

	public static void setNewMasterpassword() {
		Scanner sc = new Scanner(System.in);
		String newMasterPassword = null;
		boolean isCorrect = false;
		while (!isCorrect) {
			System.out.println("Please set a master password! It should have between 8 and 32 characters.");
			newMasterPassword = sc.nextLine();

			if (newMasterPassword.length() < 8)
				System.out.println("Your password is to short, you have to do it again.");
			else if (newMasterPassword.length() > 32)
				System.out.println("Your password is to long, you have to do it again.");
			else
				isCorrect = true;
		}
		int hashedMasterPassword = newMasterPassword.hashCode();

		System.out.println("The master password has been set.");
	}

}
