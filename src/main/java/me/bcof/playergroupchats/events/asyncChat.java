package me.bcof.playergroupchats.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.bcof.playergroupchats.utils.groupchatController;

public class asyncChat implements Listener {

    @EventHandler
    public void asyncChatEvent(AsyncPlayerChatEvent event){
        if(groupchatController.getMemberInGroupChat(event.getPlayer().getUniqueId()) != null){
            String groupChatName = groupchatController.getMemberInGroupChat(event.getPlayer().getUniqueId());
            groupchatController.sendGroupMessage(groupChatName, event.getMessage(), event.getPlayer().getDisplayName());
            event.setCancelled(true);
        }
    }

}
