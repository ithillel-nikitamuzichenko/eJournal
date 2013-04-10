package org.hillel.it.ejournal.ui.commands;

import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.service.io.Service;
import org.hillel.it.ejournal.ui.Asker;

public class AddClass implements Command {

	public static final String ADDCLASS_COMMAND = "add class";
	protected Scanner scanner;
	protected Service service;
	private static AddClass instance = null;

	private AddClass(Scanner scanner, Service service) {
		this.scanner = scanner;
		this.service = service;
	};

	public static AddClass getInstance(Scanner scanner, Service service) {
		if ((instance == null) || (instance.scanner != scanner)
				|| (instance.service != service))
			instance = new AddClass(scanner, service);
		return instance;
	}

	@Override
	public String getDescription() {
		return "add new class into DB.";
	}

	@Override
	public boolean canExecute(User user) {
		return (user != null)
				&& (user.getRole().intValue() >= Role.ADMIN.intValue());
	}

	@Override
	public void execute(User user) {
		if (canExecute(user)) {
			String name = Asker.askString("Name*: ", true, scanner);
			Integer year = Asker.askInteger("Year*: ", true, 1, 11, scanner);
			SchoolClass schoolClass = new SchoolClass(name, year, service);
			int classId = service.addSchoolClass(schoolClass, "", user);
			System.out
					.println(String.format("Class id=%d was added.", classId));

		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}
}
