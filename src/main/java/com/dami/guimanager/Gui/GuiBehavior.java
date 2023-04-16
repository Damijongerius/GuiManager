package com.dami.guimanager.Gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public interface GuiBehavior {

    public void onInventoryClick(InventoryClickEvent event, String name);

    public Inventory updateInventory(Inventory inv, String name);

    public Inventory buildInventory(String prefix, String inv);
}
