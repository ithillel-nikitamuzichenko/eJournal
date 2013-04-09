package org.hillel.it.ejournal.model.entity;

import java.util.Date;
import java.util.List;

public class Teacher extends User {

	private List<Subject> subjects;
	private Date startWork;
	private Integer Experience;
	
	public List<Subject> getSubjects() {
		return subjects;
	}

	public Date getStartWork() {
		return startWork;
	}

	public Integer getExperience() {
		return Experience;
	}

	public Teacher(String name, String surname, Date birthDate, Sex sex, String login, String password) {
		super(name, surname, birthDate, sex, Role.TEACHER, login, password);
		// TODO Auto-generated constructor stub
	}

}
