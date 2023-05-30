package fr.farkas.Main.Characters.Monstres.Vulcan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.General.GeneralVariable;

public class Vulcan extends Monstres{
	private static final String DESCRIPTION = "Vulcan";
	private String name;

	public Vulcan(Player player, String key) {
		super(player, "Vulcan");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + Vulcan.getDescription());
        this.name = "Vulcan";
        
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,Integer.MAX_VALUE, 0, false, false));
        player.getInventory().addItem(Monstres.createMat(Material.NETHER_STAR, "§6Rage"));

	}
	
    public String getName() { // Add a getter method for the name field
        return name;
    }

    @Override
    public void isDying() {
    	
    }
    
    public void useability(Plugin plugin) {
    	Player player = getPlayer();

		Server server = Bukkit.getServer();
    	long time = server.getWorld("Game").getTime();
	    if (time < 12300 || time > 23850) {
	    	player.sendMessage(ChatColor.GREEN + "Sun is here your power don't activated");
	    }else {
			player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,GeneralVariable.abilityDuration_Vulcan, 0, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,GeneralVariable.abilityDuration_Vulcan, 0, false, false));
	    }
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void getAbility(Player player) {
    	player.sendMessage("§2Tu joue Vulcan Tu gagne avec les Monstres\n"
    			+ "§6-------------------------\n"
    			+ "§6-------------------------");
    
    }

}
