package org.hillel.it.ejournal.model.entity;

import java.util.List;

public class Student extends User {
		protected SchoolClass schoolClass;
		protected int groupId;

		public Student() {
			
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
