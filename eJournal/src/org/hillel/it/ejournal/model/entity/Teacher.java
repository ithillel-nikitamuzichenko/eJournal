package org.hillel.it.ejournal.model.entity;

import java.util.Date;

public class Teacher extends User {

	public Teacher(String name, String surname, Date birthDate, Sex sex, String login, String password) {
		super(name, surname, birthDate, sex, Role.TEACHER, login, password);
		// TODO Auto-generated constructor stub
	}

}
