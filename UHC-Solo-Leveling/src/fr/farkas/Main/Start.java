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
	public void onEnable() {
		System.out.println("Plugin Start");
		
		ConfigData.put("UHCBasicRules", new ArrayList<>());
		BasicInventoryConfig BasicInventory =  new BasicInventoryConfig(ConfigData);
		BasicInventory.createInventory();
		getServer().getPluginManager().registerEvents(new UHCListeners(characterManager, BasicInventory), this);
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
