package org.hillel.it.ejournal.model.entity;

public class Subject extends Entity {
	protected String name;
	protected String shortName;
	protected double rang;

	public Subject(String name, String shortName) {
		super(null, EntityType.SUBJECT);
		this.name = name;
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}
	
	public String getShortName() {
		return shortName;
	}

	public double getRang() {
		return rang;
	}

}