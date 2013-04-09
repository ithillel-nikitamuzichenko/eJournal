package org.hillel.it.ejournal.service.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.io.commands.*;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class ConsoleService {

	private static final String HELP_COMMAND = "HELP";
	private static final String EXIT_COMMAND = "EXIT";
	private static final String LOGIN_COMMAND = "LOGIN";

	private Map<String, Command> commands = new HashMap<String, Command>();
	private int exitFlag = 0;

	private Scanner scanner = new Scanner(System.in);

	private User user = null;

	public ConsoleService() {
		System.out.println("Java command line eJournal. Type 'help' for help.");
		
		commands.put(About.ABOUT_COMMAND, About.getInstance(scanner));
		commands.put(AddClass.ADDCLASS_COMMAND, AddClass.getInstance(scanner));
		commands.put(AddStudent.ADDSTUDENT_COMMAND,
				AddStudent.getInstance(scanner));
		commands.put(ShowClasses.SHOWCLASSES_COMMAND,
				ShowClasses.getInstance(scanner));
		
		do {
			interpretCommand(getCommand());
		} while (exitFlag == 0);
	}

	private String getCommand() {
		String command = null;
		do {
			if (user == null)
				System.out.print(">");
			else
				System.out.print(String.format("%s>", user.getLogin()));
			command = scanner.nextLine();
		} while (command.isEmpty());
		return command;
	}

	public void interpretCommand(String command) {
		switch (command.toUpperCase()) {
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
			Command � = commands.get(command);
			if (� == null) {
				System.out.println("Invalid command");
			} else {
				�.execute(user);
			}
		}
	}

	private void showHelp() {
		System.out.println(String.format("%s - %s", HELP_COMMAND, "show this help."));
		System.out.println(String.format("%s - %s", LOGIN_COMMAND, "begin autorization process."));
		System.out.println(String.format("%s - %s", EXIT_COMMAND, "end programm execution."));
		for (Map.Entry<String, Command> entry : commands.entrySet()) {
			String key = entry.getKey().toUpperCase();
			String value = entry.getValue().getDescription();
			System.out.println(String.format("%s - %s", key, value));
		}

	}

	private void makeLogin() {
		System.out.print("Login: ");
		String login = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		user = DBDAO.getInstance().getUser(login, password);
		if (user == null)
			System.out.println("Access denided.");
	}
}