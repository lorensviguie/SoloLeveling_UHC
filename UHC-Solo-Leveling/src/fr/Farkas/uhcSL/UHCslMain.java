package fr.Farkas.uhcSL;

import org.bukkit.plugin.java.JavaPlugin;


public class UHCslMain extends JavaPlugin {
	
	
	public void onEnable() {
		System.out.println("PLugin Ready for utilisation");
	
	
	
		getServer().getPluginManager().registerEvents(new UHCListeners(), this);

	
	}
	@Override
	public void onDisable() {
		System.out.println("Plugin Disable");
	}

}
