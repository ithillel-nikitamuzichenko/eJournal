package org.hillel.it.ejournal.service.io;

import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.User;

public class UserService implements Service{
	private static final String WHOAMI_COMMAND = "whoami";

	protected User user;
	protected Scanner scanner;

	public UserService(User user, Scanner scanner) {
		super();
		this.user = user;
		this.scanner = scanner;
		commands.put(WHOAMI_COMMAND, "shows information about user.");
	}

	public void interpretCommand(String command) {
		if (commands.containsKey(command))
			execute((command));
		else
			System.out.println("Invalid command");
	}

	private void execute(String command) {
		switch (command.toLowerCase()) {
		case WHOAMI_COMMAND:
			whoami();
			break;
		}
	}

	public User getUser() {
		return user;
	}

	private void whoami() {
		if (user == null)
			System.out.println("anonymous");
		else
			System.out.println(user.toString());
	}
}
