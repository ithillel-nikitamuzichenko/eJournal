package org.hillel.it.ejournal.model.entity;

import java.util.Date;
import java.util.List;

public class Student extends User {
		protected SchoolClass schoolClass;
		protected int groupId;


		public Student(String name, String surname, Date birthDate, Sex sex) {
			super(name, surname, birthDate, sex);
			// TODO Auto-generated constructor stub
		}

		public SchoolClass getSchoolClass () {
			return schoolClass;
		}

		public List<Mark> getMarks (Subject subject) {
			return null;
		}

		public List<Lesson> getTimetable (int dayNumber) {
			return null;
		}


}
