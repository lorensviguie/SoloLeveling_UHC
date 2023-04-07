package fr.farkas.Main.General;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.farkas.Main.General.Scoreboard.Scoreboard;
import fr.farkas.Main.General.Timer.TimerManager;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.General.Configuration.Config;

public class Game {
	
	private  boolean GameStart;
	private Timer timer = new Timer();
	private TimerManager time = new TimerManager();
	private Scoreboard scoreboard;
	private Config config;
	private TimerTask task;
	private CharacterManager characterManager;
	
	
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
	
	public Game() {
		this.scoreboard = new Scoreboard(this.time);
		this.config = new Config(this);
		this.task = new GlobalEverySecond(this.scoreboard);
		this.timer.schedule(this.task, 1000,1000);
		this.characterManager = new CharacterManager();
		SetGameStatus(false);
	}
	
	
	public void StopGame() {
		this.timer.cancel();
		this.timer.purge();
	}
	public boolean DidgameStart() {
		return this.GameStart;
	}
	
	
	
	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}
	public void SetGameStatus(Boolean status) {
		System.out.println(status);
		GameStart = false;
	}
	
	public Config getConfig() {
		return this.config;
	}
	
	public CharacterManager getCharacterManager() {
		return this.characterManager;
	}

}
