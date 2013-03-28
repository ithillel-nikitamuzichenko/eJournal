package org.hillel.it.ejournal.model.entity;

import java.util.Date;

public class Admin extends Teacher {

	public Admin(String name, String surname, Date birthDate, Sex sex) {
		super(name, surname, birthDate, sex);
		// TODO Auto-generated constructor stub
	}

	public int addSchoolClass(String name, int year) {
		return 0;
	}

	public int addSubject(String name, double rang) {
		return 0;
	}

	public int addStudent(String name, String surname, Date birthDate, Sex sex,
			int schoolClassId, int groupId) {
		return 0;
	}

	public int addTeacher(String name, String surname, Date birthDate, Sex sex) {
		return 0;
	}

	public void addTeacherSubjectLink(int teacherId, int subjectId) {

	}

	public void addTeacherClassLink(int teacherId, int schoolClassId) {

	}

	public int addLesson(int subjectId, int schoolClassid, int groupId,
			int dayNumber, int lessonNumber) {
		return 0;
	}

}
