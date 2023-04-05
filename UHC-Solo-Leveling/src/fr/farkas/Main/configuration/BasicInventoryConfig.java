package fr.farkas.Main.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BasicInventoryConfig implements Listener {

    private Inventory inventory;
    private Map<String, List<String>> configData;
        
    public BasicInventoryConfig(Map<String, List<String>> configData) {
		this.configData = configData;
	}
    public void openConfig(Player player) {
        player.openInventory(inventory);
    }

	public void createInventory() {
        // Create the inventory
        inventory = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "Locked Inventory");

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

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inventory)) {
        	Player player = (Player) event.getWhoClicked();
        	ItemStack current = event.getCurrentItem();
        	System.out.println("COUCOU");
        	event.setCancelled(true);
        	player.closeInventory();
		switch(current.getType()) {
		case DIAMOND:
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give @"+ player.getDisplayName()+"minecraft:diamond 2");
			
			break;
		case ANVIL:
			break;
			
		default:break;
		}
        }
    }
}

