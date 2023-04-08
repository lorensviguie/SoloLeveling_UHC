package fr.farkas.Main.Characters.Fragments.GoGunHee;

import java.time.temporal.WeekFields;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Characters.Fragments.Fragments;

public class GoGunHee extends Fragments {

    private static final String DESCRIPTION = "Go Gun Hee";
    private String name;
    private int timer;
    private Boolean transformation;

    public GoGunHee(Player player, String key) {
        super(player, key);
        this.name = "GoGunHee";
        this.transformation = false;
        player.getInventory().addItem(createMat(Material.NETHER_STAR, "§6Transformation"));
    }

    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    private ItemStack createMat(Material mat,String Display) {
    	ItemStack crea = new ItemStack(mat);
    	ItemMeta creaMeta = crea.getItemMeta();
    	creaMeta.setDisplayName(Display);
    	crea.setItemMeta(creaMeta);
    	return crea;
    }
    
    public void useability(Plugin plugin) {
    	Player player = getPlayer();
    	if (player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
    		player.sendMessage("§6 tu dois recuperer PAPY");
    	}else {
    	if (!this.transformation) {
    		player.sendMessage("§6 You Activate Your Transformation");
        	player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*30*3, 0)); // strength 1 for 30 seconds
        	player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*30*3, 0)); // resistance 2 for 60 seconds
        	player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*30*3, 0)); // speed 1 for 30 seconds
        	this.transformation = true;
        	player.setMaxHealth(player.getMaxHealth() - 2); // decreases maximum health by 2 hearts
        	player.setHealth(player.getMaxHealth()); // set current health to the new maximum health

        	new BukkitRunnable() {
            	@Override
            	public void run() {
                	if (transformation == true) {
                		transformation = false;
                		useability(plugin);
                	}
            	}
        	}.runTaskLater(plugin, 20*30*3); // after 1 minutes 30
    	}else {
    		player.sendMessage("§6 You Disabled Your Transformation");
    		this.transformation = false;
    		player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
    		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    		player.removePotionEffect(PotionEffectType.SPEED);
    		player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*60*5, 0));
    	}
    	}
    	}
}