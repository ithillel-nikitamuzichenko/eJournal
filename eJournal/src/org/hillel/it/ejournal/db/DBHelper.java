package org.hillel.it.ejournal.db;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hillel.it.ejournal.main.AppSettings;
import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.model.entity.Sex;
import org.hillel.it.ejournal.model.entity.User;

public class DBHelper {

	private Connection connection = null;
	private Statement statement = null;
	private static DBHelper instance = null;

	private DBHelper() {
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

	public static DBHelper getInstance() {
		if (instance == null)
			instance = new DBHelper();
		return instance;
	}

	public int addUser(Role role, String name, String surname, Calendar date,
			String login, String pass) {
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
		ResultSet rs = null;
		try {
			statement.execute(String.format(
					"INSERT INTO `EJournal`.`Users` VALUES "
							+ "(null, %d, '%s', '%s', DATE '%s', '%s', '%s')",
					role.intValue(), name, surname,
					date_format.format(date.getTime()), login, pass));
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

	public User getUserById(int id) {
		ResultSet rs = null;
		User usr = null;
		try {
			rs = statement.executeQuery(String.format(
					"SELECT * FROM `EJournal`.`Users` WHERE ID=%d", id));
			if (rs.next()) {
				usr = new User(rs.getString("Name"), rs.getString("Surname"),
						rs.getDate("Birthdate"), Sex.MALE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usr;
	}
}
