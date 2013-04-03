package org.hillel.it.ejournal.service.io;

import java.util.Map;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class MainService implements Service{
	private static final String HELP_COMMAND = "help";
	private static final String EXIT_COMMAND = "exit";
	private static final String ABOUT_COMMAND = "about";
	private static final String LOGIN_COMMAND = "login";

	private int exitFlag = 0;

	private Scanner scanner = new Scanner(System.in);

	private UserService service = new UserService(null, scanner);

	public MainService() {
		System.out.println("Java command line eJournal. Type 'help' for help.");
		commands.put(LOGIN_COMMAND, "begin autorization process.");
		commands.put(HELP_COMMAND, "show this help.");
		commands.put(EXIT_COMMAND, "end programm execution.");
		commands.put(ABOUT_COMMAND, "show version and information about authors.");
		do {
			interpretCommand(getCommand());
		} while (exitFlag == 0);
	}

	private String getCommand() {
		String command = null;
		do {
			if (service.getUser() == null)
				System.out.print(">");
			else
				System.out.print(String.format("%s>", service.getUser()
						.getLogin()));
			command = scanner.nextLine();
		} while (command.isEmpty());
		return command;
	}

	public void interpretCommand(String command) {
		switch (command.toLowerCase()) {
		case ABOUT_COMMAND:
			showAbout();
			break;
		case EXIT_COMMAND:
			exitFlag = 1;
			break;
		case HELP_COMMAND:
			showHelp();
			break;
		case LOGIN_COMMAND:
			makeLogin();
			break;
		default:
			service.interpretCommand(command);
		}
	}

	private void showHelp() {
		for (Map.Entry<String, String> entry : commands.entrySet()) {
		    String key = entry.getKey().toUpperCase();
		    Object value = entry.getValue();
		    System.out.println(String.format("%s - %s", key, value));
		}
	}

	private void showAbout() {
		System.out
				.println("This program was written by Daniel, Denis and Igor during java course in Hillel-IT school.");
		System.out.println("Version 0.1");
	}

	private void makeLogin() {
		System.out.print("Login: ");
		String login = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		User user = DBDAO.getInstance().getUser(login, password);
		if (user == null)
			System.out.println("Access denided.");
		else {
			switch (user.getRole()) {
			case ADMIN:
				service = new AdminService(user, scanner);
				break;
			default:
				service = new UserService(user, scanner);
				break;
			}
		}

	}
}
