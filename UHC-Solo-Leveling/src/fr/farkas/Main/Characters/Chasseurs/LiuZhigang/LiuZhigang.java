package fr.farkas.Main.Characters.Chasseurs.LiuZhigang;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Fragments.Fragments;
import fr.farkas.Main.General.GeneralVariable;

public class LiuZhigang extends Chasseurs {

    private static final String DESCRIPTION = "Liu Zhigang";
    private String name;
	
	public LiuZhigang(Player player, String key) {
		super(player, Roles.LIUZHIGANG);
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + LiuZhigang.getDescription());
        this.name = Roles.LIUZHIGANG;
        super.setRank("A");
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
        this.Eveil = false;
	}
    public String getName() { // Add a getter method for the name field
        return name;
    }
    public void useability(Plugin plugin, PlayerInteractEvent event) {
    	Player player = getPlayer();
    	Player target = getLookedAt(player);
        Location playerLoc = player.getLocation();
        Location targetLoc = target.getLocation();
        double distance = playerLoc.distance(targetLoc);

        if (distance > 0) {
            double newX = targetLoc.getX() - ((targetLoc.getX() - playerLoc.getX()) / 2);
            double newY = targetLoc.getY() - ((targetLoc.getY() - playerLoc.getY()) / 2);
            double newZ = targetLoc.getZ() - ((targetLoc.getZ() - playerLoc.getZ()) / 2);
            Location newLoc = new Location(targetLoc.getWorld(), newX, newY, newZ);

            ((Player) target).teleport(newLoc);
            player.sendMessage("§aLe joueur " + ((Player) target).getDisplayName() + " a été rapatrié !");
        }
    }
    public Player getLookedAt(Player p) {
        List<Entity> nearby = p.getNearbyEntities(16, 15, 16);
        for (Entity e : nearby) {
            if (e instanceof Player) {
                if (p.hasLineOfSight(e)) {
                    return (Player) e;
                }
            }
        }
        return null;
    }
    public static String getDescription() {
        return DESCRIPTION;
    }
	public Boolean getEveil() {
		return super.getEveil();
	}

    @Override
    public void setEveil(boolean change, Player player) {
    	Eveil = true;
		super.setRank("S");
		player.sendMessage(GeneralVariable.MessagePrefix+"§6You Pass to rank S GG");
		player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Power"));
    }
    public void getAbility(Player player) {
    	player.sendMessage("§2Tu joue Liu Zhang Tu gagne avec les Chasseurs\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 1 pouvoir apres ton eveil :\n"
    			+ "-§2 tu a possede Force 1 perma\n"
    			+ "-§2 apres eveil tu obtient une nether star qui rapproche la personne cibler de la moitié de la distance \n"
    			+ "§6-------------------------");
    
    
    }
}
