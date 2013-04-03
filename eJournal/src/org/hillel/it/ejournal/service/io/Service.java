package org.hillel.it.ejournal.service.io;

import java.util.HashMap;
import java.util.Map;

public interface Service {
	Map<String, String> commands = new HashMap<String, String>();
	public void interpretCommand(String command);
}
