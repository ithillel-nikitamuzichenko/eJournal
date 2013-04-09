package org.hillel.it.ejournal.service.io.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.SchoolClass;
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
			SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
			Date birthDate = null;
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.add(Calendar.YEAR, -90);
			do
				try {
					System.out.print("Birthday(dd.mm.yyyy): ");
					birthDate = sdf.parse(scanner.nextLine());
				} catch (ParseException e) {
					System.out.println("Ошибка ввода даты");
				}
			while ((birthDate == null)
					|| (birthDate.before(calendar.getTime()))
					|| (birthDate.after(Calendar.getInstance().getTime())));
			System.out.print("Sex(0-Female, 1-Male): ");
			Sex sex = Sex.getSex(scanner.nextInt());
			System.out.print("Login: ");
			String login = scanner.next();
			System.out.print("Password: ");
			String password = scanner.next();
			Integer classId;
			SchoolClass schoolClass;
			do {
				System.out.print("Class id: ");
				classId = scanner.nextInt();
				schoolClass = DBDAO.getInstance().getSchoolClass(classId);
			} while (schoolClass == null);
			Student student = new Student(name, surname, birthDate, sex, login,
					password);
			student.setSchoolClass(schoolClass);
			int studentId = DBDAO.getInstance().addStudent(student, "", user);
			System.out.println(String.format("Student id=%d was added.",
					studentId));
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}
}
