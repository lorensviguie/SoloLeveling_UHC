package fr.farkas.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import fr.farkas.Main.Character.CharacterManager;
import fr.farkas.Main.WorldManager.MapManager;
import fr.farkas.Main.commands.CommandSpawn;
import fr.farkas.Main.commands.SlDarkCommands;
import fr.farkas.Main.configuration.BasicInventoryConfig;

public class Start extends JavaPlugin {
	
	CharacterManager characterManager = new CharacterManager();
	Map<String, List<String>> ConfigData = new HashMap<>();
	World world = getServer().getWorld("world");
	MapManager mapManager = new MapManager(world,ConfigData);
	public void onEnable() {
		System.out.println("Plugin Start");
		ConfigData.put("UHCBasicRules", new ArrayList<>());
		ConfigData.put("Border", new ArrayList<>());
		BasicInventoryConfig BasicInventory =  new BasicInventoryConfig(ConfigData);
		mapManager.createSpawn();
		BasicInventory.createInventory();
		getServer().getPluginManager().registerEvents(new UHCListeners(characterManager, BasicInventory,mapManager), this);
		getCommand("sl").setExecutor(new SlDarkCommands(characterManager));
		getCommand("dh").setExecutor(new CommandSpawn(ConfigData, BasicInventory,world));
        
	}
    public CharacterManager getCharacterManager() {
        return characterManager;
    }
	
	public void onDisable() {
		System.out.println("Plugin Stop");
	}
	
}
