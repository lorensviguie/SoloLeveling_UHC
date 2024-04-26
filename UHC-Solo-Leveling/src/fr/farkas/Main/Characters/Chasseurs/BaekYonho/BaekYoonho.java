package fr.farkas.Main.Characters.Chasseurs.BaekYonho;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Fragments.Fragments;
import fr.farkas.Main.General.GeneralVariable;

public class BaekYoonho extends Chasseurs {
	
    private static final String DESCRIPTION = "Baek Yoonho";
    private String name;
    private boolean transfo;
    private boolean transfoCooldown;
	
	public BaekYoonho(Player player, String key) {
		super(player, Roles.BAEKYOONHO);
        player.sendMessage(GeneralVariable.MessagePrefix+ "Tu joue Maintenant en tant que §6" + BaekYoonho.getDescription());
        getAbility(player);
        this.transfo = false;
        this.transfoCooldown = false;
        this.name = Roles.BAEKYOONHO;
        player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6Forme Bête"));
        super.setRank("A");
	}
	
	public void useability(Plugin plugin) {
	    Player player = getPlayer();
	    if (!this.transfo) {
	    	player.sendMessage(GeneralVariable.MessagePrefix+"Tu utilise ton pouvoir");
	    	if (this.Eveil) {
	    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 60 * 20, 1)); // Ajoute l'effet de vitesse pendant 2 minutes
	    		Bukkit.getScheduler().runTaskLater(plugin, () -> {
		            player.removePotionEffect(PotionEffectType.SPEED);
		            player.sendMessage(GeneralVariable.MessagePrefix+"Ta transformation est en cd");
		        }, 5 * 60 * 20);
	    	}else {
	    		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2 * 60 * 20, 1)); // Ajoute l'effet de vitesse pendant 2 minutes
	    		Bukkit.getScheduler().runTaskLater(plugin, () -> {
		            player.removePotionEffect(PotionEffectType.SPEED);
		            player.sendMessage(GeneralVariable.MessagePrefix+"Ta transformation est en cd");
		        }, 2 * 60 * 20);
	    	}
	        this.transfoCooldown = true;
	        Bukkit.getScheduler().runTaskLater(plugin, () -> this.transfoCooldown = false, 5 * 60 * 20);
	    } else {
	        if (this.transfoCooldown) {
	            player.sendMessage(GeneralVariable.MessagePrefix+"Ta transformation est en cd");
	        } else {
	            player.removePotionEffect(PotionEffectType.SPEED);
	            player.sendMessage(GeneralVariable.MessagePrefix+"Ta transformation est en cd");
	            this.transfoCooldown = true;
	            Bukkit.getScheduler().runTaskLater(plugin, () -> this.transfoCooldown = false, 5 * 60 * 20);
	        }
	    }
	}


    @Override
    public String getName() {
        return name;
    }
    public static String getDescription() {
        return DESCRIPTION;
    }
    
    @Override
    public void setEveil(boolean change, Player player) {
        this.Eveil = change;
        player.sendMessage(GeneralVariable.MessagePrefix+"§b Tu a débloqué ton full pouvoir tu es maintenant de rang S");
        this.Rank = "S";
        ItemStack sharpnessBook = new ItemStack(Material.ENCHANTED_BOOK);
        sharpnessBook.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
        player.getInventory().addItem(sharpnessBook);
    }

    @Override
    public void getAbility(Player player) {
        player.sendMessage("§6-------------------------\n"
        		+GeneralVariable.MessagePrefix+"§bTu joues §6Baek Yoonho §bTu gagnes avec les Chasseurs\n"
                + "§6-------------------------\n"
                + "§5Tu possèdes  pouvoirs :\n"
                + "§b- tu as ta transformation qui te donne speed 1 pendant 2Min avec 5Min de cd\n"
                + "§b- tu dois taper MinByung pour t'eveiller\n"
                + "§4 eveillé tu gagne un livre sharp4 tu passe a 5min de speed et 30% de chance de slow par coup\n"
                + "§6-------------------------");
    }
}
