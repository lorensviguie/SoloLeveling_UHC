package fr.farkas.Main.WorldManager;

import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.WorldBorder;

public class BorderManager {

	public static void createBorder(World worlde, Map<String, List<String>> configData) {
		WorldBorder border = worlde.getWorldBorder();
		List<String> rules = configData.get("Border");
		border.setCenter(0, 0);
		border.setSize(Integer.parseInt(rules.get(0)));	
	}
	
	public static void destroyBorder(World worlde) {
		WorldBorder border = worlde.getWorldBorder();
		border.setCenter(0, 0);
		border.setSize(1500);
	}
}
