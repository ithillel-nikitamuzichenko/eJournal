package org.hillel.it.ejournal.service.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.Sex;
import org.hillel.it.ejournal.model.entity.Student;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class AdminService extends UserService implements Service {
	private static final String ADDCLASS_COMMAND = "add class";
	private static final String ADDSTUDENT_COMMAND = "add student";
	private static final String SHOWCLASSES_COMMAND = "show classes";

	private Map<Integer, SchoolClass> schoolClasses = new HashMap<Integer, SchoolClass>();

	public AdminService(User user, Scanner scanner) {
		super(user, scanner);
		commands.put(ADDCLASS_COMMAND, "add new class into DB.");
		commands.put(ADDSTUDENT_COMMAND, "add new student into DB.");
		commands.put(SHOWCLASSES_COMMAND,
				"show list of all registered school classes.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interpretCommand(String command) {
		// TODO Auto-generated method stub
		if (commands.containsKey(command))
			execute((command));
		super.interpretCommand(command);
	}

	private void execute(String command) {
		switch (command.toLowerCase()) {
		case ADDCLASS_COMMAND:
			addSchoolClass();
			break;
		case ADDSTUDENT_COMMAND:
			addStudent();
			break;
		case SHOWCLASSES_COMMAND:
			showSchoolClasses();
			break;
		}
	}

	private void addSchoolClass() {
		System.out.print("Name: ");
		String name = scanner.nextLine();
		System.out.print("Year: ");
		Integer year = Integer.valueOf(scanner.nextLine());
		if (!(year == null) && !(name.length() > 2)) {
			SchoolClass schoolClass = new SchoolClass(name, year);
			int classId = DBDAO.getInstance().addSchoolClass(schoolClass);
			System.out
					.println(String.format("Class id=%d was added.", classId));
			updateSchoolClasses();
		} else {
			System.out.println("Invalid input.");
		}
	}

	private void updateSchoolClasses() {
		schoolClasses = DBDAO.getInstance().getSchoolClasses();
	}

	private void showSchoolClasses() {
		if (schoolClasses.isEmpty())
			updateSchoolClasses();
		for (Map.Entry<Integer, SchoolClass> entry : schoolClasses.entrySet()) {
			Integer key = entry.getKey();
			SchoolClass value = entry.getValue();
			System.out.println(String.format("%d - %s", key, value.toString()));
		}
	}

	private void addStudent() {
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
		System.out.println(String.format("Class id=%d was added.", studentId));
	}
}
