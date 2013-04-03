package org.hillel.it.ejournal.model.entity;

public enum EntityType {
	USER {
		public int intValue() {
			return 0;
		}
	},
	SCHOOL_CLASS {
		public int intValue() {
			return 1;
		}
	},
	SUBJECT {
		public int intValue() {
			return 2;
		}
	},
	MARK {
		public int intValue() {
			return 3;
		}
	},
	PRESENCE {
		public int intValue() {
			return 4;
		}
	},
	LESSON {
		public int intValue() {
			return 5;
		}
	};

	public abstract int intValue();
}
