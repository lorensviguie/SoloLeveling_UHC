package fr.farkas.Main.Characters.Chasseurs.WooChinjul;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import fr.farkas.Main.Start;
import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.General.GeneralVariable;

public class WooChinjul extends Chasseurs  {

    private static final String DESCRIPTION = "Woo Chinjul";
    private String name;
    private Boolean Eveil;
    private String Killer;
    private int day;

	
	public WooChinjul(Player player, String key) {
		super(player, Roles.WOOCHINJUL);
        player.sendMessage(GeneralVariable.MessagePrefix+"Tu joue mtn en tant que §6" + WooChinjul.getDescription());
        day = 0;
        super.setRank("E");
        getAbility(player);
        this.Eveil = false;
        this.name = Roles.WOOCHINJUL;
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

	public void addArrowIndicator(Player player, Player killer,Plugin plugin,Player victim) {
		int x = (int) Math.round(killer.getLocation().getX());
		int y = (int) Math.round(killer.getLocation().getY());
		int z = (int) Math.round(killer.getLocation().getZ());
        String message = "§4! "+ victim.getDisplayName() +"a été tué \n" + " aux coordonnées X: " + x + ", Y: " + y + ", Z: " + z;
	    Killer = killer.getDisplayName();
	    // Schedule a task to clear the message after 10 seconds
	    Bukkit.getScheduler().runTaskLater(plugin, () -> {
	        player.sendMessage(GeneralVariable.MessagePrefix+""+ message);
	    }, 200L);
	}
	
	public void trouver(Player player,Player find, Start plugin,int days) {
		if (days == day) {
			player.sendMessage(GeneralVariable.MessagePrefix+"tu a deja utiliser ton pouvoir aujourd'hui");
		}else {
			day = days;
			if (find.getDisplayName() == Killer) {
				this.setEveil(true,player);
				player.sendMessage(GeneralVariable.MessagePrefix+"Tu a trouver le tueur Tu a debloque ton pouvoir GG");
			}else {
				player.sendMessage(GeneralVariable.MessagePrefix+"C'est pas le tueur du premier kill de la journée");
			}
		}
	}
	
	
	
    public void planifierTache(Start plugin, Player player) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            long time = Bukkit.getWorlds().get(0).getTime();
            if (time >= 0 && time < 12000) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 60 * 20, 0));
            } else {
                player.removePotionEffect(PotionEffectType.SPEED);
            }
        }, 0L, 20 * 60L);
    }

    @Override
    public void setEveil(boolean change, Player player) {
        this.Eveil = change;
        Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
    	player.sendMessage(GeneralVariable.MessagePrefix+"§6You pass to rank A GG");
        super.setRank("A");
        planifierTache(plugin, player);
        
    }

    public void getAbility(Player player) {
    	player.sendMessage("§6-------------------------\n"
    			+GeneralVariable.MessagePrefix+"§bTu joue §6Woo Chinjul §bTu gagne avec les Chasseurs\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 1 pouvoir apres ton eveil :\n"
    			+ "-§b tu a possede Speed 1 perma apres ton eveil\n"
    			+ "-§b apres le premier kill de chaque jour tu obtient les coordonné du kill\n"
    			+ "-§b avec la commande /sl find joueur si tu trouve le tueur du premier kill tu obtient ton eveil\n"
    			+ "§6-------------------------");
    
    
    }

}
