package com.matheus.httpserver.config;

public class ConfigManager {

    private static ConfigManager configManager;
    private static Config currentConfig;

    public ConfigManager() {
    }

    public static ConfigManager getInstance() {

        if (configManager == null) {
            configManager = new ConfigManager();
        }

        return configManager;
    }

    public void loadConfigFile(String filePath) {

    }

    public void getCurrentConfig() {

    }

}
