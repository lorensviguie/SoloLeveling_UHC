package fr.farkas.Main.Characters.Chasseurs.MinByung;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Character;
import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Chasseurs.BaekYonho.BaekYoonho;
import fr.farkas.Main.Characters.Fragments.Fragments;
import fr.farkas.Main.General.GeneralVariable;

public class MinByung extends Chasseurs {
	
    private static final String DESCRIPTION = "Min Byung";
    private String name;
    private int day;
    private int power;
    private Set<String> buffedPlayers = new HashSet<>();
	
	public MinByung(Player player, String key) {
		super(player, Roles.MINBYUNG);
        player.sendMessage(GeneralVariable.MessagePrefix+ "You are now playing as §6" + MinByung.getDescription());
        getAbility(player);
        this.name = Roles.MINBYUNG;
        this.day = 0;
        this.power = 0;
        super.setRank("A");
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
        		+GeneralVariable.MessagePrefix+"§bTu joues §6Min Byung §bTu gagnes avec les Chasseurs\n"
                + "§6-------------------------\n"
                + "§5Tu possèdes 2 pouvoirs :\n"
                + "§b- Chaque jour tu peux donner un coeur bonus a un joueur avec /sl buff"
                + "(une fois par joueur) "
                + "si le joueur n'est pas un chasseur il ne se passe rien mais tu ne le sera pas\n"
                + "§4Si c'est Baek qui recoit ton buff tu obtient son nom et il recoit 5gapple en plus "
                + "et tu passe de rang S\n"
                + "§b- Tu Obtient une nether Star 2 fois dans la game tous les chasseur dans un rayon de 20 block "
                + "§bfull regen et gagne Resi1 pendant 5 min 1 jour de Cooldown\n"
                + "§6-------------------------");
    }
    public void giveBuff(Character character, Player player, int days, Player minbyung) {
        String playerName = player.getName();
        if (days == this.day) {
            minbyung.sendMessage(GeneralVariable.MessagePrefix+"Tu as déjà utilisé ton pouvoir Kassos.");
        } else {
        	this.day = days;
            if (character instanceof Chasseurs) {
                if (!buffedPlayers.contains(playerName)) {
                    player.setMaxHealth(player.getMaxHealth() + 2);
                    player.sendMessage(GeneralVariable.MessagePrefix+"Tu as été buffé par MinByung.");
                    if (character instanceof BaekYoonho) {
                        setEveil(true,minbyung);
                        minbyung.sendMessage(GeneralVariable.MessagePrefix + " §2Tu as trouvé Baek qui est : " + player.getDisplayName());
                        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));
                        ;
                    }
                    buffedPlayers.add(playerName);
                } else {
                    minbyung.sendMessage(GeneralVariable.MessagePrefix+"Ce joueur a déjà été buffé.");
                }
            }
        }
    }
    
    @Override
    public void setEveil(boolean change, Player player) {
        this.Eveil = change;
        player.sendMessage(GeneralVariable.MessagePrefix+"§6Tu passe au rang S GG");
        player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Derniere Espoir"));
        super.setRank("S");
        
    }
    
    public void usePower(Player player, List<Player> hunters) {
		if (this.power <2){
			this.power ++;
			System.out.print(hunters);
			for (Player nearbyPlayer : hunters) {
				nearbyPlayer.setHealth(nearbyPlayer.getMaxHealth());
				nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5 * 60 * 20, 0,false,false));
				nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1 * 30 * 20, 0));
		    }
		}else {
			player.sendMessage(GeneralVariable.MessagePrefix+" §bTu a deja utiliser 2 fois ton pouvoir");
		}
    }
}
