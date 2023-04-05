package fr.farkas.Main.configuration;

import java.util.Arrays;
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

public class BasicInventoryConfig {

    private Inventory inventory;
    private UHCBasicRules uhcBasicRules;
    private Map<String, List<String>> configData;
        
    public BasicInventoryConfig(Map<String, List<String>> configData) {
		this.setConfigData(configData);
		this.uhcBasicRules = new UHCBasicRules(configData);
		uhcBasicRules.createInventory();
	}
    public void openConfig(Player player) {
        player.openInventory(inventory);
    }

	public void createInventory() {
        // Create the inventory
        inventory = Bukkit.createInventory(null, 9,"§eLocked Inventory");

        // Add the items to the inventory
        ItemStack charSelect = new ItemStack(Material.IRON_SWORD);
        ItemMeta charSelectMeta = charSelect.getItemMeta();
        charSelectMeta.setDisplayName(ChatColor.GOLD + "Character Selection");
        charSelectMeta.setLore(Arrays.asList(ChatColor.GRAY + "Select your favorite character!"));
        charSelect.setItemMeta(charSelectMeta);
        inventory.setItem(0, charSelect);

        ItemStack uhcSetup = new ItemStack(Material.DIAMOND);
        ItemMeta uhcSetupMeta = uhcSetup.getItemMeta();
        uhcSetupMeta.setDisplayName(ChatColor.GOLD + "UHC Setup");
        uhcSetupMeta.setLore(Arrays.asList(ChatColor.GRAY + "Set up your UHC game!"));
        uhcSetup.setItemMeta(uhcSetupMeta);
        inventory.setItem(1, uhcSetup);

        ItemStack border = new ItemStack(Material.DARK_OAK_FENCE);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.setDisplayName(ChatColor.GOLD + "Border");
        borderMeta.setLore(Arrays.asList(ChatColor.GRAY + "Configure the world border!"));
        border.setItemMeta(borderMeta);
        inventory.setItem(2, border);

        ItemStack playerHead = new ItemStack(Material.SHEARS);
        ItemMeta playerHeadMeta = playerHead.getItemMeta();
        playerHeadMeta.setDisplayName(ChatColor.GOLD + "FarkasGL's Head");
        playerHeadMeta.setLore(Arrays.asList(ChatColor.GRAY + "Get the head of FarkasGL!"));
        playerHead.setItemMeta(playerHeadMeta);
        inventory.setItem(3, playerHead);
    }
    public void editConfig(Player player) {
        openConfig(player);
    }

    public void BasicInventoryClick(InventoryClickEvent event) {
    		Inventory inv = event.getInventory();
        	Player player = (Player) event.getWhoClicked();
        	ItemStack current = event.getCurrentItem();
        	event.setCancelled(true);
        	player.closeInventory();
    		if(inv.getName().equalsIgnoreCase("§6UHC Basic Rules")){
    			uhcBasicRules.UHCRulesClick(event);
    			uhcBasicRules.openConfig(player);
    		}
		switch(current.getType()) {
		case DIAMOND:
			System.out.println("Canard");
			uhcBasicRules.openConfig(player);
			break;
		case ANVIL:
			break;
			
		default:break;
		}
    }
	public Map<String, List<String>> getConfigData() {
		return configData;
	}
	public void setConfigData(Map<String, List<String>> configData) {
		this.configData = configData;
	}
}

