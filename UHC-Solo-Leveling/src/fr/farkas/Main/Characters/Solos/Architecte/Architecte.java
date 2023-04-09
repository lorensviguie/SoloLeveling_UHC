package fr.farkas.Main.Characters.Solos.Architecte;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Fragments.Fragments;
import fr.farkas.Main.Characters.Solos.Solos;

public class Architecte extends Solos {

	
    private static final String DESCRIPTION = "L'architect";
    private String name;
    private Boolean dreamornot;
	public Architecte(Player player, String key) {
		super(player, key);
		this.name = "Architecte";
		player.setMaxHealth(24);
		player.setHealth(24);
		this.dreamornot = false;
		player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§0TP Yourself to DreamWorld"));
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
		// TODO Auto-generated constructor stub
	}
	
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    public void tptodreamWorld(Player player) {
    	if (dreamornot) {
    	player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    	player.removePotionEffect(PotionEffectType.SPEED);
    	World world = Bukkit.getWorld("world");
    	int x = (int)(Math.random() * 1000) - 500;
    	int z = (int)(Math.random() * 1000) - 500;
    	Location loc = new Location(world, x, 150, z);
    	player.teleport(loc);
    	player.setNoDamageTicks(150);
    	this.dreamornot = false;
    	}else {
    		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    	World dreamWorld = Bukkit.getWorld("world_the_end");
    	Location location = new Location(dreamWorld, 0, 63, 0);
    	player.teleport(location);
    	this.dreamornot = true;
    	}
    }
}
