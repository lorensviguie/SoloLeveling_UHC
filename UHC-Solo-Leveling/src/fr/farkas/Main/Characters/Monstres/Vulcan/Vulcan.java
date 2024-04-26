package fr.farkas.Main.Characters.Monstres.Vulcan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.General.GeneralVariable;

public class Vulcan extends Monstres{
	private static final String DESCRIPTION = "Vulcan";
	private int day ;
	private String name;

	public Vulcan(Player player, String key) {
		super(player, Roles.VULCAN);
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + Vulcan.getDescription());
        this.name = Roles.VULCAN;
        this.day = 0;
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,Integer.MAX_VALUE, 0, false, false));
        player.getInventory().addItem(Monstres.createMat(Material.NETHER_STAR, "§6Rage"));

	}
	
    public String getName() { // Add a getter method for the name field
        return name;
    }

    @Override
    public void isDying() {
    	
    }
    
    public void useAbility(Plugin plugin, int days) {
        Player player = getPlayer();
        Server server = Bukkit.getServer();
        long time = server.getWorld("Game").getTime();
        if (time < 12300 || time > 23850) {
            player.sendMessage(GeneralVariable.MessagePrefix + "Le soleil est là, vous êtes trop ébloui pour utiliser votre pouvoir.");
        } else if (!GeneralVariable.PortailDefenseSucces) {
            player.sendMessage(GeneralVariable.MessagePrefix + "Vous n'avez pas défendu votre portail.");
        } else {
        	if (this.day < days) {
        		this.day = days;
        		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, GeneralVariable.abilityDuration_Vulcan, 0, false, false));
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                	player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, GeneralVariable.Vulcan_weakduration, 0, false, false));
                    player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                }, GeneralVariable.abilityDuration_Vulcan);
        	}else {
        		player.sendMessage(GeneralVariable.MessagePrefix + "Tu a deja utiliser ton pouvoir cette nuit.");
        	}
            
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
