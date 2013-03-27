package org.hillel.it.ejournal.main;

import org.hillel.it.ejournal.db.DBHelper;
import org.hillel.it.ejournal.model.entity.Role;
import org.hillel.it.ejournal.model.entity.User;

import java.util.GregorianCalendar;

public class EJournal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GregorianCalendar cal = new GregorianCalendar(1990, 01, 01);
		int userid = DBHelper.getInstance().addUser(Role.STUDENT, "name", "surname", cal, "login", "pass");
		User usr = DBHelper.getInstance().getUserById(userid);
		System.out.println(usr.getName());
		}

}
