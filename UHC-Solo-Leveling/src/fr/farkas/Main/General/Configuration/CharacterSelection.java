package fr.farkas.Main.General.Configuration;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CharacterSelection {

	private final Map<String, List<String>> ConfigData;
	private Inventory inventory;
	
	public CharacterSelection(Map<String, List<String>> configData) {
		this.ConfigData = configData;
	}
	public void createinventory() {
		inventory = Bukkit.createInventory(null, 27,"§6Character Liste");
		updateInventory();
	}
    public void openConfig(Player player) {
        player.openInventory(inventory);
    }
	public void updateInventory() {
		List<String> CharacterList = ConfigData.get("CharacterList");
		if (CharacterList.contains("GoGunHee")) {
			inventory.setItem(1, createMat(Material.GOLD_BLOCK, "GoGunHee"));
		}else {
			inventory.setItem(1, createMat(Material.IRON_BLOCK, "GoGunHee"));
		}
		if (CharacterList.contains("ThomasAndre")) {
			inventory.setItem(2, createMat(Material.GOLD_BLOCK, "ThomasAndre"));
		}else {
			inventory.setItem(2, createMat(Material.IRON_BLOCK, "ThomasAndre"));
		}
		if (CharacterList.contains("Legia")) {
			inventory.setItem(10, createMat(Material.GOLD_BLOCK, "Legia"));
		}else {
			inventory.setItem(10, createMat(Material.IRON_BLOCK, "Legia"));
		}
		if (CharacterList.contains("MonarqueBetes")) {
			inventory.setItem(11, createMat(Material.GOLD_BLOCK, "MonarqueBetes"));
		}else {
			inventory.setItem(11, createMat(Material.IRON_BLOCK, "MonarqueBetes"));
		}
		if (CharacterList.contains("Selner")) {
			inventory.setItem(19, createMat(Material.GOLD_BLOCK, "Selner"));
		}else {
			inventory.setItem(19, createMat(Material.IRON_BLOCK, "Selner"));
		}
		if (CharacterList.contains("Shae")) {
			inventory.setItem(20, createMat(Material.GOLD_BLOCK, "Shae"));
		}else {
			inventory.setItem(20, createMat(Material.IRON_BLOCK, "Shae"));
		}
		if (CharacterList.contains("Beru")) {
			inventory.setItem(22, createMat(Material.GOLD_BLOCK, "Beru"));
		}else {
			inventory.setItem(22, createMat(Material.IRON_BLOCK, "Beru"));
		}
		if (CharacterList.contains("Baruka")) {
			inventory.setItem(23, createMat(Material.GOLD_BLOCK, "Baruka"));
		}else {
			inventory.setItem(23, createMat(Material.IRON_BLOCK, "Baruka"));
		}
		if (CharacterList.contains("Architecte")) {
			inventory.setItem(26, createMat(Material.GOLD_BLOCK, "Architecte"));
		}else {
			inventory.setItem(26, createMat(Material.IRON_BLOCK, "Architecte"));
		}
		if (CharacterList.contains("ChaHaeIn")) {
			inventory.setItem(7, createMat(Material.GOLD_BLOCK, "ChaHaeIn"));
		}else {
			inventory.setItem(7, createMat(Material.IRON_BLOCK, "ChaHaeIn"));
		}
	}
    public void CharacterListclick(InventoryClickEvent event) {
    	Inventory inv = event.getInventory();
    	System.out.println(inv.getName());
    	if(inv.getName().equalsIgnoreCase("§6Character liste")){
            ItemStack current = event.getCurrentItem();
            if (current == null) return;
            event.setCancelled(true);
            List<String> rules = ConfigData.get("CharacterList");
            System.out.println(current.getItemMeta().getDisplayName());
            switch (current.getType()) {
                case IRON_BLOCK:
                    rules.add(current.getItemMeta().getDisplayName());
                    updateonClick(rules);
                    break;       
                case GOLD_BLOCK:
                    rules.remove(current.getItemMeta().getDisplayName());
                    updateonClick(rules);
                    break;
                default:
                    break;
            }
    	}
    }
    private void updateonClick(List<String> characterList) {
        ConfigData.put("CharacterList", characterList);
        System.out.println(ConfigData);
        updateInventory();
    }
	
	
    private ItemStack createMat(Material mat,String Display) {
    	ItemStack crea = new ItemStack(mat);
    	ItemMeta creaMeta = crea.getItemMeta();
    	creaMeta.setDisplayName(Display);
    	crea.setItemMeta(creaMeta);
    	return crea;
    }
}
