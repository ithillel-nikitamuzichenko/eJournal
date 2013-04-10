package org.hillel.it.ejournal.ui.commands;

import java.util.List;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.Student;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.io.Service;

public class ShowClass implements Command {
	public static final String SHOWCLASS_COMMAND = "show class";
	protected Scanner scanner;
	protected Service service;
	private static ShowClass instance = null;

	private ShowClass(Scanner scanner, Service service) {
		this.scanner = scanner;
		this.service = service;
	};

	public static ShowClass getInstance(Scanner scanner, Service service) {
		if ((instance == null) || (instance.scanner != scanner)
				|| (instance.service != service))
			instance = new ShowClass(scanner, service);
		return instance;
	}

	@Override
	public String getDescription() {
		return "show list of all students studied in class.";
	}

	@Override
	public boolean canExecute(User user) {
		return (user != null)
				&& (user.getRole().intValue() >= Role.TEACHER.intValue());
	}

	@Override
	public void execute(User user) {
		if (canExecute(user)) {
			Integer classId;
			SchoolClass schoolClass;
			do {
				System.out.print("Class id: ");
				classId = scanner.nextInt();
				schoolClass = service.getSchoolClass(classId);
			} while (schoolClass == null);
			List<Student> students = service.getClassList(classId);
			System.out.println(Student.stringHeader());
			for (int index = 0; index < students.size(); index++) {
				System.out.println(students.get(index).toString());
			}
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}

}
