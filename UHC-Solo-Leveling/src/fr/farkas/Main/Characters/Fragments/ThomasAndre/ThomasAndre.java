package fr.farkas.Main.Characters.Fragments.ThomasAndre;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Characters.Fragments.Fragments;

public class ThomasAndre extends Fragments {
	
    private static final String DESCRIPTION = "Thomas Andre";
    private String name;
    private Boolean transformation;

	public ThomasAndre(Player player, String key) {
        super(player, "ThomasAndre");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + ThomasAndre.getDescription());
        this.name = "ThomasAndre";
        this.transformation = false;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0)); // strength 1 for 30 seconds
        player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Transformation"));
        transformation = false;
	}
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    public void useability(Plugin plugin) {
    	Player player = getPlayer();
    	if (transformation) {
    		player.sendMessage("Transformation in cooldown");
    	}else {
    	if (player.getHealth()+10 > 20) {
    		player.setHealth(20);
    	}else {
    		player.setHealth(player.getHealth()+10);
    	}
    	this.transformation = true;
    	player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60*5, 0)); // resistance 2 for 60 seconds
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
    	player.sendMessage("§2Tu joue Thomas Andre Tu gagne avec les Fragments\n"
    			+ "§6-------------------------\n"
    			+ "§1Tu possede la liste des fragments\n"
    			+ "§9Tu possede l'effet Force Permanent\n"
    			+ "§5tu a un unique pouvoir:\n"
    			+ "§2Quand tu l'active tu te heal de 5Coeur et tu gagne resistance 2 pendant 5 Minutes tu a 15 Min de cooldown sur ta transformation\n"
    			+ "§6-------------------------");
    
    }

}
