package fr.farkas.Main.configuration;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UHCBasicRules implements Listener {

    private final Map<String, List<String>> ConfigData;
    private Inventory inventory;

    public UHCBasicRules(Map<String, List<String>> ConfigData) {
        this.ConfigData = ConfigData;
    }

    public Inventory createInventory() {
        inventory = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "UHC Basic Rules");
        updateInventory();
        return inventory;
    }

    private void updateInventory() {
        List<String> rules = ConfigData.get("UHCBasicRules");
        boolean nightVision = rules.contains("NightVision");

        // Create the glass panes
        ItemStack greenPane = new ItemStack(Material.GREEN_RECORD);
        ItemMeta greenMeta = greenPane.getItemMeta();
        greenMeta.setDisplayName(ChatColor.GREEN + "Night Vision: " + (nightVision ? "On" : "Off"));
        greenPane.setItemMeta(greenMeta);

        ItemStack redPane = new ItemStack(Material.RED_SANDSTONE);
        ItemMeta redMeta = redPane.getItemMeta();
        redMeta.setDisplayName(ChatColor.RED + "Night Vision: " + (nightVision ? "On" : "Off"));
        redPane.setItemMeta(redMeta);

        // Add the glass panes to the inventory
        inventory.setItem(3, nightVision ? greenPane : redPane);
        inventory.setItem(5, nightVision ? redPane : greenPane);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inventory)) {
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
                    updateInventory();
                    break;
                default:
                    break;
            }
        }
    }
}
