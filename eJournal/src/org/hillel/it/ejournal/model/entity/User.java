package org.hillel.it.ejournal.model.entity;

import java.util.Date;

abstract class User {
	private String name;
	private Date birthDate;
	private Sex sex;

	public User() {
		
	}

	public String getName () {
		return name;
	}

	public Date getBirthDate () {
		return birthDate;
	}

	public Sex getSex () {
		return sex;
	}
}
