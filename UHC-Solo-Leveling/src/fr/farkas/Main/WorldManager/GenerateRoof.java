package fr.farkas.Main.WorldManager;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;

public class GenerateRoof extends BlockPopulator {
    private static final int REGION_SIZE = 500;

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int cx = chunk.getX() * 16;
        int cz = chunk.getZ() * 16;

        // Center of the region
        int center_x = 0;
        int center_z = 0;

        // Calculate the distance from the center of the region
        double distance = Math.sqrt(Math.pow(cx - center_x, 2) + Math.pow(cz - center_z, 2));

        // Only change the biome if the chunk is within the radius of the region
        if (distance <= REGION_SIZE) {
            // Change the biome to a roofed forest
            for (int x = cx; x < cx + 16; x++) {
                for (int z = cz; z < cz + 16; z++) {
                    world.setBiome(x, z, Biome.ROOFED_FOREST);
                }
            }
        }
    }


}
