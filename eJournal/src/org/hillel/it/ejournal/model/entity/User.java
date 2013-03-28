package org.hillel.it.ejournal.model.entity;

import java.util.Date;

public class User {
	private String name;
	private String surname;
	private Date birthDate;
	private Sex sex;


	public User(String name, String surname, Date birthDate, Sex sex) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.sex = sex;
	}

	public String getName () {
		return name;
	}
	
	public String getSurname () {
		return surname;
	}

	public Date getBirthDate () {
		return birthDate;
	}

	public Sex getSex () {
		return sex;
	}
}
