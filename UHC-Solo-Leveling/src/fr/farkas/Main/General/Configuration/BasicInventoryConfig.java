package fr.farkas.Main.General.Configuration;

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

import fr.farkas.Main.General.Configuration.Inventory.UHCBasicRules;
import fr.farkas.Main.General.World.MapManager;

public class BasicInventoryConfig {

    private Inventory inventory;
    private UHCBasicRules uhcBasicRules;
    private BorderConfig borderConfig;
    private CharacterSelection characterSelection;
    private Map<String, List<String>> configData;
        
    public BasicInventoryConfig(Map<String, List<String>> configData) {
		this.setConfigData(configData);
		this.uhcBasicRules = new UHCBasicRules(configData);
		this.borderConfig = new BorderConfig(configData);
		this.characterSelection = new CharacterSelection(configData);
		
		characterSelection.createinventory();
		uhcBasicRules.createInventory();
		borderConfig.createInventory();

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
        playerHeadMeta.setDisplayName(ChatColor.GOLD + "check world");
        playerHeadMeta.setLore(Arrays.asList(ChatColor.GRAY + "Tp to world"));
        playerHead.setItemMeta(playerHeadMeta);
        inventory.setItem(3, playerHead);
    }
    public void editConfig(Player player) {
        openConfig(player);
    }

    public void BasicInventoryClick(InventoryClickEvent event,MapManager mapManager) {
    		Inventory inv = event.getInventory();
        	Player player = (Player) event.getWhoClicked();
        	ItemStack current = event.getCurrentItem();
        	event.setCancelled(true);
        	player.closeInventory();
    		if(inv.getName().equalsIgnoreCase("§6UHC Basic Rules")){
    			uhcBasicRules.UHCRulesClick(event);
    			uhcBasicRules.openConfig(player);
    		}
    		if(inv.getName().equalsIgnoreCase("§6Border Config")){
    			borderConfig.BorderConfigClick(event);
    			borderConfig.openConfig(player);
    		}
    		if(inv.getName().equalsIgnoreCase("§6Character Liste")) {
    			characterSelection.CharacterListclick(event);
    			characterSelection.openConfig(player);
    		}
		switch(current.getType()) {
		case DIAMOND:

			uhcBasicRules.openConfig(player);

			break;
		case DARK_OAK_FENCE:
			borderConfig.openConfig(player);
			break;
		case IRON_SWORD:
			characterSelection.openConfig(player);
			break;
		case SHEARS:
			mapManager.RegenArea(player);
			break;
		default:
			break;
		}
    }
	public Map<String, List<String>> getConfigData() {
		return configData;
	}
	public void setConfigData(Map<String, List<String>> configData) {
		this.configData = configData;
	}
}

