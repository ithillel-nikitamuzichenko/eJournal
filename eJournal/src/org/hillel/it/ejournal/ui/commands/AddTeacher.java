package org.hillel.it.ejournal.ui.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.model.entity.Sex;
import org.hillel.it.ejournal.model.entity.Teacher;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.io.Service;
import org.hillel.it.ejournal.ui.Asker;

public class AddTeacher implements Command {

	public static final String ADDTEACHER_COMMAND = "add teacher";
	protected Scanner scanner;
	protected Service service;
	private static AddTeacher instance = null;

	private AddTeacher(Scanner scanner, Service service) {
		this.scanner = scanner;
		this.service = service;
	};

	public static AddTeacher getInstance(Scanner scanner, Service service) {
		if ((instance == null) || (instance.scanner != scanner)
				|| (instance.service != service))
			instance = new AddTeacher(scanner, service);
		return instance;
	}

	@Override
	public String getDescription() {
		return "add new teacher into DB.";
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
			String surname = Asker.askString("Surname*: ", true, scanner);
			Date birthDate = Asker.askDate("Birthday(dd.mm.yyyy): ",
					new SimpleDateFormat("dd.mm.yyyy"), true, scanner);
			Sex sex = Sex.getSex(Asker.askInteger("Sex(0-Female, 1-Male): ",
					true, 0, 1, scanner));
			String login = Asker.askString("Login*: ", true, scanner);
			String password = Asker.askString("Password*: ", true, scanner);
			Teacher teacher = new Teacher(name, surname, birthDate, sex, login,
					password);
			int teacherId = service.addTeacher(teacher, "", user);
			System.out.println(String.format("Teacher id=%d was added.",
					teacherId));
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}
}
