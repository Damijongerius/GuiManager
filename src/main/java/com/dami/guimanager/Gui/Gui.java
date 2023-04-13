package com.dami.guimanager.Gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
                return set.getValue();
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

    public void setBehavior(GuiBehavior behavior){
        this.behavior = behavior;
    }

    public String getPrefix(){
        return prefix;
    }
}
