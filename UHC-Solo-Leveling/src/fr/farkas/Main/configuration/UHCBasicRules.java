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

public class UHCBasicRules {

    private final Map<String, List<String>> ConfigData;
    private Inventory inventory;

    public UHCBasicRules(Map<String, List<String>> ConfigData) {
        this.ConfigData = ConfigData;
    }

    public void createInventory() {
        inventory = Bukkit.createInventory(null, 9,"§6UHC Basic Rules");
        updateInventory();
    }
    public void openConfig(Player player) {
        player.openInventory(inventory);
    }

    private void updateInventory() {
        List<String> rules = ConfigData.get("UHCBasicRules");
        if (rules.contains("NightVision")) {    	
        	inventory.setItem(3, createMat(Material.STAINED_CLAY, "§aNight Vision: ON"));
        } else {
        	inventory.setItem(3, createMat(Material.RED_SANDSTONE, "§4Night Vision: OFF"));
        }
        if (rules.contains("SafeMiner")) {
        	inventory.setItem(1, createMat(Material.IRON_PICKAXE, "§aSafe Miner: ON"));
        }else {
        	inventory.setItem(1, createMat(Material.WOOD_PICKAXE, "§4Safe Miner: OFF"));
        }
        if (rules.contains("StarterKit")) {
        	inventory.setItem(2, createMat(Material.CHEST, "§aSarter Kit: ON"));
        }else {
        	inventory.setItem(2, createMat(Material.ENDER_CHEST, "§4Starter Kit: OFF"));
        }
    }


    public void UHCRulesClick(InventoryClickEvent event) {
    	Inventory inv = event.getInventory();
    	System.out.println(inv.getName());
    	if(inv.getName().equalsIgnoreCase("§6UHC Basic Rules")){
            ItemStack current = event.getCurrentItem();
            if (current == null) return;
            event.setCancelled(true);

            List<String> rules = ConfigData.get("UHCBasicRules");
            System.out.println(current.getType());
            switch (current.getType()) {
                case WOOD_PICKAXE:
                    rules.add("SafeMiner");
                    updateonClick(rules);
                    break;       
                case IRON_PICKAXE:
                    rules.remove("SafeMiner");
                    updateonClick(rules);
                    break;
                case RED_SANDSTONE:
                    rules.add("NightVision");
                    updateonClick(rules);
                    break;
                case STAINED_CLAY:
                	rules.remove("NightVision");
                    updateonClick(rules);
                    break;
                case CHEST:
                	rules.remove("StarterKit");
                    updateonClick(rules);
                    break;
                case ENDER_CHEST:
                	rules.add("StarterKit");
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
   private void updateonClick(List<String> rules) {
       ConfigData.put("UHCBasicRules", rules);
       System.out.println(ConfigData);
       updateInventory();
   }
}
