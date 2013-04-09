package org.hillel.it.ejournal.service.io.commands;

import java.util.List;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.Student;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class ShowClass implements Command {
	public static final String SHOWCLASS_COMMAND = "show class";

	private static ShowClass instance = null;
	private Scanner scanner;

	private ShowClass(Scanner scanner) {
		this.scanner = scanner;
	};

	public static ShowClass getInstance(Scanner scanner) {
		if ((instance == null) || (instance.scanner != scanner))
			instance = new ShowClass(scanner);
		return instance;
	}

	@Override
	public String getDescription() {
		return "show list of all students studied in class.";
	}

	@Override
	public void execute(User user) {
		if ((user != null)
				&& (user.getRole().intValue() >= Role.TEACHER.intValue())) {
			Integer classId;
			SchoolClass schoolClass;
			do {
				System.out.print("Class id: ");
				classId = scanner.nextInt();
				schoolClass = DBDAO.getInstance().getSchoolClass(classId);
			} while (schoolClass == null);
			List<Student> students = DBDAO.getInstance().getClassList(classId);
			System.out.println(Student.stringLineHeader());
			for (int index = 0; index < students.size(); index++) {
				System.out.println(students.get(index).toStringLine());
			}
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}

}
