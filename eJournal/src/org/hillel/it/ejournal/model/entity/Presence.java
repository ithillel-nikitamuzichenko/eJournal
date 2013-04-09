package org.hillel.it.ejournal.model.entity;

public class Presence implements Entity{

	protected int value;
	protected int delay;
	protected Student student;
	protected Integer id;
	
	public Presence(int value, int delay, Student student) {
		super();
		this.value = value;
		this.delay = delay;
		this.student = student;
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

}