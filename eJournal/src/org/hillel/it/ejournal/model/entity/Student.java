package org.hillel.it.ejournal.model.entity;

import java.util.Date;

public class Student extends User {
		private SchoolClass schoolClass;

		private int groupId;

		public Student(String name, String surname, Date birthDate, Sex sex, String login, String password) {
			super(name, surname, birthDate, sex, Role.STUDENT, login, password);
			// TODO Auto-generated constructor stub
		}

		public int getGroupId() {
			return groupId;
		}
		
		public SchoolClass getSchoolClass () {
			return schoolClass;
		}

		public void setSchoolClass(SchoolClass schoolClass) {
			this.schoolClass = schoolClass;
		}
		
		public static String stringLineHeader() {
			return String.format("%10s%10s%12s%8s%10s", "Surname", "Name",
					"Birthday", "Sex", "Login");
		}

		public String toStringLine() {
			return String.format("%10s%10s%12s%8s%10s", surname, name, birthDate,
					sex, login);
		}
}
