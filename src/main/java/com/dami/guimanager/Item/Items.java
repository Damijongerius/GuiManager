package com.dami.guimanager.Item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Items {
    public static Map<String, ItemStack> staticItems = new LinkedHashMap<>();

    public static ItemStack generateItem(String name, Material material, List<String> lore, Boolean hide_Attributes){


        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(name);

        if(Boolean.TRUE.equals(hide_Attributes)){
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }
}
