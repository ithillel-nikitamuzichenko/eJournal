package org.hillel.it.ejournal.model.entity;

public interface Entity {
	public EntityType getEntityType();

	public Integer getId();

	public void setId(Integer id);

	public EntityLog getEntityLog();
}
