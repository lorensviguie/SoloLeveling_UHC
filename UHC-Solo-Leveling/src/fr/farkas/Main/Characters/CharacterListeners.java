package fr.farkas.Main.Characters;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Chasseurs.BaekYonho.BaekYoonho;
import fr.farkas.Main.Characters.Chasseurs.ChaHaeIn.ChaHaeIn;
import fr.farkas.Main.Characters.Chasseurs.LiuZhigang.LiuZhigang;
import fr.farkas.Main.Characters.Chasseurs.MinByung.MinByung;
import fr.farkas.Main.Characters.Chasseurs.YooJinho.YooJinHo;
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
		if (event.getItem() != null) {
			switch (game.getCharacterManager().getCharacterName(player)){
			
			case Roles.VULCAN:
				if (event.getItem().getItemMeta().getDisplayName() == "§6Rage") {
					Vulcan vulcan = (Vulcan) characterManager.getCharacter(player);
					int day = game.getScoreboard().GetTimer().getDay();
					vulcan.useAbility(game.getPlugin(),day);
				}
				break;
				
			case Roles.GOGUNHEE:
				if (event.getItem().getItemMeta().getDisplayName() == "§6Transformation") {
					GoGunHee goGunHee = (GoGunHee) characterManager.getCharacter(player);
					goGunHee.useability(game.getPlugin());
				}
				break;
				
			case Roles.THOMASANDRE:
				if (event.getItem().getItemMeta().getDisplayName() == "§6Transformation") {
					ThomasAndre thomasAndre = (ThomasAndre) characterManager.getCharacter(player);
					thomasAndre.useability(game.getPlugin());
				}
				break;
				
			case Roles.CHRISTOPHERREED:
				if (event.getItem().getItemMeta().getDisplayName() == "§6Transformation") {
					ChristopherReed christopherReed = (ChristopherReed) characterManager.getCharacter(player);
					christopherReed.useability(game.getPlugin());
				}
				break;
				
			case Roles.ARCHITECT:
				if (event.getItem().getItemMeta().getDisplayName() == "§0TP Yourself to DreamWorld") {
					Architecte architecte = (Architecte) characterManager.getCharacter(player);
					architecte.tptodreamWorld(player);
				}
				break;
				
			case Roles.SUNGJINWOO:
				System.out.println("CANARD");
				break;
				
			case Roles.BERU:
				if (event.getItem().getItemMeta().getDisplayName() == "§6Evovle") {
					Beru beru = (Beru) characterManager.getCharacter(player);
					beru.useability(game.getPlugin());
				}
				break;
				
			case Roles.MONARQUEBETES:
				if (event.getItem().getItemMeta().getDisplayName () == "§6Formes") {
					MonarqueBetes monarquesBetes = (MonarqueBetes) characterManager.getCharacter(player);
					monarquesBetes.getFormeInventory().openFormInventory();
				}
				break;
				
			case Roles.CHAHAEIN:
				if (event.getItem().getItemMeta().getDisplayName() == "§6Light Power") {
					ChaHaeIn chaHaeIn = (ChaHaeIn) characterManager.getCharacter(player);
					chaHaeIn.useability(game.getPlugin());
				}
				break;
				
			case Roles.LIUZHIGANG:
				if (event.getItem().getItemMeta().getDisplayName() == "§6Power") {
					LiuZhigang liuZhigang = (LiuZhigang) characterManager.getCharacter(player);
					liuZhigang.useability(game.getPlugin(),event);
				}
				break;
			case Roles.BAEKYOONHO:
				if (event.getItem().getItemMeta().getDisplayName() == "§6Forme Bête") {
					BaekYoonho baekyoonho = (BaekYoonho) characterManager.getCharacter(player);
					baekyoonho.useability(game.getPlugin());
				}
				break;
			case Roles.YOOJINHO:
				if (event.getItem().getItemMeta().getDisplayName() == "§6TPALLMAP") {
					YooJinHo yoojinho = (YooJinHo) characterManager.getCharacter(player);
					int day = game.getScoreboard().GetTimer().getDay();
					yoojinho.useTP(player,day,game.getPlugin());
				}
			
			case Roles.MINBYUNG:
			    if (event.getItem().getItemMeta().getDisplayName().equals("§6Derniere Espoir")) {
			        Location location = player.getLocation();
			        World world = player.getWorld();
			        List<Player> hunters = new ArrayList<>();
			        List<Player> players = world.getPlayers().stream().filter(p -> p.getLocation().distance(location) <= 50).collect(Collectors.toList());
			        for (Player nearbyPlayer : players) {
			            if (characterManager.getCharacter(nearbyPlayer) instanceof Chasseurs) {
			                hunters.add(nearbyPlayer);
			            }
			        }
			        MinByung minbyung = (MinByung) characterManager.getCharacter(player);
			        minbyung.usePower(player, hunters);
			    }
			    break;

			}
		}
		
	}
	
	public void CommandAbility() {
		
	}
}
