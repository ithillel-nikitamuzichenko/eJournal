package org.hillel.it.ejournal.service.persistance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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

	public int addUser(User user) {
		try {
			statement
					.execute(String
							.format("INSERT INTO EJournal.Users VALUES "
									+ "(null, %d, '%s', '%s', DATE '%s', %d, '%s', '%s')",
									user.getRole().intValue(), user.getName(),
									user.getSurname(), date_format.format(user
											.getBirthDate().getTime()), user
											.getSex().intValue(), user
											.getLogin(), user.getPassword()));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public User getUser(String login, String password) {
		User user = null;
		try {
			rs = statement.executeQuery(String.format(
					"SELECT * FROM EJournal.Users WHERE (Login = '%s') "
							+ "AND (Password = '%s')", login, password));
			if (rs.next()) {
				user = new User(rs.getString("Name"), rs.getString("Surname"),
						rs.getDate("Birthdate"), Sex.getSex(rs.getInt("Sex")),
						Role.getRole(rs.getInt("Role")), rs.getString("Login"),
						rs.getString("Password"));
				user.setId(rs.getInt("ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int addStudent(Student student) {
		// TODO Auto-generated method stub
		try {
			int userId = addUser(student);
			statement.execute(String.format(
					"INSERT INTO EJournal.Students VALUES (null, %d, %d, %d)",
					userId, student.getSchoolClass().getId(),
					student.getGroupId()));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Student getStudent(int id) {
		// TODO Auto-generated method stub
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
						rs.getString("Password"));
				stud.setId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stud;
	}

	@Override
	public int addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Teacher getTeacher(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addSchoolClass(SchoolClass schoolClass) {
		try {
			statement.execute(String.format(
					"INSERT INTO EJournal.Classes VALUES (null, '%s', %d)",
					schoolClass.getName(), schoolClass.getYear()));
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolClasses;
	}

	@Override
	public int addMark(Mark mark) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Mark getMark(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPresence(Presence presence) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Presence getPresence(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addLesson(Lesson lesson) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Lesson getLesson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
