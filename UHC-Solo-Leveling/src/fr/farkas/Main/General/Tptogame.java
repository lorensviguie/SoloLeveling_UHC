package fr.farkas.Main.General;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Tptogame {
	public static void TPtoGame(Player player) {
		World world = Bukkit.getWorld("Game");
		int x = (int)(Math.random() * 1000) - 500;
		int z = (int)(Math.random() * 1000) - 500;
		Location loc = new Location(world, x, 150, z);
		player.teleport(loc);
		player.setNoDamageTicks(20*10);
	}
}
