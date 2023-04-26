package fr.farkas.Main.Characters.Monarques.Legia;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Monarques.Monarques;

public class Legia extends Monarques{

    private static final String DESCRIPTION = "Legia";
    private String name = "Legia";
	
	public Legia(Player player, String key) {
		super(player,"Legia");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + Legia.getDescription());
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
        

        // Suppr for real
        player.sendMessage("Power Locked");
        
        try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        player.sendMessage("Pass to Solo");
        this.turnToSolo();
        
        try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        player.sendMessage("Unlocked Power");
        this.unLockPower();
        
	}
	
    @Override
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void turnToSolo() {
    	super.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
    }
    
    public void unLockPower() {
    	super.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
    	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
    }
    public void getAbility(Player player) {
    	player.sendMessage("§2Tu Legia Tu gagne avec les Monarques\n"
    			+ "§6-------------------------\n"
    			+ "§9 tu possede Resistance et Faiblaisse Permanent"
    			+ "§9Tu perd Faiblesse si tu passe solo\n"
    			+ "§5tu a un unique pouvoir:\n"
    			+ "§6-------------------------");
    
    }

}
