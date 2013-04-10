package org.hillel.it.ejournal.ui.commands;

import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.io.Service;

public class About implements Command {

	public static final String ABOUT_COMMAND = "about";
	protected Scanner scanner;
	protected Service service;
	private static About instance = null;

	private About(Scanner scanner, Service service) {
		this.scanner = scanner;
		this.service = service;
	};

	public static About getInstance(Scanner scanner, Service service) {
		if ((instance == null) || (instance.scanner != scanner)
				|| (instance.service != service))
			instance = new About(scanner, service);
		return instance;
	}

	@Override
	public String getDescription() {
		return "show version and information about authors.";
	}

	@Override
	public boolean canExecute(User user) {
		return true;
	}

	@Override
	public void execute(User user) {
		System.out
				.println("This program was written by Daniel, Denis and Igor during java course in Hillel-IT school.");
		System.out.println("Version 0.1.1");
	}
}
