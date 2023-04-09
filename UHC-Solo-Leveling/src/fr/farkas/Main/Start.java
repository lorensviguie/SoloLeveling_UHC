package fr.farkas.Main;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.farkas.Main.Commands.CommandSpawn;
import fr.farkas.Main.Commands.SlDarkCommands;
import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.World.MapManager;
import fr.farkas.Main.PluginManager.UHCListeners;

public class Start extends JavaPlugin {
	private Game game;
	private World world = getServer().getWorld("world");
	private MapManager mapManager;
	Plugin plugin = this;

	public void onEnable() {
		System.out.println("Plugin Start");
		
		//this.world.getPopulators().add(new GenerateRoof());
		
		this.game = new Game(plugin);
		

		this.mapManager = new MapManager(this.world,this.game.getConfig().getConfigData());
		this.mapManager.createSpawn();
		
		getServer().getPluginManager().registerEvents(new UHCListeners(game.getCharacterManager(), game.getConfig().getBasicInventoryConfig(),mapManager, game), this);
		getCommand("sl").setExecutor(new SlDarkCommands(game.getCharacterManager()));
		getCommand("dh").setExecutor(new CommandSpawn(game.getConfig().getConfigData(), game.getConfig().getBasicInventoryConfig(), game.getScoreboard(),world,game));		

	}
	
	public void onDisable() {
		System.out.println("Plugin Stop");
		this.game.StopGame();
	}
	
}
