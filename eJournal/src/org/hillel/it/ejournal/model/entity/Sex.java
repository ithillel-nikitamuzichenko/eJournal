package org.hillel.it.ejournal.model.entity;

public enum Sex {
	FEMALE {
		public int intValue() {
			return 0;
		}
	},
	MALE {
		public int intValue() {
			return 1;
		}
	};

	public abstract int intValue();

	public static Sex getSex(int value) {
		switch (value) {
		case 0:
			return FEMALE;
		case 1:
			return MALE;
		default: 
			return null;
		}
	}
}
