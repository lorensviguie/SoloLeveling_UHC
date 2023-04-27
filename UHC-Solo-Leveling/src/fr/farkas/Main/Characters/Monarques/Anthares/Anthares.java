package fr.farkas.Main.Characters.Monarques.Anthares;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Monarques.Monarques;
import fr.farkas.Main.Characters.Solos.Solos;

public class Anthares extends Monarques {

	private static final String DESCRIPTION = "Anthares";
    private String name = "Anthares";
    
	
	public Anthares(Player player, String key) {
		super(player, "Anthares");
		player.sendMessage(ChatColor.GREEN + "You are now playing as " + Anthares.getDescription());
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
        
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public String getName() { // Add a getter method for the name field
        return name;
    }
    
    @Override
    public void isDying() {
    	Monarques.campToSolo();
    	
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
}
