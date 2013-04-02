package org.hillel.it.ejournal.model.entity;

import java.util.Date;

public class Admin extends User {

	public Admin(String name, String surname, Date birthDate, Sex sex, String login, String password) {
		super(name, surname, birthDate, sex, Role.ADMIN, login, password);
		// TODO Auto-generated constructor stub
	}

}
