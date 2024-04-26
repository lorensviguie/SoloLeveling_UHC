package fr.farkas.Main.Characters.Monstres.Baruka;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.General.GeneralVariable;

public class Baruka extends Monstres{
    private static final String DESCRIPTION = "Baruka";
    private String name;
    public Baruka(Player player, String key) {
        super(player, Roles.BARUKA);
        player.sendMessage(GeneralVariable.MessagePrefix + "You are now playing as " + Baruka.getDescription());
        this.name = Roles.BARUKA;
        this.getAbility(player);
        ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) enchantedBook.getItemMeta();
        meta.addStoredEnchant(Enchantment.ARROW_INFINITE, 1, false);
        meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 3, false);
        enchantedBook.setItemMeta(meta);
        player.getInventory().addItem(enchantedBook);
        
    }

    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
//    public void planifierTache(Plugin plugin, Player player) {
//    	if (GeneralVariable.PortailDefenseSucces) {
//    		long timeUntilNextCycle;
//            World Area = Bukkit.getWorld("Game");
//            long time = Area.getTime();
//            if (time < 12000) {
//                timeUntilNextCycle = 12000 - time;
//            } else {
//                timeUntilNextCycle = 24000 - time + 12000;
//            }
//            timeUntilNextCycle += 1;
//            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
//                if (Area.getTime() > 12000) {
//                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10 * 60 * 20, 0));
//                } else {
//                    player.removePotionEffect(PotionEffectType.SPEED);
//                }
//            }, timeUntilNextCycle);
//    	}
//        
//    }
    public void planifierTache(Plugin plugin, Player player) {
    	System.out.print("TU COUR OUH");
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 0,false , false));
    }

    
    public void getAbility(Player player) {
    	player.sendMessage(GeneralVariable.MessagePrefix+"§bTu joue §6Baruka §bTu gagne avec les Monstres\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 2 pouvoir :\n"
    			+ "§b- Tu Commande la partie avec un livre Infinityet un power3\n"
    			+ "-§b Tes fleches appliques Slow pendant 3 sec\n"
    			+ "-§b Tu possede Speed la nuit\n"
    			+ "§4 avec la commande /sl liste tu peux voir la liste des tes aliés"
    			+ "§6-------------------------");
    
    
    }

}