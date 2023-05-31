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

import fr.farkas.Main.Characters.Roles;

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
		
		Roles.selectableRoles.forEach((role,place) -> {
			if (CharacterList.contains(role)) {
				inventory.setItem(place, createMat(Material.GOLD_BLOCK, role));
			}else {
				inventory.setItem(place, createMat(Material.IRON_BLOCK, role));
			}
			
		});
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
