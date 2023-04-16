package fr.farkas.Main.General.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.farkas.Main.General.Timer.TimerManager;

public class Scoreboard {

	private ScoreboardManager manager = Bukkit.getScoreboardManager();
	private org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
	private Objective objective;
    private String cycle;
    private TimerManager timer;
    private String last_timer;
	
	private int onlinePlayerNumber;
	
	public Scoreboard(TimerManager timer) {
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = board.registerNewObjective("Global","I don't know what is it");
		this.onlinePlayerNumber = Bukkit.getOnlinePlayers().size();
		this.timer = timer;
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6§l    SoloLeveling UHC    ");
		this.Update();

    }
	
	public TimerManager GetTimer() {
		return this.timer;
	}
	
	public void Update() { 

		for(String entry : board.getEntries()) {
			objective.getScoreboard().resetScores(entry);
		};
		
		Server server = Bukkit.getServer();
	    long time = server.getWorld("world").getTime();
		
        Score space2 = objective.getScore(Bukkit.getOfflinePlayer("§8. "));
        space2.setScore(5);
        
        Score dayNb = objective.getScore(Bukkit.getOfflinePlayer(String.format("§bJour %d", this.timer.getDay())));
        dayNb.setScore(4);

        if(Bukkit.getOnlinePlayers().size() != 0) {
            if(this.onlinePlayerNumber != Bukkit.getOnlinePlayers().size()) {
            	objective.getScoreboard().resetScores(String.format("§cPlayers: §f%d", this.onlinePlayerNumber));
            	this.onlinePlayerNumber = Bukkit.getOnlinePlayers().size();
            }
            Score playerNb = objective.getScore(Bukkit.getOfflinePlayer(String.format("§c%d §4Joueurs ", this.onlinePlayerNumber)));
            playerNb.setScore(3);
        }
        
        Score space = objective.getScore(Bukkit.getOfflinePlayer("§8."));
        space.setScore(2);

        if(this.last_timer != this.timer.GetTimeString()) {
        	objective.getScoreboard().resetScores(String.format("§6Timer : §e%s", last_timer));
        	this.last_timer = this.timer.GetTimeString();
        }
        Score timerS = objective.getScore(Bukkit.getOfflinePlayer(String.format("§6Timer : §e%s", last_timer)));
        timerS.setScore(1);
        
	    if (time < 12300 || time > 23850) {
	    	if(cycle == "Nuit") {
	    		objective.getScoreboard().resetScores(String.format("§6Cycle : §e%s", cycle));
	    	}
	    	cycle = "Jour";
	    }else {
	    	if(cycle == "Jour") {
	    		objective.getScoreboard().resetScores(String.format("§6Cycle : §e%s", cycle));
	    	}
	    	cycle = "Nuit";
	    }
        Score dayStatus = objective.getScore(Bukkit.getOfflinePlayer(String.format("§6Cycle : §e%s", cycle)));
        dayStatus.setScore(0);
        
        this.timer.GetTimeString();        
		
	}
	
	public void Display(Player player) {
        player.setScoreboard(board);
	}
}
