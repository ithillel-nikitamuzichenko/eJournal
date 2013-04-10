package org.hillel.it.ejournal.service.io;

import java.util.List;
import java.util.Map;

import org.hillel.it.ejournal.model.entity.Lesson;
import org.hillel.it.ejournal.model.entity.Mark;
import org.hillel.it.ejournal.model.entity.Presence;
import org.hillel.it.ejournal.model.entity.SchoolClass;
import org.hillel.it.ejournal.model.entity.Student;
import org.hillel.it.ejournal.model.entity.Subject;
import org.hillel.it.ejournal.model.entity.Teacher;
import org.hillel.it.ejournal.model.entity.User;
import org.hillel.it.ejournal.service.persistance.dao.DAO;

public class Service implements DAO {

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

	@Override
	public int addUser(User user, String comment, User creator) {
		return dao.addUser(user, comment, creator);
	}

	@Override
	public User getUser(String login, String password) {
		return dao.getUser(login, password);
	}

	@Override
	public int addStudent(Student student, String comment, User creator) {
		return dao.addStudent(student, comment, creator);
	}

	@Override
	public Student getStudent(int id) {
		return dao.getStudent(id);
	}

	@Override
	public List<Student> getClassList(int schoolClassId) {
		return dao.getClassList(schoolClassId);
	}

	@Override
	public int addTeacher(Teacher teacher, String comment, User creator) {
		return dao.addTeacher(teacher, comment, creator);
	}

	@Override
	public Teacher getTeacher(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addSubject(Subject subject, String comment, User creator) {
		return dao.addSubject(subject, comment, creator);
	}

	@Override
	public List<Subject> getSubjectList() {
		return dao.getSubjectList();
	}

	@Override
	public int addSchoolClass(SchoolClass schoolClass, String comment,
			User creator) {
		return dao.addSchoolClass(schoolClass, comment, creator);
	}

	@Override
	public SchoolClass getSchoolClass(int id) {
		return dao.getSchoolClass(id);
	}

	@Override
	public SchoolClass getSchoolClass(int year, String name) {
		return dao.getSchoolClass(year, name);
	}

	@Override
	public Map<Integer, SchoolClass> getSchoolClasses() {
		return dao.getSchoolClasses();
	}

	@Override
	public int addMark(Mark mark) {
		return dao.addMark(mark);
	}

	@Override
	public Mark getMark(int id) {
		return dao.getMark(id);
	}

	@Override
	public int addPresence(Presence presence) {
		return addPresence(presence);
	}

	@Override
	public Presence getPresence(int id) {
		return dao.getPresence(id);
	}

	@Override
	public int addLesson(Lesson lesson) {
		return dao.addLesson(lesson);
	}

	@Override
	public Lesson getLesson(int id) {
		return dao.getLesson(id);
	}

	
}
