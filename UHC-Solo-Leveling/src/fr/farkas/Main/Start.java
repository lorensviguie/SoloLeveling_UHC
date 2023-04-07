package fr.farkas.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import fr.farkas.Main.Character.CharacterManager;
import fr.farkas.Main.WorldManager.GenerateRoof;
import fr.farkas.Main.WorldManager.MapManager;
import fr.farkas.Main.commands.CommandSpawn;
import fr.farkas.Main.commands.SlDarkCommands;
import fr.farkas.Main.configuration.BasicInventoryConfig;
import fr.farkas.Main.game.Scoreboard;
import fr.farkas.Main.game.TimerManager;

class GlobalEverySecond extends TimerTask{
	private Scoreboard scoreboard;
	public GlobalEverySecond(Scoreboard scoreboard){
		this.scoreboard = scoreboard;
	}
	
	public void run(){
		List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
		this.scoreboard.GetTimer().AddSecond();
		for (Player player : players) {
			this.scoreboard.Update();
			this.scoreboard.Display(player);
		}
	}
}

public class Start extends JavaPlugin {
	Timer timer = new Timer();
	CharacterManager characterManager = new CharacterManager();
	Map<String, List<String>> ConfigData = new HashMap<>();
	BasicInventoryConfig BasicInventory;
	TimerManager time = new TimerManager();
	Scoreboard scoreboard = new Scoreboard(time);
	World world = getServer().getWorld("world");
	MapManager mapManager = new MapManager(world,ConfigData);

	public void onEnable() {
		System.out.println("Plugin Start");
		world.getPopulators().add(new GenerateRoof());
		
		TimerTask task = new GlobalEverySecond(scoreboard);
		timer.schedule(task, 1000,1000);
		
		
		ConfigData.put("UHCBasicRules", new ArrayList<>());
		ConfigData.put("Border", new ArrayList<>());
		BasicInventory = new BasicInventoryConfig(ConfigData);

		BasicInventory.createInventory();
		
		mapManager.createSpawn();
		BasicInventory.createInventory();
		getServer().getPluginManager().registerEvents(new UHCListeners(characterManager, BasicInventory,mapManager), this);
		getCommand("sl").setExecutor(new SlDarkCommands(characterManager));
		getCommand("dh").setExecutor(new CommandSpawn(ConfigData, BasicInventory, scoreboard,world));		

	}
    public CharacterManager getCharacterManager() {
        return characterManager;
    }
	
	public void onDisable() {
		System.out.println("Plugin Stop");
		timer.cancel();
	}
	
}
