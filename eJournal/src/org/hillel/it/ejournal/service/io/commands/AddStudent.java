package org.hillel.it.ejournal.service.io.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.Sex;
import org.hillel.it.ejournal.model.entity.Student;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.model.entity.Role;
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
	public void execute(User user) {
		if ((user != null)
				&& (user.getRole().intValue() >= Role.ADMIN.intValue())) {
			System.out.print("Name: ");
			String name = scanner.nextLine();
			System.out.print("Surname: ");
			String surname = scanner.nextLine();
			System.out.print("Birthday(dd.mm.yyyy): ");
			SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
			Date birthDate = null;
			try {
				birthDate = sdf.parse(scanner.nextLine());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("Sex(0-Female, 1-Male): ");
			Sex sex = Sex.getSex(scanner.nextInt());
			System.out.print("Login: ");
			String login = scanner.next();
			System.out.print("Password: ");
			String password = scanner.next();
			System.out.print("Class id: ");
			Integer classId = scanner.nextInt();

			Student student = new Student(name, surname, birthDate, sex, login,
					password);
			student.setSchoolClass(DBDAO.getInstance().getSchoolClass(classId));
			int studentId = DBDAO.getInstance().addStudent(student);
			System.out.println(String.format("Class id=%d was added.",
					studentId));
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}

}
