package com.matheus.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.matheus.httpserver.config.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }

        StringBuffer stringBuffer = new StringBuffer();

        int i;
        while (true) {
            try {
                if (!((i = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            stringBuffer.append((char) i);
        }

        JsonNode jsonNode = null;
        try {
            jsonNode = Json.parse(stringBuffer.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the configuration file.", e);
        }
        try {
            currentConfig = Json.fromJson(jsonNode, Config.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the configuration file internal.", e);
        }
    }

    public Config getCurrentConfig() {

        if (currentConfig == null) {
            throw new HttpConfigurationException("No Current Configuration Set.");
        }

        return currentConfig;
    }

}
