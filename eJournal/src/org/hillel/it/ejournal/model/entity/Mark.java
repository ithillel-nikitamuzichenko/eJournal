package org.hillel.it.ejournal.model.entity;

public class Mark implements Entity{
	protected int value;
	protected String comment;
	protected Student student;
	protected Integer id;
	
	public Mark(int value, String comment, Student student) {
		super();
		this.value = value;
		this.comment = comment;
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