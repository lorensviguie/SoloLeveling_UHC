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
}
