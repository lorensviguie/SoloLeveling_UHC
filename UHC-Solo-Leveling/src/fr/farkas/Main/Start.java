package fr.farkas.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import fr.farkas.Main.Character.CharacterManager;
import fr.farkas.Main.commands.CommandSpawn;
import fr.farkas.Main.commands.SlDarkCommands;
import fr.farkas.Main.configuration.BasicInventoryConfig;

public class Start extends JavaPlugin {
	
	CharacterManager characterManager = new CharacterManager();
	Map<String, List<String>> ConfigData = new HashMap<>();
	BasicInventoryConfig BasicInventory =  new BasicInventoryConfig(ConfigData);
	public void onEnable() {
		System.out.println("Plugin Start");
		BasicInventory.createInventory();
		ConfigData.put("UHCBasicRules", new ArrayList<>());
		getServer().getPluginManager().registerEvents(new BasicInventoryConfig(ConfigData), this);
		getServer().getPluginManager().registerEvents(new UHCListeners(characterManager), this);
		getCommand("sl").setExecutor(new SlDarkCommands(characterManager));
		getCommand("dh").setExecutor(new CommandSpawn(ConfigData, BasicInventory));
        
	}
    public CharacterManager getCharacterManager() {
        return characterManager;
    }
	
	public void onDisable() {
		System.out.println("Plugin Stop");
	}
	
}
