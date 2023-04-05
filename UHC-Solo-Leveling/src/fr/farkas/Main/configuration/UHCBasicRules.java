package fr.farkas.Main.configuration;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        boolean nightVision = rules.contains("NightVision");

        if (nightVision) {
            ItemStack greenPane = new ItemStack(Material.GREEN_RECORD);
            ItemMeta greenMeta = greenPane.getItemMeta();
            greenMeta.setDisplayName(ChatColor.GREEN + "Night Vision: On");
            greenPane.setItemMeta(greenMeta);
        	
        	inventory.setItem(3, greenPane);
        } else {
        	
            ItemStack redPane = new ItemStack(Material.RED_SANDSTONE);
            ItemMeta redMeta = redPane.getItemMeta();
            redMeta.setDisplayName(ChatColor.RED + "Night Vision: Off");
            redPane.setItemMeta(redMeta);
        	inventory.setItem(3, redPane);
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
            boolean nightVision = rules.contains("NightVision");

            switch (current.getType()) {
                case GREEN_RECORD:
                case RED_SANDSTONE:
                    if (nightVision) {
                        rules.remove("NightVision");
                    } else {
                        rules.add("NightVision");
                    }
                    ConfigData.put("UHCBasicRules", rules);
                    System.out.println(ConfigData);
                    updateInventory();
                    break;
                default:
                    break;
            }
        }
    }
}
