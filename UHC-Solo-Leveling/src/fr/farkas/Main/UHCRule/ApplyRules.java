package fr.farkas.Main.UHCRule;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ApplyRules {
	
	private Map<String, List<String>> configdata;
	
	public ApplyRules(Map<String, List<String>> configdata) {
		this.configdata = configdata;
	}

	public void Applyallrules() {
		List<String> rules = configdata.get("UHCBasicRules");
		if (rules.contains("NightVision")) {
			// Récupérer tous les joueurs connectés
			Collection<? extends Player> players = Bukkit.getOnlinePlayers();

			// Appliquer l'effet Night Vision à tous les joueurs
			for (Player player : players) {
			    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
			}
		}
	}
}
