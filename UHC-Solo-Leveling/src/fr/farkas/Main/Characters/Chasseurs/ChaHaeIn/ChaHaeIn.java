package fr.farkas.Main.Characters.Chasseurs.ChaHaeIn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Start;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Fragments.Fragments;

public class ChaHaeIn extends Chasseurs {
	
    private static final String DESCRIPTION = "Cha Hae In";
    private String name;
    private Boolean transformation;
    private Boolean Eveil;
	
	public ChaHaeIn(Player player, String key) {
		super(player, "ChaHaeIn");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + this.getDescription());
        this.name = "ChaHaeIn";
        this.transformation = false;
        this.Eveil =false;
        player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Light Power"));
	}
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void sentir(Player playeres,Start plugin) {
    	Map<String, Integer> effectMap = new HashMap<>();
    	effectMap.put("E", 0);
    	effectMap.put("A", 0);
    	effectMap.put("S", 0);
    	effectMap.put("NATION", 0);
    	Location location = playeres.getLocation();
    	World world = playeres.getWorld();
    	List<Player> players = world.getPlayers().stream().filter(player -> player.getLocation().distance(location) <= 50).collect(Collectors.toList());
    	
    	for(Player player : players) {
    	    int numEffects = player.getActivePotionEffects().size();
    	    System.out.println(numEffects);
    	    if(numEffects == 0) {
    	        effectMap.put("E", effectMap.get("E") + 1);
    	    } else if(numEffects == 1) {
    	        effectMap.put("A", effectMap.get("A") + 1); 
    	    } else if(numEffects == 2) {
    	        effectMap.put("S", effectMap.get("S") + 1);
    	    } else if(numEffects > 2) {
    	        effectMap.put("NATION", effectMap.get("NATION") + 1); 
    	    }
    	}

    	// Increment the value for key "NATION" by the total number of players in the world
    	int Numberofplayer = Bukkit.getServer().getWorld("world").getPlayers().size();

    	// Print the values for each key in the map
    	playeres.sendMessage("§2Here the result of your power");
    	playeres.sendMessage("§6-------------------------");
    	playeres.sendMessage("§2 there is"+Numberofplayer+"§2 around you");
    	for(String key : effectMap.keySet()) {
    	    playeres.sendMessage(key + ": " + effectMap.get(key));
    	}
    }
    
    public void useability(Plugin plugin) {
    	Player player = getPlayer();
    	if (Eveil) {
    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*30*5, 0)); // speed 1 for 160 seconds
    		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*30*5, 0));
    	}else {
    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*60*2, 0)); // speed  for 120 seconds
    	}
    }

}
