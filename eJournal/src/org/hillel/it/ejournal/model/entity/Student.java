package org.hillel.it.ejournal.model.entity;

import java.util.Date;

public class Student extends User {
		private SchoolClass schoolClass;

		private Integer groupId;

		public Student(String name, String surname, Date birthDate, Sex sex, String login, String password, Integer groupId) {
			super(name, surname, birthDate, sex, Role.STUDENT, login, password);
			this.groupId = groupId;  
		}

		public Integer getGroupId() {
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
