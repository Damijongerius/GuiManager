package com.dami.guimanager.ClickEvents;

import com.dami.guimanager.Gui.Gui;
import com.dami.guimanager.Gui.Wrapper;
import com.dami.guimanager.GuiManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class GuiWaiter implements Listener {

    private Map<UUID, Wrapper> players = new LinkedHashMap<>();

    private GuiManager main;
    public GuiWaiter(GuiManager gm){
        main = gm;
    }

    /**
     * this is for needing a chat message from a player
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Wrapper wrapper = players.get(player.getUniqueId());
        if (wrapper != null) {
            Gui gui = main.getGui(wrapper.FullName);
            assert gui != null;
            gui.reOpenPlayer(player,gui.getInventoryName(wrapper.FullName),wrapper.inventory,wrapper.tinyBehavior,event.getMessage());
            players.remove(player.getUniqueId());
        }
    }

    public void onPlayerLeave(PlayerQuitEvent e){
        players.remove(e.getPlayer().getUniqueId());
    }

    public void AddPlayerForMessage(Player p, String tinyBehavior, String message){
        Wrapper wrapper = new Wrapper();
        wrapper.FullName = p.getOpenInventory().getTitle();
        wrapper.inventory = p.getOpenInventory().getInventory(0);
        wrapper.tinyBehavior = tinyBehavior;

        players.put(p.getUniqueId(),wrapper);

        p.closeInventory();
        p.sendMessage(message);
    }
    public void AddPlayerForMessage(Player p, String tinyBehavior, String message, boolean actionBar){
        Wrapper wrapper = new Wrapper();
        wrapper.FullName = p.getOpenInventory().getTitle();
        wrapper.inventory = p.getOpenInventory().getInventory(0);
        wrapper.tinyBehavior = tinyBehavior;

        players.put(p.getUniqueId(),wrapper);

        p.closeInventory();
        if(actionBar){
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }else{
          p.sendMessage(message);
        }

    }
}
