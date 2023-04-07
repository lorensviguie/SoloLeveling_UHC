package fr.farkas.Main.General.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.farkas.Main.General.Game;

public class Config {

	Map<String, List<String>> ConfigData = new HashMap<>();
	
	BasicInventoryConfig BasicInventory;
	
	public Config(Game game) {
		this.ConfigData.put("UHCBasicRules", new ArrayList<>());
		this.ConfigData.put("Border", new ArrayList<>());	
		
		this.BasicInventory = new BasicInventoryConfig(this.ConfigData);
		this.BasicInventory.createInventory();
	}
	
	public Map<String, List<String>> getConfigData(){
		return this.ConfigData;
	}
	
	public BasicInventoryConfig getBasicInventoryConfig() {
		return this.BasicInventory;
	}
}
