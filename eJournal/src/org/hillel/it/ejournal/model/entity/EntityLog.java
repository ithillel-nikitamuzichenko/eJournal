package org.hillel.it.ejournal.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntityLog {
	private List<EntityLogRecord> entityLog = new ArrayList<EntityLogRecord>();

	public void add(EntityLogRecord entityLogRecord) {
		entityLog.add(entityLogRecord);
	}

	public EntityLogRecord getRecord(int index) {
		return entityLog.get(index);
	}
}

class EntityLogRecord {
	private User user;
	private Date date;
	private Action action;
	private String comment;

	public EntityLogRecord(User user, Date date, Action action, String comment) {
		super();
		this.user = user;
		this.date = date;
		this.action = action;
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public Action getAction() {
		return action;
	}

	public String getComment() {
		return comment;
	}

}