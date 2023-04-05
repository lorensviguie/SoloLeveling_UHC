package fr.Farkas.uhcSL;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import mapGeneration.mapGeneration;

public class UHCListeners implements Listener {
    

    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
        	player.getInventory().setItem(9,getItem(Material.CHEST, "§pConfigurations"));
        }
    }
	@EventHandler
	 public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		ItemStack it = event.getItem();
		if(it == null)return;
		
		
		if(it.getType() == Material.CHEST && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§pConfigurations")) {
			Inventory inv = Bukkit.createInventory(null, 27, "§7Config");
			
			
			inv.setItem(11, getItem(Material.APPLE, "§pgenerate a new map"));
			
			
			
			player.openInventory(inv);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		if(current == null) return;
		if(inv.getName().equalsIgnoreCase("§7Config")){
			
			event.setCancelled(true);
			player.closeInventory();
			switch(current.getType()) {
			case APPLE:
				World world = player.getWorld();
				mapGeneration.generetemap(world);
				
				break;

				
			default:break;
			}

			
		}
		
	}
    
    public ItemStack getItem(Material material, String customDisplayName) {
    	ItemStack it = new ItemStack(material, 1);
    	ItemMeta itM = it.getItemMeta();
    	if(customDisplayName != null) itM.setDisplayName(customDisplayName);
    	it.setItemMeta(itM);
    	return it;
    }
}
