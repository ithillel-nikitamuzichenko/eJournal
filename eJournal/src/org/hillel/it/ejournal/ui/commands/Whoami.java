package org.hillel.it.ejournal.ui.commands;

import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.io.Service;

public class Whoami implements Command {

	public static final String WHOAMI_COMMAND = "whoami";
	protected Scanner scanner;
	protected Service service;
	private static Whoami instance = null;

	private Whoami(Scanner scanner, Service service) {
		this.scanner = scanner;
		this.service = service;
	};

	public static Whoami getInstance(Scanner scanner, Service service) {
		if ((instance == null) || (instance.scanner != scanner)
				|| (instance.service != service))
			instance = new Whoami(scanner, service);
		return instance;
	}

	@Override
	public String getDescription() {
		return "show information about the current user.";
	}

	@Override
	public boolean canExecute(User user) {
		return true;
	}

	@Override
	public void execute(User user) {
		if (user == null) {
			System.out.println("anonymous");
		} else {
			System.out.println(user.getInfoString());
		}
	}
}
