package org.hillel.it.ejournal.service.io.commands;

import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class AddClass implements Command {

	public static final String ADDCLASS_COMMAND = "add class";

	private static AddClass instance = null;
	private Scanner scanner;

	private AddClass(Scanner scanner) {
		this.scanner = scanner;
	};

	public static AddClass getInstance(Scanner scanner) {
		if ((instance == null) || (instance.scanner != scanner))
			instance = new AddClass(scanner);
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
			System.out.print("Name: ");
			String name = scanner.nextLine();
			System.out.print("Year: ");
			Integer year = Integer.valueOf(scanner.nextLine());
			if (!(year == null) && !(name.length() > 2)) {
				SchoolClass schoolClass = new SchoolClass(name, year);
				int classId = DBDAO.getInstance().addSchoolClass(schoolClass,
						"", user);
				System.out.println(String.format("Class id=%d was added.",
						classId));
			} else {
				System.out.println("Invalid input.");
			}
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}
}
