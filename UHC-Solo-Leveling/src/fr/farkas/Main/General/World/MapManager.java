package fr.farkas.Main.General.World;

import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class MapManager {
	private World world;
	private Map<String, List<String>> configdata;
	public MapManager(World world, Map<String, List<String>> configData) {
		this.world = world;
		this.configdata = configData;
	}
	public void createSpawn() {
		SpawnManager.createSpawn(world);
	}
	public void tptoSpawn(Player player) {
		SpawnManager.tptoSpawn(player,world);
	}
	public void createborder() {
		BorderManager.createBorder(world,configdata);
	}
	public void createRoof() {
		
	}
}
