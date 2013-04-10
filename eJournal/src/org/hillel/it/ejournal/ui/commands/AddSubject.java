package org.hillel.it.ejournal.ui.commands;

import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.model.entity.Subject;
import org.hillel.it.ejournal.service.io.Service;
import org.hillel.it.ejournal.ui.Asker;

public class AddSubject implements Command {

	public static final String ADDSUBJECT_COMMAND = "add subject";
	protected Scanner scanner;
	protected Service service;
	private static AddSubject instance = null;
	
	private AddSubject(Scanner scanner, Service service) {
		this.scanner = scanner;
		this.service = service;
	};

	public static AddSubject getInstance(Scanner scanner, Service service) {
		if ((instance == null) || (instance.scanner != scanner)
				|| (instance.service != service))
			instance = new AddSubject(scanner, service);
		return instance;
	}

	@Override
	public String getDescription() {
		return "add new subject into DB.";
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
			String shortName = Asker.askString("Short name: ", false, scanner);
			Subject subject = new Subject(name, shortName);
			int subjectId = service.addSubject(subject, "", user);
			System.out.println(String.format("Subject id=%d was added.",
					subjectId));
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}
}
