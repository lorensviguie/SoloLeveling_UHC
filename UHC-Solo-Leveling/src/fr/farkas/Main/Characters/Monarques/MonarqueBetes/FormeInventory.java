package fr.farkas.Main.Characters.Monarques.MonarqueBetes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FormeInventory {
	
	private Inventory inventory;
	private MonarqueBetes monarqueBetes;
	
	public FormeInventory(MonarqueBetes monarqueBetes) {
		this.monarqueBetes = monarqueBetes;
		this.createInventory();
		
	}
	
    public void openFormInventory() {
        this.monarqueBetes.getPlayer().openInventory(inventory);
    }
	
	public void createInventory() {
        // Create the inventory
        this.inventory = Bukkit.createInventory(null, 9,"§6Forme Inventory");
        

		ItemStack humainForme = getItem(Material.ACACIA_DOOR_ITEM, "§6Humain");
		this.inventory.setItem(2, humainForme);

        ItemStack loupForme = getItem(Material.BIRCH_DOOR_ITEM, "§6Loup");
        this.inventory.setItem(4, loupForme);

        ItemStack oursForme = getItem(Material.DARK_OAK_DOOR_ITEM, "§6Ours");
        this.inventory.setItem(6, oursForme);
    }
	


    public void FormeInventoryClick(InventoryClickEvent event) {
			Inventory inv = event.getInventory();
	    	Player player = (Player) event.getWhoClicked();
	    	ItemStack current = event.getCurrentItem();
	    	event.setCancelled(true);
	    	System.out.println(inv.getName());
	    	player.closeInventory();

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
			default:
				System.out.print(current.getType());
				break;
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
