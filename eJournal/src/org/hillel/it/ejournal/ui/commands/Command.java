package org.hillel.it.ejournal.ui.commands;

import org.hillel.it.ejournal.model.entity.User;

public interface Command {
	String getDescription();
	void execute(User user);
	boolean canExecute(User user);
}
