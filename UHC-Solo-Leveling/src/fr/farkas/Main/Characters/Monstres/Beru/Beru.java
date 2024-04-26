package fr.farkas.Main.Characters.Monstres.Beru;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.General.GeneralVariable;

public class Beru extends Monstres{
    private static final String DESCRIPTION = "Beru";
    private String name;
    private int power;
	public Beru(Player player, String key) {
		super(player, Roles.BERU);
        player.sendMessage(GeneralVariable.MessagePrefix+"You are now playing as " + Beru.getDescription());
        this.name = Roles.BERU;
        this.power = 3;
        this.getAbility(player);
        player.getInventory().addItem(Monstres.createMat(Material.NETHER_STAR, "§6Evovle"));
	}
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void useability(Plugin plugin) {
    	Player player = getPlayer();
    	if (power == 3) {//3 = force
    		power = 1;
    		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,Integer.MAX_VALUE, 0, false, false));
    	}else if (power == 2) {//2 = speed
    		player.removePotionEffect(PotionEffectType.SPEED);
    		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,Integer.MAX_VALUE, 0, false, false));
    		power = 3;
    	}else if (power == 1) {//1 = resistance
    		player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE, 0, false, false));
    		power = 2;
    	}
    }
    
    public void getAbility(Player player) {
    	player.sendMessage(GeneralVariable.MessagePrefix+"§bTu joue §6Beru §bTu gagne avec les Monstres\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 2 pouvoir :\n"
    			+ "§b- tu a ta Nether Star qui te permet de changer entre trois effets force resi speed\n"
    			+ "-§b tu a 10% de chance de donner poisson quand tu frappe une personne\n"
    			+ "§4 avec la commande /sl liste tu peux voir la liste des tes aliés"
    			+ "§6-------------------------");
    
    
    }

}
