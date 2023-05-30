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
    private int last_day;
	
	private int onlinePlayerNumber;
	
	public Scoreboard(TimerManager timer) {
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = board.registerNewObjective("Global","I don't know what is it");
		this.onlinePlayerNumber = Bukkit.getOnlinePlayers().size();
		this.timer = timer;
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6§l    SoloLeveling UHC    ");
		this.Creation();

    }
	
	public TimerManager GetTimer() {
		return this.timer;
	}
	
	@SuppressWarnings("deprecation")
	public void Creation() {
        this.last_timer = this.timer.GetTimeString();
        this.last_day = 0;
		
		Server server = Bukkit.getServer();
	    long time = server.getWorld("Game").getTime();
        Score space = objective.getScore(Bukkit.getOfflinePlayer("§§§2"));
        space.setScore(2);
        Score space2 = objective.getScore(Bukkit.getOfflinePlayer("§§§2 "));
        space2.setScore(5);
        Score timerS = objective.getScore(Bukkit.getOfflinePlayer(String.format("§6Timer : §e%s", last_timer)));
        timerS.setScore(1);
        
        Score dayNb = objective.getScore(Bukkit.getOfflinePlayer(String.format("§bJour %d", this.last_day)));
        dayNb.setScore(4);

        this.onlinePlayerNumber = Bukkit.getOnlinePlayers().size();
        
        Score playerNb = objective.getScore(Bukkit.getOfflinePlayer(String.format("§c%d §4Joueurs ", this.onlinePlayerNumber)));
        playerNb.setScore(3);
        
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
	
	public void Update() { 
		
		Server server = Bukkit.getServer();
	    long time = server.getWorld("world").getTime();
	    
		for(String entry : board.getEntries()) {
			if(entry != "§8.") {
				// --------- Change every second
		        if(entry.contains("Timer")) {
		            if(this.last_timer != this.timer.GetTimeString()) {
		            	this.last_timer = this.timer.GetTimeString();
						objective.getScoreboard().resetScores(entry);
				        @SuppressWarnings("deprecation")
						Score timerS = objective.getScore(Bukkit.getOfflinePlayer(String.format("§6Timer : §e%s", last_timer)));
				        timerS.setScore(1);
		            }
		        }
		        // -----------------------------
		        
		        if(entry.contains(String.format("§bJour %d", this.last_day))) {
		        	if(this.last_day != this.timer.getDay()) {
		        		this.last_day = this.timer.getDay();
						objective.getScoreboard().resetScores(entry);
			            @SuppressWarnings("deprecation")
						Score dayNb = objective.getScore(Bukkit.getOfflinePlayer(String.format("§bJour %d", this.last_day)));
			            dayNb.setScore(4);
		        	}
		        }
		        
		        if(entry.contains(String.format("§c%d §4Joueurs ", this.onlinePlayerNumber))) {
	                if(this.onlinePlayerNumber != Bukkit.getOnlinePlayers().size()) {
						objective.getScoreboard().resetScores(entry);
	                	this.onlinePlayerNumber = Bukkit.getOnlinePlayers().size();
		                @SuppressWarnings("deprecation")
						Score playerNb = objective.getScore(Bukkit.getOfflinePlayer(String.format("§c%d §4Joueurs ", this.onlinePlayerNumber)));
		                playerNb.setScore(3);
	                }
		        }
		        
		        
			}
		};
        
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
        @SuppressWarnings("deprecation")
		Score dayStatus = objective.getScore(Bukkit.getOfflinePlayer(String.format("§6Cycle : §e%s", cycle)));
        dayStatus.setScore(0);
        
        this.timer.GetTimeString();        
		
	}
	
	public void Display(Player player) {
        player.setScoreboard(board);
	}
}
