package org.hillel.it.ejournal.model.entity;

public class Subject extends Entity {
	protected String name;
	protected double rang;

	public Subject() {
		super(null, EntityType.SUBJECT);
	}

	public String getName() {
		return name;
	}

	public double getRang() {
		return rang;
	}

}