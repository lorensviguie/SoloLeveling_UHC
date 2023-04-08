package fr.farkas.Main.Characters;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.farkas.Main.Characters.Fragments.GoGunHee.GoGunHee;
import fr.farkas.Main.General.Game;

public class CharacterListeners {

	private Game game;
	private CharacterManager characterManager;
	
	public CharacterListeners(Game game) {
		this.game = game;
		this.characterManager = game.getCharacterManager();
	}
	
	public void CharacterClick(PlayerInteractEvent event,Player player) {
		System.out.println(game.getCharacterManager().getCharacterName(player));
		if ((game.getCharacterManager().getCharacterName(player).equals("GoGunHee"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Transformation") {
				GoGunHee goGunHee = (GoGunHee) characterManager.getCharacter(player);
				goGunHee.useability(game.getPlugin());
			}else {
			}
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("SungJinWoo"))) {
			System.out.println("CANARD");
		}
		
	}
}
