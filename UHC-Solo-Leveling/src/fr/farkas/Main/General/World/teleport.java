package fr.farkas.Main.General.World;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class teleport {
	public static void teleportPlayerRandomly(Player player, World world,Plugin game) {
        Location randomLocation = getRandomLocation(world, 100);
        player.teleport(randomLocation);
        createInvisibleCage(randomLocation);

        new BukkitRunnable() {
            @Override
            public void run() {
                destroyInvisibleCage(randomLocation);
            }
        }.runTaskLater(game, 40L); // 40 ticks = 2 secondes
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setNoDamageTicks(200);
            }
        }.runTaskLater(game, 180L);
    }
    public static void destroyInvisibleCage(Location location) {
        World world = location.getWorld();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    Block block = world.getBlockAt(x + xOffset, y + yOffset, z + zOffset);
                    block.setType(Material.AIR); // Remove the block
                }
            }
        }
    }

    public static Location getRandomLocation(World world, int height) {
        int x = (int) (Math.random() * 1000) - 500;
        int z = (int) (Math.random() * 1000) - 500;
        int y = world.getHighestBlockYAt(x, z) + height;
        return new Location(world, x, y, z);
    }

    public static void createInvisibleCage(Location location) {
        World world = location.getWorld();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    if (xOffset == 0 && yOffset == 0 && zOffset == 0) {
                        continue; // Skip the center block
                    }
                    Block block = world.getBlockAt(x + xOffset, y + yOffset, z + zOffset);
                    block.setType(Material.AIR); // Make the block invisible
                }
            }
        }
    }
}
