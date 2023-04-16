package com.dami.guimanager;

import com.dami.guimanager.Gui.Gui;
import com.dami.guimanager.Gui.GuiBehavior;
import com.dami.guimanager.Item.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;


public final class GuiManager extends JavaPlugin implements Listener {
    private static GuiManager instance;
    private Items items = new Items();

    private List<Gui> inventories = new ArrayList<>();
    private Map<UUID,Gui> playerInventories =  new LinkedHashMap<>();
    @Override
    public void onEnable() {
        instance = this;

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(playerInventories.containsKey(e.getWhoClicked().getUniqueId())){
            Gui gui = playerInventories.get(e.getWhoClicked().getUniqueId());
            gui.click(e.getView().getTitle(),e);
        }
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        if(playerInventories.containsKey(e.getPlayer().getUniqueId())){
            playerInventories.get(e.getPlayer().getUniqueId()).removePlayer((Player)e.getPlayer());
            playerInventories.remove(e.getPlayer().getUniqueId());
        }
    }

    /**
     * all the inventories and static items here pls
     */
    public void onInitialize(List<Gui> inventoryList, Map<String, ItemStack> staticItems){
        Items.definedItems = staticItems;
        inventories = inventoryList;
    }

    public void addGui(Gui gui){
        inventories.add(gui);
    }
    public void addGui(String  prefix, GuiBehavior behavior){
        inventories.add(new Gui(prefix,behavior));
    }

    public void addStaticItem(String name, ItemStack items){
        Items.definedItems.put(name,items);
    }

    public boolean openGuiFor(Player p, String prefix, String extendingInventory){
        for(Gui gui : inventories){
            if(prefix.equals(gui.getPrefix())){
                gui.addPlayer(p,extendingInventory);
                return true;
            }
        }
        return false;
    }

    public static GuiManager getInstance(){
        return instance;
    }
}
