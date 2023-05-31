package fr.farkas.Main.Characters.Chasseurs.WooChinjul;




import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;

public class WooChinjul extends Chasseurs {

    private static final String DESCRIPTION = "Woo Chinjul";
    private String name;
    private Boolean Eveil;

	
	public WooChinjul(Player player, String key) {
		super(player, Roles.WOOCHINJUL);
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + WooChinjul.getDescription());
        this.name = Roles.WOOCHINJUL;
        this.Eveil =false;
        
        //player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Light Power"));
	}
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
	public Boolean getEveil() {
		return Eveil;
	}
	public void setEveil (Player player) {
		Eveil = true;
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
	}
	public void addArrowIndicator(Player player, Player killer,Plugin plugin) {
	    Location killerLoc = killer.getLocation();
	    Vector direction = killerLoc.toVector().subtract(player.getLocation().toVector()).normalize();

	    String message = ChatColor.RED + "" + ChatColor.BOLD + ">> ";

	    if (direction.getX() < -0.5) {
	        message += "← ";
	    } else if (direction.getX() > 0.5) {
	        message += "→ ";
	    }
	    if (direction.getZ() < -0.5) {
	        message += "↑ ";
	    } else if (direction.getZ() > 0.5) {
	        message += "↓ ";
	    }

	    player.sendMessage(ChatColor.RED + "Killer direction: " + message);

	    // Schedule a task to clear the message after 10 seconds
	    Bukkit.getScheduler().runTaskLater(plugin, () -> {
	        player.sendMessage(ChatColor.RED + "Killer direction: ");
	    }, 200L);
	}




    public void getAbility(Player player) {
    	player.sendMessage("§2Tu joue Woo Chinjul Tu gagne avec les Chasseurs\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 1 pouvoir apres ton eveil :\n"
    			+ "-§2 tu a possede Speed 1 perma apres ton eveil\n"
    			+ "-§2 apres le premier kill de chaque jour tu obtient une fleche vers le kill\n"
    			+ "§6-------------------------");
    
    
    }

}
