package fr.farkas.Main.General.World;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public class MapManager {
	private World world;
	private World Lobby;
	private Map<String, List<String>> configdata;
	public MapManager(World world, Map<String, List<String>> configData,World Lobby) {
		this.world = world;
		this.configdata = configData;
		this.Lobby = Lobby;
	}
	public void createSpawn() {
		SpawnManager.createSpawn(world);
	}
	public void tptoSpawn(Player player) {
		SpawnManager.tptoSpawn(player,Lobby);
	}
	public void createborder() {
		BorderManager.createBorder(world,configdata);
	}
	public void createRoof() {
		
	}
	public World takeLobby() {
		return Lobby;
	}
	public static void checkOcean() {
		World world = Bukkit.getWorld("world"); 
		boolean hasOcean = false;

		for (int x = -1000; x < 1000; x += 16) {
		    for (int z = -1000; z < 1000; z += 16) {
		        Biome biome = world.getBiome(x, z);
		        if (biome == Biome.OCEAN) {
		            hasOcean = true;
		            break;
		        }
		    }
		    if (hasOcean) {
		        break;
		    }
		}

		if (hasOcean) {
	        deleteWorld(new File(world.getWorldFolder().getPath()));
	        createWorld(new WorldCreator(world.getName()));
		}

	}
	public static void createWorld(WorldCreator creator) {
	    creator.createWorld();
	}
	public static void deleteWorld(File path) {
	    if (path.exists()) {
	        File[] files = path.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    deleteWorld(file);
	                } else {
	                    file.delete();
	                }
	            }
	        }
	    }
	    path.delete();
	}


}
