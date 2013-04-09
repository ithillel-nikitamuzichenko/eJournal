package org.hillel.it.ejournal.service.io.commands;

import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.model.entity.Subject;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class AddSubject implements Command {

	public static final String ADDSUBJECT_COMMAND = "add subject";

	private static AddSubject instance = null;
	private Scanner scanner;

	private AddSubject(Scanner scanner) {
		this.scanner = scanner;
	};

	public static AddSubject getInstance(Scanner scanner) {
		if ((instance == null) || (instance.scanner != scanner))
			instance = new AddSubject(scanner);
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
			System.out.print("Name: ");
			String name = scanner.nextLine();
			System.out.print("Short name: ");
			String shortName = scanner.nextLine();
			Subject subject = new Subject(name, shortName);
			int subjectId = DBDAO.getInstance().addSubject(subject, "", user);
			System.out.println(String.format("Subject id=%d was added.",
					subjectId));
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}
}
