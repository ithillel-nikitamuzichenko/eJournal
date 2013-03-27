package org.hillel.it.ejournal.model.entity;

import java.util.Date;
import java.util.List;

public class Teacher extends Student {

	public Teacher(String name, String surname, Date birthDate, Sex sex) {
		super(name, surname, birthDate, sex);
		// TODO Auto-generated constructor stub
	}

	public List<Lesson> getTimetable(int dayNumber) {
		return null;
	}

	public List<SchoolClass> getSchoolClasses() {
		return null;
	}

	public void setMark(int value, String comment, Student student,
			Lesson lesson) {

	}

	public void setPresence(int value, int delay, Student student, Lesson lesson) {

	}

}
