package org.hillel.it.ejournal.service.io.commands;

import org.hillel.it.ejournal.model.entity.User;

public interface Command {
	String getDescription();
	void execute(User user);
}