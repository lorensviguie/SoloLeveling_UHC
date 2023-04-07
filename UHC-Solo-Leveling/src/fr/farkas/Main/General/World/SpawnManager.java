package fr.farkas.Main.General.World;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class SpawnManager {
	
		public static void createSpawn(World worlde) {
        // Get the world where the cube will be created
        World world = worlde;

        // Get the coordinates of the starting point for the cube
        int startX = 0;
        int startY = 127;
        int startZ = 0;

        // Create the cube using barrier blocks
        for (int x = startX; x < startX + 10; x++) {
            for (int y = startY; y < startY + 5; y++) {
                for (int z = startZ; z < startZ + 10; z++) {
                    // Only place barrier blocks on the edges of the cube
                    if (x == startX || x == startX + 9 || y == startY || y == startY + 4 || z == startZ || z == startZ + 9) {
                        Location location = new Location(world, x, y, z);
                        Block block = world.getBlockAt(location);
                        block.setType(Material.BARRIER);
                    }
                }
            }
        }
    }
		public static void tptoSpawn(Player player, World world) {
	        int x = 5;
	        int y = 128;
	        int z = 5;

	        Location location = new Location(world, x, y, z);
	        player.teleport(location);
		}
}
