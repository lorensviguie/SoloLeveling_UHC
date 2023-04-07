package fr.farkas.Main.Characters;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.farkas.Main.General.Game;

public class CharacterListeners {

	private Game game;
	
	public CharacterListeners(Game game) {
		this.game = game;
	}
	
	public void CharacterClick(PlayerInteractEvent event,Player player) {
		if (!(game.getCharacterManager().getCharacterName(player).equals("GoGunHee"))) {
			System.out.println("COUCOU");
		}
		if (!(game.getCharacterManager().getCharacterName(player).equals("SungJinWoo"))) {
			System.out.println("CANARD");
		}
	}
}
