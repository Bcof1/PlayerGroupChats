package me.bcof.playergroupchats.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.bcof.playergroupchats.utils.stringUtils;
import me.bcof.playergroupchats.utils.groupchatController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class groupchatCommand implements CommandExecutor {

    /*
    /groupchat <create/leave> <player,player....>
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("groupchat.use")){
                if(args.length == 0){
                    player.sendMessage(stringUtils.translateMessage("&2&lGroup chat help!"));
                    player.sendMessage(stringUtils.translateMessage("&a&l/groupchat &7&l<create> <groupName> <IGN, IGN, IGN....> &7 - Creates a groupchat, seperate each member with an IGN"));
                    player.sendMessage(stringUtils.translateMessage("&a&l/groupchat &7&l<disband> <groupName> &7 - Disbands the groupchat for everyone involved"));
                    player.sendMessage(stringUtils.translateMessage("&a&l/groupchat &7&l<leave> <groupName> &7 - Only makes you leave the groupchat"));
                }

                if(args.length == 2){
                    String option = args[0];
                    String groupName = args[1];
                    if(option.equalsIgnoreCase("leave")){
                        // make them leave hashmap
                        groupchatController.removeMemberFromGroupChat(groupName, player.getUniqueId());
                        player.sendMessage(stringUtils.translateMessage("&cYou left the group chat"));

                        groupchatController.sendGroupMessage(groupName, player.getDisplayName() + " &c has left the groupchat");
                    }

                    if(option.equalsIgnoreCase("disband")){
                        groupchatController.sendGroupMessage(groupName, player.getDisplayName() + " &chas disbanded the groupchat");
                        groupchatController.disbandGroupChat(groupName);
                    }
                }

                if(args.length > 2){
                    String option = args[0];
                    String groupName = args[1];
                    String message = "";
                    List<UUID> members = new ArrayList<>();

                    if(option.equalsIgnoreCase("create")){
                        if(!groupchatController.groupChatNameExists(groupName)){
                            for (int i = 0; i < args.length; i++) {
                                if(!(i <= 2)){
                                    message = message + args[i];
                                }
                            }
                            String[] uuids = message.split(",");
                            for (int i = 0; i < uuids.length ; i++) {
                                if(Bukkit.getPlayer(uuids[i]) == null){
                                    player.sendMessage(stringUtils.translateMessage("&c" + uuids[i] + " is not an actual player!"));
                                    break;
                                }else{
                                    Player receiver = Bukkit.getPlayer(uuids[i]);
                                    if(!receiver.isOnline()){
                                        player.sendMessage(stringUtils.translateMessage("&c" + receiver + " has to be online to be added!"));
                                        break;
                                    }
                                    members.add(receiver.getUniqueId());
                                    }
                                }
                            groupchatController.addToGroupChat(groupName, members);
                            player.sendMessage(stringUtils.translateMessage("&aCreated " + groupName));
                            }

                        }
                    }
                }
            }
        return false;
        }
    }

