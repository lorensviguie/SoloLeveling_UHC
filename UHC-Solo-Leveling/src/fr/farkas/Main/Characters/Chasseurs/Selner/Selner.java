package fr.farkas.Main.Characters.Chasseurs.Selner;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Chasseurs.Chasseurs;

public class Selner extends Chasseurs {
    private static final String DESCRIPTION = "Selner";
    private String name;
    private int day;
    
	public Selner(Player player, String key) {
		super(player, "Selner");
		player.sendMessage(ChatColor.GREEN + "You are now playing as " + this.getDescription());
        this.name = "Selner";
		this.day = 0;
		// TODO Auto-generated constructor stub
	}
	
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void eveilAbility(Player player,Plugin plugin) {
    	
    }
    
    public void makeInvisibleIfNoArmor(Player player,int days) {
        if (hasArmor(player)) {
        	if (days == day) {
        		player.sendMessage("Tu a deja été invisible cette Journée");
        	}
        	else {
        		day = days;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 5, 0));
        }
    	}
    }
    private boolean hasArmor(Player player) {
        ItemStack[] armorContents = player.getInventory().getArmorContents();
        for (ItemStack armor : armorContents) {
            if (armor != null && armor.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }

    public void getAbility(Player player) {
    	player.sendMessage("§2Tu joue Selner Tu gagne avec les Chasseurs\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 2 pouvoir :\n"
    			+ "-§2 tu peux devenir invisible pendant 5 min une fois par jour en enlevant ton armure\n"
    			+ "-§2 Une fois par jour tu peux marquer une personne et si tu la touche tu obtiendra son aura\n"
    			+ "§4Une fois dans la partie du declencher l'eveil d'une personne\n"
    			+ "§6-------------------------");
    
    
    }

}
