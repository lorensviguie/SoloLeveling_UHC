package fr.farkas.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.farkas.Main.Character.CharacterManager;
import fr.farkas.Main.commands.CommandSpawn;
import fr.farkas.Main.commands.SlDarkCommands;
import fr.farkas.Main.configuration.BasicInventoryConfig;
import fr.farkas.Main.game.Scoreboard;
import fr.farkas.Main.game.TimerManager;

class GlobalEverySecond extends TimerTask{
	private Scoreboard scoreboard;
	private TimerManager timer;
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
	BasicInventoryConfig BasicInventory =  new BasicInventoryConfig(ConfigData);
	TimerManager time = new TimerManager();
	Scoreboard scoreboard = new Scoreboard(time);
	public void onEnable() {
		System.out.println("Plugin Start");
		
		
		TimerTask task = new GlobalEverySecond(scoreboard);
		timer.schedule(task, 1000,1000);
		
		
		
		BasicInventory.createInventory();
		ConfigData.put("UHCBasicRules", new ArrayList<>());
		getServer().getPluginManager().registerEvents(new BasicInventoryConfig(ConfigData), this);
		getServer().getPluginManager().registerEvents(new UHCListeners(characterManager), this);
		getCommand("sl").setExecutor(new SlDarkCommands(characterManager));
		getCommand("dh").setExecutor(new CommandSpawn(ConfigData, BasicInventory, scoreboard));		

	}
    public CharacterManager getCharacterManager() {
        return characterManager;
    }
	
	public void onDisable() {
		System.out.println("Plugin Stop");
		timer.cancel();
	}
	
}
