package fr.farkas.Main.PluginManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.CharacterListeners;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Fragments.Fragments;
import fr.farkas.Main.Characters.Monarques.Monarques;
import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.Characters.Solos.Solos;
import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.World.MapManager;

public  class Deathvictory {
	@SuppressWarnings("deprecation")
	public static void Victory(Player player, CharacterManager characterManager, Game game, CharacterListeners characterListeners, MapManager mapManager) {
		HashMap<Integer, Integer> campCount = new HashMap<>();
		for (int i = 1; i <= 6; i++) {
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
		for (int i = 1; i <= 6; i++) {
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
		    } else if (winningCamp == 5) {
		    	message = "§cThe Monster player has won!";
		    } else if(campCount.get(4) == 1){
		        message = "§1The solo player has won!";
		    }else if(winningCamp == 6) {
		    	message = "§1Solo Sung Jin Woo has won!";
		    }
		    if (message != "") {
			    for (Player playeres : Bukkit.getOnlinePlayers()) {
			    	playeres.sendTitle("§6VICTORY", message);
			    }
		    }
		}
	}
	
	
	// -------------------------- Victory but with list système
	
	@SuppressWarnings("deprecation")
	public static void checkVictory() {
		String message = "";
		boolean otherCampAlive = false;
		if(Monstres.getMembers().size() > 0) {
			if(otherCampAlive) {
				return;
			}
			otherCampAlive = true;
			message = "§cThe Monster player has won!";
		}
		if(Chasseurs.getMembers().size() > 0) {
			if(otherCampAlive) {
				return;
			}
			message = "§2The hunters have won!";
			otherCampAlive = true;
		}
		if(Fragments.getMembers().size() > 0) {
			if(otherCampAlive) {
				return;
			}
	        message = "§6The fragments have won!";
			otherCampAlive = true;
		}
		if(Monarques.getMembers().size() > 0) {
			if(otherCampAlive) {
				return;
			}
	        message = "§6The fragments have won!";
			otherCampAlive = true;
		}
		if(Solos.getMembers().size() > 0) {
			if(otherCampAlive || Solos.getMembers().size() != 1) {
				return;
			}
	        message = "§1The solo player has won!";
			otherCampAlive = true;
		}
	    if (message != "") {
		    for (Player playeres : Bukkit.getOnlinePlayers()) {
		    	playeres.sendTitle("§6VICTORY", message);
		    }
	    }
	}
}





