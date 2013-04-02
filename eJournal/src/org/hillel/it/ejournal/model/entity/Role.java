package org.hillel.it.ejournal.model.entity;

public enum Role {

	STUDENT {
		public int intValue() {
			return 0;
		}
	},
	TEACHER {
		public int intValue() {
			return 1;
		}
	},
	ADMIN {
		public int intValue() {
			return 2;
		}
	};

	public abstract int intValue();

	public static Role getRole(int value) {
		switch (value) {
		case 0:
			return STUDENT;
		case 1:
			return TEACHER;
		case 2:
			return ADMIN;
		default:
			return null;
		}
	}
}
