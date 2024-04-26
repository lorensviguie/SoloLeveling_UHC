package fr.farkas.Main.General.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class ApplyRules {
	
	private Map<String, List<String>> configdata;
	
	public ApplyRules(Map<String, List<String>> configdata) {
		this.configdata = configdata;
	}

	public void Applyallrules() {
		List<String> rules = configdata.get("UHCBasicRules");
		System.out.print(rules);
		if (rules.contains("NightVision")) {
			// Récupérer tous les joueurs connectés
			Collection<? extends Player> players = Bukkit.getOnlinePlayers();

			// Appliquer l'effet Night Vision à tous les joueurs
			for (Player player : players) {
			    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false));
			}
		}
		System.out.print(rules);
		if (rules.contains("SafeMiner")) {
			// Récupérer tous les joueurs connectés
			Collection<? extends Player> players = Bukkit.getOnlinePlayers();

			// Appliquer l'effet Night Vision à tous les joueurs
			for (Player player : players) {
			    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 60 * 20, 0));
			}
		}
		System.out.print(rules);
		if (rules.contains("StarterKit")) {
			for (Player player : Bukkit.getOnlinePlayers()) {
			    player.getInventory().addItem(new ItemStack(Material.WOOD, 64));
			    player.getInventory().addItem(new ItemStack(Material.APPLE, 16));
			    player.getInventory().addItem(new ItemStack(Material.BOOK, 8));
			    player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT, 64));
			    ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE);
			    ItemMeta pickaxeMeta = pickaxe.getItemMeta();
			    pickaxeMeta.addEnchant(Enchantment.DURABILITY, 3, true);
			    pickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
			    pickaxe.setItemMeta(pickaxeMeta);
			    player.getInventory().addItem(pickaxe);
			}

		}
	}
	
}
