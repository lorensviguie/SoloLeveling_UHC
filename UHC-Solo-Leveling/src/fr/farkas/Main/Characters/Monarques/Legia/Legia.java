package fr.farkas.Main.Characters.Monarques.Legia;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Monarques.Monarques;

public class Legia extends Monarques{

    private static final String DESCRIPTION = "Legia";
	
	public Legia(Player player, String key) {
		super(player,key);
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
		
	}

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void turnToSolo() {
    	super.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
    }

}
