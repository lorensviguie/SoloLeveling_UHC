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
import fr.farkas.Main.General.GeneralVariable;

public class ChaHaeIn extends Chasseurs {
	
    private static final String DESCRIPTION = "Cha Hae In";
    private String name;
    private Boolean transformation;
    private Boolean Eveil;
    private int day;
	
	public ChaHaeIn(Player player, String key) {
		super(player, "ChaHaeIn");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + this.getDescription());
        this.name = "ChaHaeIn";
        this.transformation = false;
        this.Eveil =false;
        this.day = 0;
        player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Light Power"));
	}
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void sentir(Player playeres,Start plugin,int days,Boolean good) {
    	if (day == days) {
    		playeres.sendMessage("§2 you have already use your power this day");
    	}else {
    		day = days;
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
    	playeres.sendMessage("§6-------------------------");
    }
    if (good) {
    	playeres.sendMessage("§6You Found SungJinWoo You Unlock Your power GG");
    	Eveil = true;
    }

    }
    
    public void useability(Plugin plugin) {
    	Player player = getPlayer();
    	if (Eveil) {
    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, GeneralVariable.eveilEffect_ChaHaeIn, 0));
    		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, GeneralVariable.eveilEffect_ChaHaeIn, 0));
    	}else {
    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, GeneralVariable.noteveilEffect_ChaHaeIn, 0));
    	}
    }
    public void getAbility(Player player) {
    	player.sendMessage("§2Tu joue ChaHaeIn Tu gagne avec les Chasseurs\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 2 pouvoir :\n"
    			+ "-§2 tu a ta transformation qui te donne speed 1 plus force 1 si tu es eveiller\n"
    			+ "-§2 tu possède la commande sl sentir joueur qui te donnerra des information sur la puissances des gens autour de toi\n"
    			+ "§4si tu trouve SungJinWoo avec sentir tu debloquera ton eveil\n"
    			+ "§6-------------------------");
    
    
    }

}
