package com.sekapato.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertyConfig {

    private static final Logger logger = LoggerFactory.getLogger(PropertyConfig.class);

    private static PropertyConfig config;

    private static PropertiesConfiguration propertiesConfiguration;

    public PropertyConfig() throws ConfigurationException {
        propertiesConfiguration = new PropertiesConfiguration("application.properties");
    }

    public static PropertyConfig getInstance() {
        
        try {
            config = new PropertyConfig();
        } catch (ConfigurationException e) {
            logger.error("failed to load property file");
        }
        return config;
    }

    public String getString(String key) {
        return propertiesConfiguration.getString(key, "");
    }

    public int getInt(String key) {
        return propertiesConfiguration.getInt(key, 0);
    }

}
