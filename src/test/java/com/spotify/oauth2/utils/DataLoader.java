package com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {
	private final Properties properties;
	public static DataLoader dataloader;

	private DataLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
	}

	public static DataLoader getInstance() {
		if (dataloader == null) {
			dataloader = new DataLoader();
		}
		return dataloader;
	}

	public String getPlayListId() {
		String prop = properties.getProperty("getplaylist_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property client_id is not specified in the config.properties file");
	}
}
