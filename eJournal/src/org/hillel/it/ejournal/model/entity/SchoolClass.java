package org.hillel.it.ejournal.model.entity;

public class SchoolClass extends Entity {
	private String name;
	private int year;

	@Override
	public String toString() {
		return String.format("%d%s", year, name.toUpperCase());
	}

	public int getYear() {
		return year;
	}

	public String getName() {
		return name;
	}

	public SchoolClass(String name, int year) {
		super(null, EntityType.SCHOOL_CLASS);
		this.name = name;
		this.year = year;
	}

}
