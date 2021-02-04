package me.bcof.playergroupchats.config;

import me.bcof.playergroupchats.PlayerGroupChats;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class configManager {
    private static final PlayerGroupChats main = PlayerGroupChats.getPlugin(PlayerGroupChats.class);
    private static FileConfiguration config;
    private static File configFile;

    public void setup() {
        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdirs();
        }

        configFile = new File(main.getDataFolder(), "settings.yml");

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfigFile() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfigFIle(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
