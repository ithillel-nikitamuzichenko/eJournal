package org.hillel.it.ejournal.service.persistance.dao;

import java.util.List;
import java.util.Map;

import org.hillel.it.ejournal.model.entity.*;

public interface DAO {
	int addStudent(Student student, String comment, User creator);
	Student getStudent(int id);
	List<Student> getClassList(int schoolClassId);

	int addTeacher(Teacher teacher);
	Teacher getTeacher(int id);
	
	int addSubject(Subject subject);
	List<Subject> getSubjectList();

	int addSchoolClass(SchoolClass schoolClass, String comment, User creator);
	SchoolClass getSchoolClass(int id);
	SchoolClass getSchoolClass(int year, String name);
	Map<Integer, SchoolClass> getSchoolClasses();

	int addMark(Mark mark);
	Mark getMark(int id);

	int addPresence(Presence presence);
	Presence getPresence(int id);

	int addLesson(Lesson lesson);
	Lesson getLesson(int id);
}
