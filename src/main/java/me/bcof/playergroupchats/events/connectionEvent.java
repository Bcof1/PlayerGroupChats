package me.bcof.playergroupchats.events;

import me.bcof.playergroupchats.utils.groupchatController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.bcof.playergroupchats.utils.stringUtils;
import org.bukkit.event.player.PlayerQuitEvent;

public class connectionEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(groupchatController.getMemberInGroupChat(event.getPlayer().getUniqueId()) != null){
            String groupChatName = groupchatController.getMemberInGroupChat(event.getPlayer().getUniqueId());
            groupchatController.sendGroupMessage(groupChatName, stringUtils.translateMessage("&a&l" + event.getPlayer() + " &a has joined"));
        }

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        if(groupchatController.getMemberInGroupChat(event.getPlayer().getUniqueId()) != null){
            String groupChatName = groupchatController.getMemberInGroupChat(event.getPlayer().getUniqueId());
            groupchatController.sendGroupMessage(groupChatName, stringUtils.translateMessage("&a&l" + event.getPlayer() + " &a has quit"));
        }
    }
}
