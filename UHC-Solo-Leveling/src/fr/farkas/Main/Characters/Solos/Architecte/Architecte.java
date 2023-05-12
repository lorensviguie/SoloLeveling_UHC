package fr.farkas.Main.Characters.Solos.Architecte;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
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
    		player.setNoDamageTicks(20*10);
    	}else {
    		this.dreamornot = true;
    		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
    		World dreamWorld = Bukkit.getWorld("world_the_end");
    		Location location = new Location(dreamWorld, 0, 63, 0);
    		player.teleport(location);
    	}
    }
    public void tpalltodream(Player playere) {
    	World world = Bukkit.getWorld("Game");
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
    
    public void BlackHearth(Player Archi, Player Sung) {
    	World world = Bukkit.getWorld("world_the_end");
    	Location location1 = new Location(world, 10, 63, 10);
    	Location location2 = new Location(world, -10, 63, -10);
    	Archi.teleport(location1);
    	Sung.teleport(location2);
    	
    }
    public void Igris(Player player) {
    	setCamp(6);
    	player.sendMessage("§5 Tu gagne Maintenant avec SungJinWoo et tu devient Igris");
    	player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
    	ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = diamondSword.getItemMeta();

        swordMeta.setDisplayName(ChatColor.YELLOW + "§bBaran Sword");

        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);

        diamondSword.setItemMeta(swordMeta);

        player.getInventory().addItem(diamondSword);;
    }
}
