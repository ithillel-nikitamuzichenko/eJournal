package org.hillel.it.ejournal.service.io.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.model.entity.Sex;
import org.hillel.it.ejournal.model.entity.Teacher;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class AddTeacher implements Command {

	public static final String ADDTEACHER_COMMAND = "add teacher";

	private static AddTeacher instance = null;
	private Scanner scanner;

	private AddTeacher(Scanner scanner) {
		this.scanner = scanner;
	};

	public static AddTeacher getInstance(Scanner scanner) {
		if ((instance == null) || (instance.scanner != scanner))
			instance = new AddTeacher(scanner);
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
			Teacher teacher = new Teacher(name, surname, birthDate, sex, login,
					password);
			int teacherId = DBDAO.getInstance().addTeacher(teacher, "", user);
			System.out.println(String.format("Teacher id=%d was added.",
					teacherId));
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}
}
