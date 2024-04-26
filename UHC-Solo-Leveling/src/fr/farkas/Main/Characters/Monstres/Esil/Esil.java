package fr.farkas.Main.Characters.Monstres.Esil;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.General.GeneralVariable;

public class Esil extends Monstres{
    private static final String DESCRIPTION = "Esil Radiru";
    private String name;
    private int day;
	public Esil(Player player, String key) {
		super(player, Roles.ESIL);
        player.sendMessage(GeneralVariable.MessagePrefix+"You are now playing as " + Esil.getDescription());
        this.name = Roles.ESIL;
        this.day = 0 ;
        this.getAbility(player);
        player.getInventory().addItem(Monstres.createMat(Material.NETHER_STAR, "§6Evovle"));
	}
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    public void giveSpeedAndStrength(Player player, Plugin plugin) {
    	System.out.print("BITE");
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 10, 0, true, false));

        if (GeneralVariable.PortailDefenseSucces) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60 * 10, 0, true, false));
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            player.removePotionEffect(PotionEffectType.SPEED);
        }, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                int playersNearMonsters = 0;
                Location location = player.getLocation();
                World world = player.getWorld();
                List<Player> players = world.getPlayers().stream().filter(p -> p.getLocation().distance(location) <= 20).collect(Collectors.toList());
                for (Player monstre : players) {
                    if (monstre instanceof Monstres) {
                        playersNearMonsters++;
                    }
                }
                if (playersNearMonsters >= 2) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 10, 0, true, false));
                    if (GeneralVariable.PortailDefenseSucces) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60 * 10, 0, true, false));
                    }
                    return;
                }
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                player.removePotionEffect(PotionEffectType.SPEED);
            }
        }, 0, 5 * 20);
    }
    
    public void tpintoPortal(Player player,Player totp,int days) {
    	if (this.day < days) {
    		this.day = days;
        	Location loc = new Location(Bukkit.getWorld(GeneralVariable.DonjonWorld), 222, 44, 51);
        	totp.teleport(loc);
        	Location loc1 = new Location(Bukkit.getWorld(GeneralVariable.DonjonWorld), 283, 50, 50);
        	player.teleport(loc1);
    	}else {
    		player.sendMessage(GeneralVariable.MessagePrefix + "Tu a deja utiliser ton pouvoir aujourd'hui.");
    	}
    }
}