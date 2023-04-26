package fr.farkas.Main.Characters.Solos.Architecte;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
		super(player, "Architect");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + Architecte.getDescription());
		this.name = "Architect";
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
    	System.out.println("FEUR");
    	if (dreamornot) {
    		this.dreamornot = false;
    		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    		player.removePotionEffect(PotionEffectType.SPEED);
    		World world = Bukkit.getWorld("Game");
    		int x = (int)(Math.random() * 1000) - 500;
    		int z = (int)(Math.random() * 1000) - 500;
    		Location loc = new Location(world, x, 150, z);
    		player.teleport(loc);
    		player.setNoDamageTicks(20*5);
    	}else {
    		this.dreamornot = true;
    		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    		World dreamWorld = Bukkit.getWorld("world_the_end");
    		Location location = new Location(dreamWorld, 0, 63, 0);
    		player.teleport(location);
    	}
    }
    public void tpalltodream(Player playere) {
    	World world = Bukkit.getWorld("Area");
    	List<Player> players = world.getPlayers();
    	world = Bukkit.getWorld("world_the_end");
        for (Player player : players) {
            Location playerLocation = player.getLocation();
            double distance = playere.getLocation().distance(playerLocation);
            if (distance <= 20) {
            	Location location = new Location(world, 0, 63, 0);
                player.teleport(location);
            }
        }
    }
}
