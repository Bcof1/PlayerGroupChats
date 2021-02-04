package me.bcof.playergroupchats;

import me.bcof.playergroupchats.commands.groupchatCommand;
import me.bcof.playergroupchats.events.asyncChat;
import me.bcof.playergroupchats.events.connectionEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.bcof.playergroupchats.config.configManager;


public final class PlayerGroupChats extends JavaPlugin {
    private configManager configManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfigManager();
        getCommand("groupchat").setExecutor(new groupchatCommand());
        getServer().getPluginManager().registerEvents(new asyncChat(), this);
        getServer().getPluginManager().registerEvents(new connectionEvent(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfigManager(){
        configManager = new configManager();
        configManager.setup();
        configManager.saveConfigFile();
        configManager.reloadConfigFIle();

    }
}
