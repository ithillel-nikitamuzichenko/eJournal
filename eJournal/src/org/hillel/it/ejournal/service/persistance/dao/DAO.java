package org.hillel.it.ejournal.service.persistance.dao;

import java.util.List;
import java.util.Map;

import org.hillel.it.ejournal.model.entity.*;

public interface DAO {
	int addUser(User user, String comment, User creator);
	User getUser(String login, String password);
	
	int addStudent(Student student, String comment, User creator);
	Student getStudent(int id);
	List<Student> getClassList(int schoolClassId);

	int addTeacher(Teacher teacher, String comment, User creator);
	Teacher getTeacher(int id);
	
	int addSubject(Subject subject, String comment, User creator);
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
