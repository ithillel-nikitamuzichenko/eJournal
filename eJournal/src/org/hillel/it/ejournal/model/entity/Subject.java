package org.hillel.it.ejournal.model.entity;

public class Subject implements Entity {
	protected String name;
	protected String shortName;
	protected double rang;
	protected Integer id; 

	public Subject(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.SUBJECT;
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