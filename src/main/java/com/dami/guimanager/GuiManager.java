package com.dami.guimanager;

import com.dami.guimanager.Gui.Gui;
import com.dami.guimanager.Gui.GuiBehavior;
import com.dami.guimanager.Item.Items;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;


public final class GuiManager extends JavaPlugin{
    private static GuiManager instance;
    private Items items = new Items();

    private List<Gui> inventories = new ArrayList<>();
    private Map<UUID,Gui> playerInventories =  new LinkedHashMap<>();
    @Override
    public void onEnable() {
        instance = this;

    }

    /**
     * all the inventories and static items here pls
     */
    public void onInitialize(List<Gui> inventoryList, Map<String, ItemStack> staticItems){
        Items.staticItems = staticItems;
        inventories = inventoryList;
    }

    public void addGui(Gui gui){
        inventories.add(gui);
    }
    public void addGui(String  prefix, GuiBehavior behavior){
        inventories.add(new Gui(prefix,behavior));
    }

    public void addStaticItem(String name, ItemStack items){
        Items.staticItems.put(name,items);
    }

    public void openGuiFor(Player p, String prefix, String ExtendingInventory){

    }

    public static GuiManager getInstance(){
        return instance;
    }
}
