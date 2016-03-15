package com.mdc.whowas;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by Main on 3/12/16.
 */
public class WhoWas extends JavaPlugin{
    @Override
    public void onEnable(){}

    @Override
    public void onDisable(){}


    private String prefix = ChatColor.LIGHT_PURPLE + "[WhoWas] ";
    private String badPrefix = ChatColor.LIGHT_PURPLE + "[WhoWas] " + ChatColor.RED + ChatColor.ITALIC;

    @Override
    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("whowas.use")){
            sender.sendMessage(badPrefix + "You do not have permission to use WhoWas!");
            return true;
        }
        if(args.length != 1){
            sender.sendMessage(badPrefix + "Usage: /whowas <username>");
            return true;
        }
        final String username = args[0];
        sender.sendMessage(prefix + ChatColor.ITALIC + "Loading asynchronously...");
        Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
            public void run() {
                UsernameHistory history;
                    try {
                        history = new UsernameHistory(username);
                    }catch(IOException e){
                        sender.sendMessage(badPrefix + "An error occurred while trying to connect to Mojang's servers: " + e.getLocalizedMessage());
                        return;
                    }catch(ParseException e){
                        sender.sendMessage(badPrefix + "Unable to understand Mojang's response: " + e.getLocalizedMessage());
                        return;
                    }
                sender.sendMessage(prefix + "Username history for " + ChatColor.ITALIC + username);
                for(Username usr : history.getHistory()){
                    sender.sendMessage(prefix + "     " + usr.getName() + ChatColor.DARK_PURPLE + " - " + usr.getTime());
                }
            }
        });
        return true;
    }

}
