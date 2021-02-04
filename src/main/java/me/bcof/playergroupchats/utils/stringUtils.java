package me.bcof.playergroupchats.utils;

import org.bukkit.ChatColor;

public class stringUtils {

    public static String translateMessage(String message){
        String string = " ";
        string = ChatColor.translateAlternateColorCodes('&', message);
        return string;
    }


}
