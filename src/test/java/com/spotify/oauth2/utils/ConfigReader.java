package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private Properties properties;

    private ConfigReader() {
        properties = ConfigLoader.loadProperties();
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getClientId() {
        return properties.getProperty("client_id");
    }

    public String getClientSecret() {
        return properties.getProperty("client_secret");
    }

    public String getGrantType() {
        return properties.getProperty("grant_type");
    }

    public String getRefreshToken() {
        return properties.getProperty("refresh_token");
    }

    public String getUser(){
        return properties.getProperty("user");
    }
}

