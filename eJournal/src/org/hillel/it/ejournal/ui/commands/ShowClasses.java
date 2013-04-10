package org.hillel.it.ejournal.ui.commands;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.service.io.Service;

public class ShowClasses implements Command {
	public static final String SHOWCLASSES_COMMAND = "show classes";
	protected Scanner scanner;
	protected Service service;
	private static ShowClasses instance = null;

	private ShowClasses(Scanner scanner, Service service) {
		this.scanner = scanner;
		this.service = service;
	};

	public static ShowClasses getInstance(Scanner scanner, Service service) {
		if ((instance == null) || (instance.scanner != scanner)
				|| (instance.service != service))
			instance = new ShowClasses(scanner, service);
		return instance;
	}

	@Override
	public String getDescription() {
		return "show list of all registered school classes.";
	}

	@Override
	public boolean canExecute(User user) {
		return (user != null)
				&& (user.getRole().intValue() >= Role.STUDENT.intValue());
	}

	@Override
	public void execute(User user) {
		if (canExecute(user)) {
			List<SchoolClass> schoolClasses = service.getSchoolClasses();
			Iterator<SchoolClass> iterator = schoolClasses.iterator();
			System.out.println(SchoolClass.stringHeader());
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}
		} else {
			System.out.println("Not enough permission. Access denided.");
		}
	}

}
