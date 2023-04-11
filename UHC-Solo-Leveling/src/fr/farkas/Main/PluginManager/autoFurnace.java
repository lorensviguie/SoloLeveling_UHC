package fr.farkas.Main.PluginManager;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class autoFurnace {
	public static void Iron(Event event, Player player, Block block) {
                block.setType(Material.AIR); // remove the diamond block
                block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_INGOT)); // drop a gold ingot
                block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(7);
		}
	public static void Gold(Event event, Player player, Block block) {
        block.setType(Material.AIR); // remove the diamond block
        block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_INGOT)); // drop a gold ingot 
        block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(7);
}
}
