package org.hillel.it.ejournal.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppSettings {
	private Properties properties;
	private static final String GLOBALCFGPATH = "./etc/global.properties";
	private static final AppSettings instance = new AppSettings();

	private AppSettings() {
		properties = new Properties();
		try {
			FileInputStream propFileStream = new FileInputStream(GLOBALCFGPATH);
			properties.load(propFileStream);
			propFileStream.close();
		} catch (IOException ex) {
			System.err.format("Properties file %0 has not yet created",
					GLOBALCFGPATH);
		}
	}

	public static AppSettings getInstance() {
		return instance;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
