package org.hillel.it.ejournal.service.io.commands;

import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.User;

public class About implements Command {
	public static final String ABOUT_COMMAND = "about";

	private static About instance = null;
	private Scanner scanner;

	private About(Scanner scanner) {
		this.scanner = scanner;
	};

	public static About getInstance(Scanner scanner) {
		if ((instance == null) || (instance.scanner != scanner))
			instance = new About(scanner);
		return instance;
	}

	@Override
	public String getDescription() {
		return "show version and information about authors.";
	}

	@Override
	public void execute(User user) {
		System.out
				.println("This program was written by Daniel, Denis and Igor during java course in Hillel-IT school.");
		System.out.println("Version 0.1.1");
	}
}
