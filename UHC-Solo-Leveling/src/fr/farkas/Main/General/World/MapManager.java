package fr.farkas.Main.General.World;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.farkas.Main.Characters.Fragments.Fragments;

public class MapManager {
	private World world;
	private World Lobby;
	private Map<String, List<String>> configdata;
	public MapManager(World world, Map<String, List<String>> configData,World Lobby) {
		this.world = world;
		this.configdata = configData;
		this.Lobby = Lobby;
	}
	
	public World getWorld() {
		return world;
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
	
	public void tpplayertoWorld(Player player) {
		player.setGameMode(GameMode.CREATIVE);
		player.teleport(new Location(Bukkit.getWorld("Game"), 0, 120, 0));
		player.getInventory().addItem(Fragments.createMat(Material.DRAGON_EGG, "Leave And regen World"));
		player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "Just leave"));
		
	}
	
	public void worldaction(Player player,PlayerInteractEvent event) {
		if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "Leave And regen World") {
			player.setGameMode(GameMode.SURVIVAL);
			player.teleport(new Location(Lobby, 5, 128, 5));
			player.getInventory().clear();
			regenWorld(player);
		}
		if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "Just leave") {
			player.setGameMode(GameMode.SURVIVAL);
			player.teleport(new Location(Lobby, 5, 128, 5));
			player.getInventory().clear();
		}
	}
	public void regenWorld(Player player) {
		World world = Bukkit.getWorld("Game"); 
		Bukkit.getServer().unloadWorld(world, true);
		File worldFolder = new File("Game");
		player.sendMessage("Starting to regen world");
	    regenerateMap(world);
	    player.sendMessage("World regen");
	}
	public void regenerateMap(World world) {
		int chunks = 0;
	     
        for(Chunk chunk: world.getLoadedChunks()){
world.regenerateChunk(chunk.getX(), chunk.getZ());
chunks+=1;
}
     
        Bukkit.getServer().unloadWorld("Game", false);
     
        Bukkit.broadcastMessage(chunks+" chunks regenerated");
     
        WorldCreator wo = new WorldCreator("Game");
     
        Long see = (new Random()).nextLong();
     
        wo.seed(see);
        wo.generator("TerrainControl");
        wo.createWorld();
	}
}
