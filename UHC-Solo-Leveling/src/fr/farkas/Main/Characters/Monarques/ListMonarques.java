package fr.farkas.Main.Characters.Monarques;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.CharacterManager;

public class ListMonarques {

	 
	
	public static void SetupList(CharacterManager charactermanager,Player playeres) {
		List<String> Monarques = new ArrayList<>();
	    Player[] players = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0]);

	    for (Player player : players) {
	    	if (charactermanager.getCharacter(player) != null) {
	    		if (charactermanager.getCharacter(player).getCamp() == 3) {
	    			System.out.println("COINCOIN");
	    			Monarques.add(player.getDisplayName());
	    		}
	    	}
	    }
		String joinedString = String.join(" , ", Monarques);
		playeres.sendMessage("§4Voici la liste : \n"+joinedString);
	}
}
