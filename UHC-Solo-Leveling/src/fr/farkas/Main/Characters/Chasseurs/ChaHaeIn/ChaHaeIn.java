package fr.farkas.Main.Characters.Chasseurs.ChaHaeIn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Start;
import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Fragments.Fragments;
import fr.farkas.Main.General.GeneralVariable;

public class ChaHaeIn extends Chasseurs {
	
    private static final String DESCRIPTION = "Cha Hae In";
    private String name;
    private int day;
    private boolean power;
	
	public ChaHaeIn(Player player, String key) {
		super(player, Roles.CHAHAEIN);
        player.sendMessage(GeneralVariable.MessagePrefix+"Tu joue mtn en tant que §6" + ChaHaeIn.getDescription());
        getAbility(player);
        this.name = Roles.CHAHAEIN;
        this.day = 0;
        this.power =false;
        super.setRank("A");
        player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Light Power"));
	}

    @Override
    public String getName() {
        return name;
    }
    public static String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void getAbility(Player player) {
        player.sendMessage("§6-------------------------\n"
        		+GeneralVariable.MessagePrefix+"§bTu joues §6ChaHaeIn §bTu gagnes avec les Chasseurs\n"
                + "§6-------------------------\n"
                + "§5Tu possèdes 2 pouvoirs :\n"
                + "§b- tu as ta transformation qui te donne speed 1 plus force 1 si tu es éveillé\n"
                + "§b- tu possèdes la commande /sl sentir joueur qui te donnera des informations sur la puissance des gens autour de toi\n"
                + "§4si tu trouves SungJinWoo avec sentir, tu débloqueras ton éveil\n"
                + "§6-------------------------");
    }

    public void sentir(Player player, Start plugin, int days, boolean good) {
        if (day == days) {
            player.sendMessage(GeneralVariable.MessagePrefix+"§b you have already used your power this day");
        } else {
            day = days;
            Map<String, Integer> effectMap = new HashMap<>();
            effectMap.put("E", 0);
            effectMap.put("A", 0);
            effectMap.put("S", 0);
            effectMap.put("NATION", 0);
            Location location = player.getLocation();
            World world = player.getWorld();
            List<Player> players = world.getPlayers().stream().filter(p -> p.getLocation().distance(location) <= 50).collect(Collectors.toList());
            for (Player target : players) {
                int numEffects = 0;
                for (PotionEffect effect : target.getActivePotionEffects()) {
                    if (effect.getType() != PotionEffectType.NIGHT_VISION) {
                        numEffects++;
                    }
                }

                if (numEffects == 0) {
                    effectMap.put("E", effectMap.get("E") + 1);
                } else if (numEffects == 1) {
                    effectMap.put("A", effectMap.get("A") + 1);
                } else if (numEffects == 2) {
                    effectMap.put("S", effectMap.get("S") + 1);
                } else if (numEffects > 2) {
                    effectMap.put("NATION", effectMap.get("NATION") + 1);
                }
            }
            player.sendMessage(GeneralVariable.MessagePrefix+"§bHere are the results of your power");
            player.sendMessage("§6-------------------------");
            for (String key : effectMap.keySet()) {
                player.sendMessage("§b"+key + ": " + effectMap.get(key));
            }
            player.sendMessage("§6-------------------------");
        }
        if (good) {
            setEveil(true,player);
            
        }
    }

    @Override
    public void setEveil(boolean change, Player player) {
    	player.sendMessage(GeneralVariable.MessagePrefix+"§6You Found SungJinWoo You Unlock Your Class S Power GG");
        this.Eveil = change;
        super.setRank("S");
    }

    public void useability(Plugin plugin) {
        Player player = getPlayer();
        if (!power) {
            if (super.Eveil) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, GeneralVariable.eveilEffect_ChaHaeIn, 0, false, false));
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, GeneralVariable.noteveilEffect_ChaHaeIn, 0, false, false));
            }
            power = true;
            new BukkitRunnable() {
                @Override
                public void run() {
                    power = false; 
                }
            }.runTaskLater(plugin, 600L * 20L);
        }else {
        	player.sendMessage(GeneralVariable.MessagePrefix+"Ton pouvoir est en cooldown");
        }
    }
}

