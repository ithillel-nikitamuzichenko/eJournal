package org.hillel.it.ejournal.model.entity;

public class Entity {
	private Integer id;

	private EntityType entityType;

	public Entity(Integer id, EntityType entityType) {
		super();
		this.id = id;
		this.entityType = entityType;
	}

	public EntityLog getEntityLog() {
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (this.id == null)
			this.id = id;
	}

	public EntityType getEntityType() {
		return entityType;
	};
}
