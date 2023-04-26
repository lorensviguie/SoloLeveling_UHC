package fr.farkas.Main.General.World;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

public class MapManager {
	private World world;
	private Map<String, List<String>> configdata;
	public MapManager(World world, Map<String, List<String>> configData) {
		this.world = world;
		this.configdata = configData;
	}
	
	public World getWorld() {
		World Area = Bukkit.getWorld("Game");
		return Area;
	}
	public void createSpawn() {
		SpawnManager.createSpawn(world);
	}
	public void tptoSpawn(Player player) {
		SpawnManager.tptoSpawn(player,world);
	}
	public void createborder() {
		World Area = Bukkit.getWorld("Area");
		BorderManager.createBorder(Area,configdata);
	}
	public void createRoof() {
		
	}
	public World takeLobby() {
		return world;
	}
	public void RegenArea() {
		World Area = Bukkit.getWorld("Game");
		Bukkit.unloadWorld(Area, false);
		File worldFolder = Area.getWorldFolder();
		deleteFolder(worldFolder);
		WorldCreator creator = new WorldCreator("Game");
		creator.type(WorldType.NORMAL);
		creator.generateStructures(true);
		creator.seed(new Random().nextLong());
		World newWorld = Bukkit.createWorld(creator);
		newWorld.getPopulators().add(new GenerateRoof());
	}

	
	private void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if (files != null) {
	        for (File file : files) {
	            if (file.isDirectory()) {
	                deleteFolder(file);
	            } else {
	                file.delete();
	            }
	        }
	    }
	    folder.delete();
	}

}
