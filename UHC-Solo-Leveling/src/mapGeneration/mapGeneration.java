package mapGeneration;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class mapGeneration {
    
    public static void generetemap(World world) {
        world.getWorldBorder().setSize(1000);
        world.getWorldBorder().setCenter(0, 0);
        world.setSpawnLocation(0, 64, 0);
        
        int middleX = 0;
        int middleZ = 0;
        
        if(world.getWorldType().equals(WorldCreator.name("uhc_Map"))) {
            middleX = 7;
            middleZ = 7;
        } else {
            middleX = world.getSpawnLocation().getBlockX();
            middleZ = world.getSpawnLocation().getBlockZ();
        }
        
        for (int x = middleX - 10; x < middleX + 200; x++) {
            for (int z = middleZ - 10; z < middleZ + 200; z++) {
                world.setBiome(x, z, org.bukkit.block.Biome.ROOFED_FOREST);
            }
        }
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("spectatorsGenerateChunks", "false");
        Bukkit.broadcastMessage("UHCsl Map generated!");
        
    }

}
