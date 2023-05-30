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
			inventory.setItem(0, createMat(Material.GOLD_BLOCK, "GoGunHee"));
		}else {
			inventory.setItem(0, createMat(Material.IRON_BLOCK, "GoGunHee"));
		}
		if (CharacterList.contains("ThomasAndre")) {
			inventory.setItem(1, createMat(Material.GOLD_BLOCK, "ThomasAndre"));
		}else {
			inventory.setItem(1, createMat(Material.IRON_BLOCK, "ThomasAndre"));
		}
		if (CharacterList.contains("ChristopherReed")) {
			inventory.setItem(2, createMat(Material.GOLD_BLOCK, "ChristopherReed"));
		}else {
			inventory.setItem(2, createMat(Material.IRON_BLOCK, "ChristopherReed"));
		}
		if (CharacterList.contains("Anthares")) {
			inventory.setItem(5, createMat(Material.GOLD_BLOCK, "Anthares"));
		}else {
			inventory.setItem(5, createMat(Material.IRON_BLOCK, "Anthares"));
		}
		if (CharacterList.contains("Legia")) {
			inventory.setItem(6, createMat(Material.GOLD_BLOCK, "Legia"));
		}else {
			inventory.setItem(6, createMat(Material.IRON_BLOCK, "Legia"));
		}
		if (CharacterList.contains("MonarqueBetes")) {
			inventory.setItem(7, createMat(Material.GOLD_BLOCK, "MonarqueBetes"));
		}else {
			inventory.setItem(7, createMat(Material.IRON_BLOCK, "MonarqueBetes"));
		}
		if (CharacterList.contains("Selner")) {
			inventory.setItem(10, createMat(Material.GOLD_BLOCK, "Selner"));
		}else {
			inventory.setItem(10, createMat(Material.IRON_BLOCK, "Selner"));
		}
		if (CharacterList.contains("SungJinWoo")) {
			inventory.setItem(25, createMat(Material.GOLD_BLOCK, "SungJinWoo"));
		}else {
			inventory.setItem(25, createMat(Material.IRON_BLOCK, "SungJinWoo"));
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
		if (CharacterList.contains("Architect")) {
			inventory.setItem(26, createMat(Material.GOLD_BLOCK, "Architect"));
		}else {
			inventory.setItem(26, createMat(Material.IRON_BLOCK, "Architect"));
		}
		if (CharacterList.contains("ChaHaeIn")) {
			inventory.setItem(11, createMat(Material.GOLD_BLOCK, "ChaHaeIn"));
		}else {
			inventory.setItem(11, createMat(Material.IRON_BLOCK, "ChaHaeIn"));
		}
		if (CharacterList.contains("LiuZhigang")) {
			inventory.setItem(12, createMat(Material.GOLD_BLOCK, "LiuZhigang"));
		}else {
			inventory.setItem(12, createMat(Material.IRON_BLOCK, "LiuZhigang"));
		}
		if (CharacterList.contains("WooChinjul")) {
			inventory.setItem(13, createMat(Material.GOLD_BLOCK, "WooChinjul"));
		}else {
			inventory.setItem(13, createMat(Material.IRON_BLOCK, "WooChinjul"));
		}
		if (CharacterList.contains("Vulcan")) {
			inventory.setItem(21, createMat(Material.GOLD_BLOCK, "Vulcan"));
		}else {
			inventory.setItem(21, createMat(Material.IRON_BLOCK, "Vulcan"));
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
