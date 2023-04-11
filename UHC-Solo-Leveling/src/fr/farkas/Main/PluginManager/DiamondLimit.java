package fr.farkas.Main.PluginManager;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class DiamondLimit {
	public  HashMap<Player, Integer> playerMap = new HashMap<Player, Integer>();


	
	public void checkdiamondlimit(Event event, Player player, Block block) {
		if (playerMap.containsKey(player)){
			if (playerMap.get(player) == 10) {
                block.setType(Material.AIR); // remove the diamond block
                block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_INGOT)); // drop a gold ingot
                player.sendMessage("§2You have reached the diamond limit");
                
                block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(14);
			}else {
			playerMap.put(player, playerMap.get(player) + 1);
			}
		}else {
			playerMap.put(player, 1);
		}
	}
}
