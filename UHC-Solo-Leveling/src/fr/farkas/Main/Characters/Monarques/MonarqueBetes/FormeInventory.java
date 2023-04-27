package fr.farkas.Main.Characters.Monarques.MonarqueBetes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.farkas.Main.Characters.Monarques.Monarques;

public class FormeInventory {
	
	private Inventory inventory;
	private MonarqueBetes monarqueBetes;
	
	public FormeInventory(MonarqueBetes monarqueBetes) {
		this.monarqueBetes = monarqueBetes;
		
	}
	
    public void openFormInventory() {
    	this.createInventory();
        this.monarqueBetes.getPlayer().openInventory(inventory);
    }
	
	public void createInventory() {
        // Create the inventory
        this.inventory = Bukkit.createInventory(null, 9,"§6Forme Inventory");
        

		ItemStack humainForme = getItem(Material.ACACIA_DOOR_ITEM, "§6Humain");
		this.inventory.setItem(Monarques.areSolo && !monarqueBetes.isInUltimeCooldown() ? 1:2, humainForme);

        ItemStack loupForme = getItem(Material.BIRCH_DOOR_ITEM, "§6Loup");
        this.inventory.setItem(Monarques.areSolo && !monarqueBetes.isInUltimeCooldown() ? 3:4, loupForme);

        ItemStack oursForme = getItem(Material.DARK_OAK_DOOR_ITEM, "§6Ours");
        this.inventory.setItem(Monarques.areSolo && !monarqueBetes.isInUltimeCooldown() ? 5:6, oursForme);

        if(Monarques.areSolo && !monarqueBetes.isInUltimeCooldown()) {
            ItemStack ultimeForme = getItem(Material.IRON_DOOR, "§6Ultime");
            this.inventory.setItem(7, ultimeForme);
        }
    }
	


    public void FormeInventoryClick(InventoryClickEvent event) {
			Inventory inv = event.getInventory();
	    	Player player = (Player) event.getWhoClicked();
	    	ItemStack current = event.getCurrentItem();
	    	event.setCancelled(true);
	    	System.out.println(inv.getName());
	    	player.closeInventory();
	    	
	    	if(monarqueBetes.getForme() != "ultime") {
				switch(current.getType()) {
				case ACACIA_DOOR_ITEM:
					System.out.print("humain");
					this.monarqueBetes.turnToHumain();
					break;
				case BIRCH_DOOR_ITEM:
					System.out.print("loup");
					this.monarqueBetes.turnToLoup();
					break;
				case DARK_OAK_DOOR_ITEM:
					System.out.print("ours");
					this.monarqueBetes.turnToOurs();
					break;
				case IRON_DOOR:
					System.out.print("ultime");
					this.monarqueBetes.turnToUltime();
					break;
				default:
					System.out.print(current.getType());
					break;
				}
	    	}else {
	    		player.sendMessage("§cYou are in your ultime forme, you can't change form now");
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
