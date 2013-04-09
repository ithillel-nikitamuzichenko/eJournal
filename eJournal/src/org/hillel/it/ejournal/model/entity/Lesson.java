package org.hillel.it.ejournal.model.entity;

import java.util.Date;
import java.util.List;

public class Lesson implements Entity {
	protected Subject subject;
	protected Date date;
	protected int number;
	protected SchoolClass schoolClass;
	protected int groupId;
	protected Integer id;

	public Lesson() {

	}

	public List<Mark> getMarks() {
		return null;
	}

	public Teacher getTeacher() {
		return null;
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