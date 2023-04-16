package com.dami.guimanager.Gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Gui {
    private final String prefix;
    private Map<String, Inventory> inventories;
    private List<UUID> players = new ArrayList<>();
    private GuiBehavior behavior;

    public Gui(String prefix){
        this.prefix = prefix;
    }

    public Gui(String prefix, GuiBehavior behavior){
        this.prefix = prefix;
        this.behavior = behavior;
    }

    public boolean containsPlayer(UUID player){
        AtomicBoolean contains = new AtomicBoolean(false);
        players.forEach(uuid -> {
            if(uuid == player){
                contains.set(true);
                return;
            }
        });

        return contains.get();
    }

    public Inventory getInventory(String inventory){
        for (Map.Entry<String, Inventory> set : this.inventories.entrySet()) {
            if(set.getKey().equals(inventory)){
                return updateInventory(set.getKey(),set.getValue());
            }
        }
        Inventory inv = behavior.buildInventory(prefix, inventory);
        inventories.put(inventory,inv);
        return inv;
    }

    public void addPlayer(Player p, String inventory){
        Inventory inv = getInventory(inventory);
        players.add(p.getUniqueId());
        p.openInventory(inv);
    }

    public void removePlayer(Player p){
        players.remove(p.getUniqueId());
        if(players.isEmpty()){
            inventories.clear();
        }
    }

    public void click(String name, InventoryClickEvent e){
        name = getInventoryName(name);
        behavior.onInventoryClick(e,name);
    }

    public String getInventoryName(String name){
        String newName = "";
        for(int i = name.indexOf("|") + 1 ; i < name.length(); i++)
        {
            newName += name.charAt(i);
        }

        System.out.println(newName);
        return newName;
    }

    public Inventory updateInventory(String  name, Inventory inv){
       Inventory inventory = behavior.updateInventory(inv, name);
       for (UUID uuid : players){
           if(Objects.equals(Objects.requireNonNull(Bukkit.getPlayer(uuid)).getOpenInventory().getTitle(), name)){
               Objects.requireNonNull(Bukkit.getPlayer(uuid)).openInventory(inventory);
           }
       }
       return inventory;
    }

    public void setBehavior(GuiBehavior behavior){
        this.behavior = behavior;
    }

    public String getPrefix(){
        return prefix;
    }
}
