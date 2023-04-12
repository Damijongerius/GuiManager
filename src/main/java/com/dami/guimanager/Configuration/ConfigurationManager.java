package com.dami.guimanager.Configuration;

import com.dami.guimanager.GuiManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigurationManager {

    private static Map<String,ConfigurationManager> managers = new LinkedHashMap<>();

    private final String fileName;
    private final GuiManager main;
    private File configFile;
    private FileConfiguration config;

    public ConfigurationManager(GuiManager main, String fileName){
        this.main = main;
        this.fileName = fileName;
        managers.put(fileName,this);

        startUp();
    }

    public ConfigurationManager(GuiManager main, String fileName, boolean autoStartup){
        this.main = main;
        this.fileName = fileName;
        managers.put(fileName,this);

        if(autoStartup) startUp();
    }

    //get config data
    public void startUp(){
        try {
            configFile = new File(main.getDataFolder(), fileName + ".yml");
        } catch (Exception e) {
            main.saveResource( fileName+".yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void startUp(String customPathAddOn){
        try {
            configFile = new File(main.getDataFolder() + "/" + customPathAddOn, fileName + ".yml");
        } catch (Exception e) {
            main.saveResource( fileName+".yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public ConfigurationSection getSection(String path) {
        return config.getConfigurationSection(path);
    }

    public void setConfig(String path, Object info){
        config.set(path, info);
    }

    //saveConfigFile
    public void saveConfig() {
        try {
            this.config.save(this.configFile);
        } catch (IOException e) {
            main.getLogger().severe("Error saving config: " + e.getMessage());
        }
    }

    public static ConfigurationManager getConfigurationManager(String fileName)
    {
        return managers.get(fileName);
    }

}
