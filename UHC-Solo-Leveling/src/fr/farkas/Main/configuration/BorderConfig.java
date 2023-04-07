package fr.farkas.Main.configuration;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BorderConfig {
	
    private final Map<String, List<String>> ConfigData;
    private Inventory inventory;

    public BorderConfig(Map<String, List<String>> ConfigData) {
        this.ConfigData = ConfigData;
    }

    public void createInventory() {
        inventory = Bukkit.createInventory(null, 9,"§6Border Config");
        List<String> rules = ConfigData.get("Border");
        rules.add(0, "2000");
        rules.add(1, "60");
        updateInventory();
    }
    public void openConfig(Player player) {
        player.openInventory(inventory);
    }
    
    public void updateInventory() {
        List<String> rules = ConfigData.get("Border");
        inventory.setItem(2, createMat(Material.COMPASS, "§4Taille: " + String.valueOf(rules.get(0))));
        inventory.setItem(6, createMat(Material.COMPASS, "§4Timer: " + String.valueOf(rules.get(1))));
        inventory.setItem(1, createMat(Material.REDSTONE, "§cReduce Border" ));
        inventory.setItem(5, createMat(Material.REDSTONE, "§cReduce Timer"));
        inventory.setItem(3, createMat(Material.REDSTONE, "§dAugment Border"));
        inventory.setItem(7, createMat(Material.REDSTONE, "§dAugment Timer"));
        
    }
    public void BorderConfigClick(InventoryClickEvent event) {
    	Inventory inv = event.getInventory();
    	if(inv.getName().equalsIgnoreCase("§6Border Config")){
            ItemStack current = event.getCurrentItem();
            if (current == null) return;
            event.setCancelled(true);
            List<String> rules = ConfigData.get("Border");
            updateonClick(rules);
             
            switch (current.getItemMeta().getDisplayName()) {
            case "§cReduce Border":
            	rules.set(0, String.valueOf(stringtoint(rules.get(0)) -100));
            	updateonClick(rules);
            	break;
            case "§cReduce Timer":
            	rules.set(1, String.valueOf(stringtoint(rules.get(1)) -10));
            	updateonClick(rules);
            	break;
            case "§dAugment Border":
            	rules.set(0, String.valueOf(stringtoint(rules.get(0)) +100));
            	updateonClick(rules);
            	break;
            case "§dAugment Timer":
            	rules.set(1, String.valueOf(stringtoint(rules.get(1)) +10));
            	updateonClick(rules);
            	break;
            default:
                break;
        }
    	}
    }
    private ItemStack createMat(Material mat,String Display) {
    	ItemStack crea = new ItemStack(mat);
    	ItemMeta creaMeta = crea.getItemMeta();
    	creaMeta.setDisplayName(Display);
    	crea.setItemMeta(creaMeta);
    	return crea;
    }
    public void updateonClick(List<String> rules) {
        ConfigData.put("Border", rules);
        System.out.println(ConfigData);
        updateInventory();
    }
    public int stringtoint(String str) {
    	return Integer.parseInt(str);
    }

}
