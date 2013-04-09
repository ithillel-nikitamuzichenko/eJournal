package org.hillel.it.ejournal.model.entity;

public enum Action {
	CREATE {
		public int intValue() {
			return 0;
		}
	},
	CHANGE {
		public int intValue() {
			return 1;
		}
	},
	DELETE {
		public int intValue() {
			return 2;
		}
	};
	public abstract int intValue();
}
