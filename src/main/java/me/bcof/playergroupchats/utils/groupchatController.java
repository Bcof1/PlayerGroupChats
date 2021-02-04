package me.bcof.playergroupchats.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import me.bcof.playergroupchats.config.configManager;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class groupchatController {
    private static HashMap<String, List<UUID>> groupChats = new HashMap<>();
    static configManager configManager;

    // creates our groupchat with its members
    public static void addToGroupChat(String groupChatName, List<UUID> members) {
        groupChats.put(groupChatName, members);
    }

    // does the groupchat even exist?
    public static boolean groupChatNameExists(String groupChatName) {
        if (groupChats.containsKey(groupChatName)) return true;
        return false;
    }

    // removes one single member from the groupchat
    public static void removeMemberFromGroupChat(String groupChatName, UUID member) {
        if (groupChats.containsKey(groupChatName)) {
            groupChats.get(groupChatName).remove(member);
        }
    }

    // deletes our groupchat entirely
    public static void disbandGroupChat(String groupChatName) {
        if (groupChatNameExists(groupChatName)) {
            groupChats.remove(groupChatName);
        }
    }

    public static String getMemberInGroupChat(UUID member) {
        if (groupChats.containsValue(member)) {
            for (String groupChat : groupChats.keySet()) {
                for (int i = 0; i < groupChats.values().size(); i++) {
                    if (groupChats.get(groupChat).get(i) == member) {
                        return groupChat;
                    }
                }
            }
        }
        return null;
    }


    // passes messages to all members of the groupchat
    public static void sendGroupMessage(String groupChatName, String[] arguments, String sender) {
        if (groupChatNameExists(groupChatName)) {
            List<UUID> members = groupChats.get(groupChatName);
            String args = " ";
            for (int i = 0; i < arguments.length; i++) {
                args = arguments[i];
            }

            for (int i = 0; i < groupChats.get(groupChatName).size(); i++) {
                Player player = Bukkit.getPlayer(members.get(i));
                player.sendMessage(stringUtils.translateMessage(sender + ": " + args));
            }

        }
    }

    public static void sendGroupMessage(String groupChatName, String message) {
        if (groupChatNameExists(groupChatName)) {
            List<UUID> members = groupChats.get(groupChatName);

            for (int i = 0; i < groupChats.get(groupChatName).size(); i++) {
                Player player = Bukkit.getPlayer(members.get(i));
                player.sendMessage(stringUtils.translateMessage(message));
            }

        }
    }

    public static void sendGroupMessage(String groupChatName, String message, String sender) {
        if (groupChatNameExists(groupChatName)) {
            List<UUID> members = groupChats.get(groupChatName);

            for (int i = 0; i < groupChats.get(groupChatName).size(); i++) {
                Player player = Bukkit.getPlayer(members.get(i));
                player.sendMessage(stringUtils.translateMessage(sender + ":" + message));
            }
        }
    }

    // creates the group chat and its attributes in our yml file
    public static void createGroupChatInConfig(String groupchatName, UUID owner){
        FileConfiguration file = configManager.getConfig();
        file.createSection(groupchatName);
        ConfigurationSection section = file.getConfigurationSection(groupchatName);

        section.set("Leader", owner);
        section.set("Members", groupChats.get(groupchatName));
        configManager.saveConfigFile();
    }



}
