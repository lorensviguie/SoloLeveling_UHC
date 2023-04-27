package fr.farkas.Main.General;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.DistribRole;
import fr.farkas.Main.General.Configuration.Config;
import fr.farkas.Main.General.Scoreboard.Scoreboard;
import fr.farkas.Main.General.Timer.TimerManager;
import fr.farkas.Main.General.World.Portal;

public class Game {
	
	private  boolean GameStart;
	private Timer timer = new Timer();
	private TimerManager time = new TimerManager();
	private Scoreboard scoreboard;
	private Config config;
	private TimerTask task;
	private CharacterManager characterManager;
	private Plugin plugin;
	
	
	// Gère le systeme de seconde
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
	
	public Game(Plugin plugin) {
		this.scoreboard = new Scoreboard(this.time);
		this.config = new Config(this);
		this.task = new GlobalEverySecond(this.scoreboard);
		this.timer.schedule(this.task, 1000,1000);
		this.characterManager = new CharacterManager();
		this.plugin = plugin;
		SetGameStatus(false);
	}
	
	
	public void StartGame() {
		SetGameStatus(true);
		System.out.print("Game start");
		this.scoreboard.GetTimer().Start();
		new BukkitRunnable() {
        	@Override
        	public void run() {
        		DistribRole.giveRole(config.getConfigData().get("CharacterList"), characterManager);
        		new BukkitRunnable() {
        			@Override
        			public void run() {
        				new Portal(plugin);
        			}
        		}.runTaskLater(plugin, GeneralVariable.portalCooldown);
        	}
    	}.runTaskLater(plugin, GeneralVariable.roleAttribution);
		
	}
	
	public void StopGame() {
		this.SetGameStatus(false);
		this.scoreboard.GetTimer().Stop();
		this.scoreboard.GetTimer().ResetTimer();
	}
	
	public void DestroyGame() {
		this.StopGame();
		this.timer.cancel();
		this.timer.purge();
	}
	
	
	public boolean DidgameStart() {
		return this.GameStart;
	}
	
	public Plugin getPlugin() {
		return this.plugin;
	}
	
	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}
	
	public void SetGameStatus(Boolean status) {
		GameStart = status;
	}
	
	public Config getConfig() {
		return this.config;
	}
	
	public CharacterManager getCharacterManager() {
		return this.characterManager;
	}

}
