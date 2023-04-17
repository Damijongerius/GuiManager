package com.dami.guimanager.Gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.LinkedHashMap;
import java.util.Map;

public interface GuiBehavior {

    public void onInventoryClick(InventoryClickEvent event, String name);

    public Inventory updateInventory(Inventory inv, String name);

    public Inventory buildInventory(String prefix, String inv);

    public Map<String,TinyBehavior> addons = new LinkedHashMap<>();

    public default Inventory executeBehaviors( String tinyBehavior, Object obj, Inventory inventory){
        return addons.get(tinyBehavior).execute(inventory,obj);
    }
}
