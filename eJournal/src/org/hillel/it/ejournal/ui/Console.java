package org.hillel.it.ejournal.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.io.Service;
import org.hillel.it.ejournal.service.persistance.dao.DAO;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;
import org.hillel.it.ejournal.ui.commands.*;

public class Console {

	private static final String HELP_COMMAND = "HELP";
	private static final String EXIT_COMMAND = "EXIT";
	private static final String LOGIN_COMMAND = "LOGIN";

	private Map<String, Command> commands = new HashMap<String, Command>();
	private int exitFlag = 0;
	private Scanner scanner = new Scanner(System.in);
	private User user = null;
	private DAO dao = DBDAO.getInstance();
	private Service service = Service.getInstance(dao);

	public Console() {
		System.out.println("Java command line eJournal. Type 'help' for help.");

		commands.put(About.ABOUT_COMMAND, 
				About.getInstance(scanner, service));
		commands.put(AddClass.ADDCLASS_COMMAND,
				AddClass.getInstance(scanner, service));
		commands.put(AddStudent.ADDSTUDENT_COMMAND,
				AddStudent.getInstance(scanner, service));
		commands.put(ShowClasses.SHOWCLASSES_COMMAND,
				ShowClasses.getInstance(scanner, service));
		commands.put(ShowClass.SHOWCLASS_COMMAND,
				ShowClass.getInstance(scanner, service));
		commands.put(AddSubject.ADDSUBJECT_COMMAND,
				AddSubject.getInstance(scanner, service));
		commands.put(AddTeacher.ADDTEACHER_COMMAND,
				AddTeacher.getInstance(scanner, service));
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
			Command c = commands.get(command);
			if (c == null) {
				System.out.println("Invalid command");
			} else {
				c.execute(user);
			}
		}
	}

	private void showHelp() {
		System.out.println(String.format("%s - %s", HELP_COMMAND,
				"show this help."));
		System.out.println(String.format("%s - %s", LOGIN_COMMAND,
				"begin autorization process."));
		System.out.println(String.format("%s - %s", EXIT_COMMAND,
				"end programm execution."));
		for (Map.Entry<String, Command> entry : commands.entrySet()) {
			if (entry.getValue().canExecute(user)) {
				String key = entry.getKey().toUpperCase();
				String value = entry.getValue().getDescription();
				System.out.println(String.format("%s - %s", key, value));
			}
		}

	}

	private void makeLogin() {
		System.out.print("Login: ");
		String login = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		user = dao.getUser(login, password);
		if (user == null)
			System.out.println("Access denided.");
	}
}
