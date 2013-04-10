package org.hillel.it.ejournal.model.entity;

import org.hillel.it.ejournal.service.io.Service;

public class SchoolClass implements Entity {
	private String name;
	private int year;
	private Service service;
	protected Integer id;
	

	public SchoolClass(String name, int year, Service service) {
		this.name = name;
		this.year = year;
		this.service = service;
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

	public static String stringHeader() {
		return String.format("%4s%5s%7s%9s", "id", "Year", "Letter", "Students");
	}
	
	@Override
	public String toString() {
		return String.format("%4d%5d%7s%9d", id, year, name.toUpperCase(), getStudentsNumber());
	}

	public int getYear() {
		return year;
	}

	public String getName() {
		return name;
	}

	public int getStudentsNumber() {
		return service.getClassList(id).size();
	}

}
