package fr.farkas.Main.Characters.Monarques.MonarqueBetes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Monarques.Monarques;

public class MonarqueBetes extends Monarques{
	
	private String currentForme;
	private FormeInventory formeInventory;
    private String name = "MonarqueBetes";

    private static final String DESCRIPTION = "Monarque des Bêtes";
	
	public MonarqueBetes(Player player, String key) {
		super(player,"MonarqueBetes");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + this.getDescription());
		super.getPlayer().getInventory().addItem(createMat(Material.NETHER_STAR, "§6Formes"));
		this.currentForme = "humain";
		this.formeInventory = new FormeInventory(this);
	}

    @Override
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void turnToHumain() {
    	switch (this.currentForme){
	    	case "loup":
	        	super.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	        	super.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	    		break;
	    	case "ours":
	        	super.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	        	super.getPlayer().setMaxHealth(20);
	    		break;
			default:
				break;	
    	}
    	this.currentForme = "humain";
		super.getPlayer().sendMessage("You tooked Human forme");
    }
    
    public FormeInventory getFormeInventory() {
    	return this.formeInventory;
    }
    
    public void turnToLoup() {
    	switch (this.currentForme){
	    	case "ours":
	    		super.getPlayer().sendMessage("You tooked Wolf forme");
	        	super.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	        	super.getPlayer().setMaxHealth(20);
	    		break;
			default:
				break;	
    	}
    	this.currentForme = "loup";
		super.getPlayer().sendMessage("You tooked Wolf forme");
    	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    }
    
    public void turnToOurs() {
    	switch (this.currentForme){
	    	case "loup":
	        	super.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	        	super.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	    		break;
			default:
				break;	
    	}
    	this.currentForme = "ours";
		super.getPlayer().sendMessage("You tooked Bear forme");
    	super.getPlayer().setMaxHealth(26);
    	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
    	if(super.getPlayer().getHealth() == 20) {
        	super.getPlayer().setHealth(20);
    	}
    }
    

    private ItemStack createMat(Material mat,String Display) {
    	ItemStack crea = new ItemStack(mat);
    	ItemMeta creaMeta = crea.getItemMeta();
    	creaMeta.setDisplayName(Display);
    	crea.setItemMeta(creaMeta);
    	return crea;
    }
}
