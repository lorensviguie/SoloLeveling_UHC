package fr.farkas.Main.PluginManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.CharacterListeners;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.World.MapManager;

public  class Deathvictory {
	@SuppressWarnings("deprecation")
	public static void Victory(Player player, CharacterManager characterManager, Game game, CharacterListeners characterListeners, MapManager mapManager) {
		HashMap<Integer, Integer> campCount = new HashMap<>();
		for (int i = 1; i <= 5; i++) {
		    campCount.put(i, 0);
		}
		for(Player play : mapManager.getWorld().getPlayers()) {
			if (play.getGameMode().equals(GameMode.SPECTATOR)) {
			}else {
				int camp = characterManager.getCharacter(play).getCamp();
			    if(campCount.containsKey(camp)) {
			        campCount.put(camp, campCount.get(camp) + 1);
			    }
			}
		}
		System.out.println(campCount);
		int numCampsAlive = 0;
		int winningCamp = -1;
		for (int i = 1; i < 5; i++) {
		    if (campCount.get(i) > 0) {
		        numCampsAlive++;
		        winningCamp = i;
		    }
		}
		if (numCampsAlive == 1){
		    String message = "";
		    if (winningCamp == 1) {
		        message = "§2The hunters have won!";
		    } else if (winningCamp == 2) {
		        message = "§6The fragments have won!";
		    } else if (winningCamp == 3) {
		        message = "§4The monarchs have won!";
		    } else if(campCount.get(4) == 1){
		        message = "§1The solo player has won!";
		    }
		    if (message == "") {
		    	
		    }else {
		    for (Player playeres : Bukkit.getOnlinePlayers()) {
		    	playeres.sendTitle("§6VICTORY", message);
		    }
		    }
		}
	}
}





