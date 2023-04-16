package com.dami.guimanager.Gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiCreator{
    private Inventory inventory;

    public GuiCreator(String prefix, String name, int size){
        inventory = Bukkit.createInventory(null,size,prefix +  " | " +  name);
    }

    public void setItem(int slot, ItemStack item){
        inventory.setItem(slot,item);
    }

    public void setItems(int begin, int end, ItemStack item){
        int apply = begin < end ? 1 : -1;
        for(int i = begin; i <= end; i += apply){
            inventory.setItem(i,item);
        }
    }
    public void setItems(int[] slots, ItemStack item){
        for(int slot : slots){
            inventory.setItem(slot,item);
        }
    }

    public Inventory buildInventory(){
        return inventory;
    }
}
