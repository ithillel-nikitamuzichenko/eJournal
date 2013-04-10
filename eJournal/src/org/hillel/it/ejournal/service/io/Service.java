package org.hillel.it.ejournal.service.io;

import java.util.List;

import org.hillel.it.ejournal.model.entity.*;
import org.hillel.it.ejournal.service.persistance.dao.DAO;

public class Service {

	private static Service instance = null;
	private DAO dao;

	private Service(DAO dao) {
		this.dao = dao;
	};

	public static Service getInstance(DAO dao) {
		if ((instance == null) || (instance.dao != dao))
			instance = new Service(dao);
		return instance;
	}

	public int addUser(User user, String comment, User creator) {
		return dao.addUser(user, comment, creator);
	}

	public User getUser(String login, String password) {
		return dao.getUser(login, password);
	}

	public int addStudent(Student student, String comment, User creator) {
		return dao.addStudent(student, comment, creator);
	}

	public Student getStudent(int id) {
		return dao.getStudent(id);
	}

	public List<Student> getClassList(int schoolClassId) {
		return dao.getClassList(schoolClassId);
	}

	public int addTeacher(Teacher teacher, String comment, User creator) {
		return dao.addTeacher(teacher, comment, creator);
	}

	public Teacher getTeacher(int id) {
		return dao.getTeacher(id);
	}

	public int addSubject(Subject subject, String comment, User creator) {
		return dao.addSubject(subject, comment, creator);
	}

	public List<Subject> getSubjectList() {
		return dao.getSubjectList();
	}

	public int addSchoolClass(SchoolClass schoolClass, String comment,
			User creator) {
		return dao.addSchoolClass(schoolClass, comment, creator);
	}

	public SchoolClass getSchoolClass(int id) {
		return dao.getSchoolClass(id);
	}

	public SchoolClass getSchoolClass(int year, String name) {
		return dao.getSchoolClass(year, name);
	}

	public List<SchoolClass> getSchoolClasses() {
		return dao.getSchoolClasses();
	}

	public int addMark(Mark mark) {
		return dao.addMark(mark);
	}

	public Mark getMark(int id) {
		return dao.getMark(id);
	}

	public int addPresence(Presence presence) {
		return addPresence(presence);
	}

	public Presence getPresence(int id) {
		return dao.getPresence(id);
	}

	public int addLesson(Lesson lesson) {
		return dao.addLesson(lesson);
	}

	public Lesson getLesson(int id) {
		return dao.getLesson(id);
	}

}
