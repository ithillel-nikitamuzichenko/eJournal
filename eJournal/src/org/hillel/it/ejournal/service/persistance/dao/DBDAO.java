package org.hillel.it.ejournal.service.persistance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.hillel.it.ejournal.main.AppSettings;
import org.hillel.it.ejournal.model.entity.*;

public class DBDAO implements DAO {

	private Connection connection = null;
	private Statement statement = null;
	private static DBDAO instance = null;
	private ResultSet rs = null;
	private SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");

	private DBDAO() {
		super();
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(AppSettings.getInstance()
					.getProperty("ConnectString"), AppSettings.getInstance()
					.getProperty("Login"), AppSettings.getInstance()
					.getProperty("Password"));
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBDAO getInstance() {
		if (instance == null)
			instance = new DBDAO();
		return instance;
	}

	private int commitEntity(Entity entity, Action action, String comment,
			User creator) {
		try {
			statement
					.execute(String
							.format("INSERT INTO EJournal.EntitiesLog VALUES "
									+ "(NULL, %d, %d, CURRENT_TIMESTAMP, %d, '%s', %d)",
									entity.getId(), entity.getEntityType().intValue(),
									action.intValue(), comment, creator.getId()));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int addUser(User user, String comment, User creator) {
		try {
			statement
					.execute(String
							.format("INSERT INTO EJournal.Users VALUES "
									+ "(null, %d, '%s', '%s', DATE '%s', %d, '%s', '%s')",
									user.getRole().intValue(), user.getName(),
									user.getSurname(), date_format.format(user
											.getBirthDate().getTime()), user
											.getSex().intValue(), user
											.getLogin(), DigestUtils.md5Hex(user.getPassword())));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				int userId = rs.getInt(1);
				user.setId(userId);
				commitEntity(user, Action.CREATE, comment, creator);
				return userId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public User getUser(String login, String password) {
		User user = null;
		try {
			rs = statement.executeQuery(String.format(
					"SELECT * FROM EJournal.Users WHERE (Login = '%s') "
							+ "AND (Password = '%s')", login,
					DigestUtils.md5Hex(password)));
			if (rs.next()) {
				user = new User(rs.getString("Name"), rs.getString("Surname"),
						rs.getDate("Birthdate"), Sex.getSex(rs.getInt("Sex")),
						Role.getRole(rs.getInt("Role")), rs.getString("Login"),
						rs.getString("Password"));
				user.setId(rs.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int addStudent(Student student, String comment, User creator) {
		try {
			int userId = addUser(student, comment, creator);
			statement.execute(String.format(
					"INSERT INTO EJournal.Students VALUES (null, %d, %d, %d)",
					userId, student.getSchoolClass().getId(),
					student.getGroupId()));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Student getStudent(int id) {
		Student stud = null;
		try {
			rs = statement
					.executeQuery(String
							.format("SELECT * FROM EJournal.Users JOIN EJournal.Students "
									+ "WHERE EJournal.Students.USERID=EJournal.Users.ID",
									id));
			if (rs.next()) {
				stud = new Student(rs.getString("Name"),
						rs.getString("Surname"), rs.getDate("Birthdate"),
						Sex.getSex(rs.getInt("Sex")), rs.getString("Login"),
						rs.getString("Password"), rs.getInt("GroupId"));
				stud.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stud;
	}

	public List<Student> getClassList(int schoolClassId) {
		List<Student> studentList = new ArrayList<Student>();
		try {
			rs = statement
					.executeQuery(String
							.format("SELECT * FROM EJournal.Users JOIN EJournal.Students "
									+ "WHERE (EJournal.Students.UserId=EJournal.Users.Id) "
									+ "and (Ejournal.Students.ClassId=%d) order by Surname",
									schoolClassId));
			while (rs.next()) {
				Student student = new Student(rs.getString("Name"),
						rs.getString("Surname"), rs.getDate("Birthdate"),
						Sex.getSex(rs.getInt("Sex")), rs.getString("Login"),
						rs.getString("Password"), rs.getInt("GroupId"));
				student.setId(rs.getInt("Id"));
				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public int addSubject(Subject subject, String comment, User creator) {
		try {
			statement
					.execute(String
							.format("INSERT INTO EJournal.Disciplines VALUES(null, '%s', '%s')",
									subject.getName(), subject.getShortName()));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				int subjectId = rs.getInt(1);
				subject.setId(subjectId);
				commitEntity(subject, Action.CREATE, comment, creator);
				return subjectId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Subject> getSubjectList() {
		List<Subject> subjectList = new ArrayList<Subject>();
		try {
			rs = statement.executeQuery("SELECT * FROM EJournal.Disciplines");
			while (rs.next()) {
				Subject subject = new Subject(rs.getString("Name"),
						rs.getString("Short_name"));
				subject.setId(rs.getInt("Id"));
				subjectList.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectList;
	}

	@Override
	public int addTeacher(Teacher teacher, String comment, User creator) {
		try {
			int userId = addUser(teacher, comment, creator);
			statement
					.execute(String
							.format("INSERT INTO EJournal.Teachers VALUES (null, %d, DATE '%s', %d)",
									userId, teacher.getStartWork().getTime(),
									teacher.getExperience()));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Teacher getTeacher(int id) {
		return null;
	}

	@Override
	public int addSchoolClass(SchoolClass schoolClass, String comment,
			User creator) {
		try {
			statement.execute(String.format(
					"INSERT INTO EJournal.Classes VALUES (null, '%s', %d)",
					schoolClass.getName(), schoolClass.getYear()));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				int classId = rs.getInt(1);
				schoolClass.setId(classId);
				commitEntity(schoolClass, Action.CREATE, comment, creator);
				return classId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public SchoolClass getSchoolClass(int id) {
		SchoolClass schoolClass = null;
		try {
			rs = statement.executeQuery(String.format(
					"SELECT * FROM EJournal.Classes WHERE ID = %d", id));
			if (rs.next()) {
				schoolClass = new SchoolClass(rs.getString("Name"),
						rs.getInt("Year"));
				schoolClass.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return schoolClass;
	}

	@Override
	public SchoolClass getSchoolClass(int year, String name) {
		SchoolClass schoolClass = null;
		try {
			rs = statement
					.executeQuery(String
							.format("SELECT * FROM EJournal.Classes WHERE (Year = %d) AND (Name = '%s')",
									year, name));
			if (rs.next()) {
				schoolClass = new SchoolClass(name, year);
				schoolClass.setId(rs.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return schoolClass;
	}

	@Override
	public Map<Integer, SchoolClass> getSchoolClasses() {
		Map<Integer, SchoolClass> schoolClasses = new HashMap<Integer, SchoolClass>();
		try {
			rs = statement.executeQuery("SELECT * FROM EJournal.Classes");
			while (rs.next()) {
				SchoolClass schoolClass = new SchoolClass(rs.getString("Name"),
						rs.getInt("Year"));
				schoolClass.setId(rs.getInt("ID"));
				schoolClasses.put(schoolClass.getId(), schoolClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return schoolClasses;
	}

	@Override
	public int addMark(Mark mark) {
		return 0;
	}

	@Override
	public Mark getMark(int id) {
		return null;
	}

	@Override
	public int addPresence(Presence presence) {
		return 0;
	}

	@Override
	public Presence getPresence(int id) {
		return null;
	}

	@Override
	public int addLesson(Lesson lesson) {
		return 0;
	}

	@Override
	public Lesson getLesson(int id) {
		return null;
	}

}
