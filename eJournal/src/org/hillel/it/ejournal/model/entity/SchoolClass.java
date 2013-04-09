package org.hillel.it.ejournal.model.entity;

public class SchoolClass implements Entity {
	private String name;
	private int year;
	protected Integer id;

	public SchoolClass(String name, int year) {
		this.name = name;
		this.year = year;
	}
	
	@Override
	public EntityType getEntityType() {
		return EntityType.SCHOOL_CLASS;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		if (this.id == null) {
			this.id = id;
		}
	}

	@Override
	public EntityLog getEntityLog() {
		return null;
	}
	
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

}
