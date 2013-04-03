package org.hillel.it.ejournal.service.persistance.dao;

import java.util.Map;

import org.hillel.it.ejournal.model.entity.*;

public interface DAO {
	public int addStudent(Student student);
	public Student getStudent(int id);

	public int addTeacher(Teacher teacher);
	public Teacher getTeacher(int id);

	public int addSchoolClass(SchoolClass schoolClass);
	public SchoolClass getSchoolClass(int id);
	public SchoolClass getSchoolClass(int year, String name);
	public Map<Integer, SchoolClass> getSchoolClasses();

	public int addMark(Mark mark);
	public Mark getMark(int id);

	public int addPresence(Presence presence);
	public Presence getPresence(int id);

	public int addLesson(Lesson lesson);
	public Lesson getLesson(int id);
}
