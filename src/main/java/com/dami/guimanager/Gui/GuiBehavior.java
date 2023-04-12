package com.dami.guimanager.Gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public interface GuiBehavior {

    public void onInventoryClick(InventoryClickEvent event);

    public Inventory updateInventory(Inventory inv);

    public Inventory BuildInventory(String inv);
}
