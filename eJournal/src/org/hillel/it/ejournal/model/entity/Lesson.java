package org.hillel.it.ejournal.model.entity;

import java.util.Date;
import java.util.List;

public class Lesson {
	protected Subject subject;
	protected Date date;
	protected int number;
	protected SchoolClass schoolClass;
	protected int groupId;

	public Lesson() {

	}

	public List<Mark> getMarks() {
		return null;
	}

	public Teacher getTeacher() {
		return null;
	}

}