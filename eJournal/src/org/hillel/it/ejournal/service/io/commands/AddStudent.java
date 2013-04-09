package org.hillel.it.ejournal.service.io.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.Sex;
import org.hillel.it.ejournal.model.entity.Student;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.service.io.Asker;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class AddStudent implements Command {

	public static final String ADDSTUDENT_COMMAND = "add student";

	private static AddStudent instance = null;
	private Scanner scanner;

	private AddStudent(Scanner scanner) {
		this.scanner = scanner;
	};

	public static AddStudent getInstance(Scanner scanner) {
		if ((instance == null) || (instance.scanner != scanner))
			instance = new AddStudent(scanner);
		return instance;
	}

	@Override
	public String getDescription() {
		return "add new student into DB.";
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
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			Date birthDate = Asker.askDate("Birthday(dd.mm.yyyy): ", sdf, false,
					scanner);
			Sex sex = Sex.getSex(Asker.askInteger("Sex(0-Female, 1-Male)*: ", true,
					0, 1, scanner));
			Integer groupId = Asker.askInteger("Group(0-2): ", false, 0, 2, scanner);
			String login = Asker.askString("Login*: ", true, scanner);
			String password = Asker.askString("Password*: ", true, scanner);
			Integer classId;
			SchoolClass schoolClass;
			do {
				classId = Asker.askInteger("Class id*: ", true, 0, Integer.MAX_VALUE,
						scanner);
				schoolClass = DBDAO.getInstance().getSchoolClass(classId);
			} while (schoolClass == null);
			Student student = new Student(name, surname, birthDate, sex, login,
					password, groupId);
			student.setSchoolClass(schoolClass);
			int studentId = DBDAO.getInstance().addStudent(student, "", user);
			System.out.println(String.format("Student id=%d was added.",
					studentId));
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}
}
