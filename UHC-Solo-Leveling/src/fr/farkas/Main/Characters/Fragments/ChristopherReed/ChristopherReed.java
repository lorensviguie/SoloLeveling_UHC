package fr.farkas.Main.Characters.Fragments.ChristopherReed;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Characters.Fragments.Fragments;

public class ChristopherReed extends Fragments {

    private static final String DESCRIPTION = "Christopher Reed";
    private String name;
    private Boolean transformation;
    private Boolean Fire;
	
	public ChristopherReed(Player player, String key) {
		super(player, "Christopher Reed");
        this.name = "ChristopherReed";
        this.transformation = false;
        this.Fire = false;
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + ChristopherReed.getDescription());
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
        player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Transformation"));
	}
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
	public Boolean getFire() {
		return Fire;
	}
	public void setFire(Player player) {
		if (Fire) {
			Fire = false;
			
		}else {
			Fire = true;
		}
		player.sendMessage("Fire is "+getFire());
	}
    public void useability(Plugin plugin) {
    	Player player = getPlayer();
    	if (transformation) {
    		player.sendMessage("Transformation in cooldown");
    	}else {
    	this.transformation = true;
    	player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60*5, 0, false, false)); // resistance 1 for 60 seconds
    	new BukkitRunnable() {
        	@Override
        	public void run() {
        		cooldown(plugin);
        	}
    	}.runTaskLater(plugin, 20*60*5); // after 5 minutes
    	}
    }
    private void cooldown(Plugin plugin) {
    	new BukkitRunnable() {
        	@Override
        	public void run() {
        		transformation = false;
        	}
    	}.runTaskLater(plugin, 20*60*15); // after 15 minutes
    }

    public void getAbility(Player player) {
    	player.sendMessage("§2Tu joue Christopher Reed Tu gagne avec les Fragments\n"
    			+ "§6-------------------------\n"
    			+ "§1Tu possede la liste des fragments\n"
    			+ "§5tu a un unique pouvoir: qui te donne resi 1 pendant 5 min avec 15 min de cooldown\n"
    			+ "§2/sl fire pour activer ou non fire aspect \n"
    			+ "Tu possede force et fire resistance perma\n"
    			+ "§6-------------------------");
    
    }
}
