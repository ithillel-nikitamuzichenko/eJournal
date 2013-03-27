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
}
