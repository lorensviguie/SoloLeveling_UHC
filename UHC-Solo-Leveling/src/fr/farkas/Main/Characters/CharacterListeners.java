package fr.farkas.Main.Characters;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.farkas.Main.Characters.Chasseurs.ChaHaeIn.ChaHaeIn;
import fr.farkas.Main.Characters.Chasseurs.LiuZhigang.LiuZhigang;
import fr.farkas.Main.Characters.Fragments.ChristopherReed.ChristopherReed;
import fr.farkas.Main.Characters.Fragments.GoGunHee.GoGunHee;
import fr.farkas.Main.Characters.Fragments.ThomasAndre.ThomasAndre;
import fr.farkas.Main.Characters.Monarques.MonarqueBetes.MonarqueBetes;
import fr.farkas.Main.Characters.Monstres.Beru.Beru;
import fr.farkas.Main.Characters.Monstres.Vulcan.Vulcan;
import fr.farkas.Main.Characters.Solos.Architecte.Architecte;
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
		
		if ((game.getCharacterManager().getCharacterName(player).equals("Vulcan"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Rage") {
				Vulcan vulcan = (Vulcan) characterManager.getCharacter(player);
				vulcan.useability(game.getPlugin());
			}
		}
		
		if ((game.getCharacterManager().getCharacterName(player).equals("GoGunHee"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Transformation") {
				GoGunHee goGunHee = (GoGunHee) characterManager.getCharacter(player);
				goGunHee.useability(game.getPlugin());
			}else {
			}
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("ThomasAndre"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Transformation") {
				ThomasAndre thomasAndre = (ThomasAndre) characterManager.getCharacter(player);
				thomasAndre.useability(game.getPlugin());
			}else {
			}
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("ChristopherReed"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Transformation") {
				ChristopherReed christopherReed = (ChristopherReed) characterManager.getCharacter(player);
				christopherReed.useability(game.getPlugin());
			}else {
			}
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("Architect"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§0TP Yourself to DreamWorld") {
				Architecte architecte = (Architecte) characterManager.getCharacter(player);
				architecte.tptodreamWorld(player);
			}else {
			}
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("SungJinWoo"))) {
			System.out.println("CANARD");
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("Beru"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Evovle") {
				Beru beru = (Beru) characterManager.getCharacter(player);
				beru.useability(game.getPlugin());
			}
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("MonarqueBetes"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Formes") {
				MonarqueBetes monarquesBetes = (MonarqueBetes) characterManager.getCharacter(player);
				monarquesBetes.getFormeInventory().openFormInventory();
			}
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("ChaHaeIn"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Light Power") {
				ChaHaeIn chaHaeIn = (ChaHaeIn) characterManager.getCharacter(player);
				chaHaeIn.useability(game.getPlugin());
			}
		}
		if ((game.getCharacterManager().getCharacterName(player).equals("LiuZhigang"))) {
			if (event.getItem() != null &&event.getItem().getItemMeta().getDisplayName() == "§6Power") {
				LiuZhigang liuZhigang = (LiuZhigang) characterManager.getCharacter(player);
				liuZhigang.useability(game.getPlugin(),event);
			}
		}
		
	}
	
	public void CommandAbility() {
		
	}
}
