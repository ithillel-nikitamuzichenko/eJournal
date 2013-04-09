package org.hillel.it.ejournal.service.io.commands;

import java.util.Map;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.service.persistance.dao.DBDAO;

public class ShowClasses implements Command {
	public static final String SHOWCLASSES_COMMAND = "show classes";

	private static ShowClasses instance = null;
	private Scanner scanner;

	private ShowClasses(Scanner scanner) {
		this.scanner = scanner;
	};

	public static ShowClasses getInstance(Scanner scanner) {
		if ((instance == null) || (instance.scanner != scanner))
			instance = new ShowClasses(scanner);
		return instance;
	}

	@Override
	public String getDescription() {
		return "show list of all registered school classes.";
	}

	@Override
	public void execute(User user) {
		if ((user != null)
				&& (user.getRole().intValue() >= Role.STUDENT.intValue())) {
			Map<Integer, SchoolClass> schoolClasses = DBDAO.getInstance()
					.getSchoolClasses();
			for (Map.Entry<Integer, SchoolClass> entry : schoolClasses
					.entrySet()) {
				Integer key = entry.getKey();
				SchoolClass value = entry.getValue();
				System.out.println(String.format("%d - %s", key,
						value.toString()));
			}
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}

}
