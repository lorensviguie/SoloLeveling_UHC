package fr.farkas.Main.General.World;

import java.util.List;
import java.util.Map;

import org.bukkit.World;
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
}
