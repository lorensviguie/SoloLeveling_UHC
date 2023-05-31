package fr.farkas.Main.Characters;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Chasseurs.ChaHaeIn.ChaHaeIn;
import fr.farkas.Main.Characters.Chasseurs.LiuZhigang.LiuZhigang;
import fr.farkas.Main.Characters.Chasseurs.Selner.Selner;
import fr.farkas.Main.Characters.Chasseurs.WooChinjul.WooChinjul;
import fr.farkas.Main.Characters.Fragments.ChristopherReed.ChristopherReed;
import fr.farkas.Main.Characters.Fragments.GoGunHee.GoGunHee;
import fr.farkas.Main.Characters.Fragments.ThomasAndre.ThomasAndre;
import fr.farkas.Main.Characters.Monarques.Anthares.Anthares;
import fr.farkas.Main.Characters.Monarques.Legia.Legia;
import fr.farkas.Main.Characters.Monarques.MonarqueBetes.MonarqueBetes;
import fr.farkas.Main.Characters.Monstres.Beru.Beru;
import fr.farkas.Main.Characters.Monstres.Vulcan.Vulcan;
import fr.farkas.Main.Characters.Solos.Architecte.Architecte;
import fr.farkas.Main.Characters.SungJinWoo.SungJinWoo;

public class DistribRole {
	public static void giveRole(List<String> roles, CharacterManager characterManager) {
		@SuppressWarnings("unchecked")
		List<Player> noRolePlayers = (List<Player>) Bukkit.getOnlinePlayers();
		for(Player player : noRolePlayers) {
			Random r = new Random();
			int rNum = r.nextInt(roles.size());
			String role = roles.get(rNum);
			roles.remove(rNum);
			characterManager.chooseCharacter(player, convertNameToRole(role, player));
			player.sendMessage("here the number of youre camp" + characterManager.getCharacter(player).getCamp());
		}	
	}
	
	public static Character convertNameToRole(String name, Player player) {
		System.out.print(name + " " + player.getName());
		
		switch (name){
		// Chasseur
		case Roles.CHAHAEIN:
			return new ChaHaeIn(player, name);
		case Roles.SELNER:
			return new Selner(player, name);
		case Roles.LIUZHIGANG:
			return new LiuZhigang(player, name);
		case Roles.WOOCHINJUL:
			return new WooChinjul(player, name);
			
			
		// Fragment
		case Roles.THOMASANDRE:
			return new ThomasAndre(player, name);
		case Roles.GOGUNHEE:
			return new GoGunHee(player, name);
		case Roles.CHRISTOPHERREED:
			return new ChristopherReed(player, name);

		// Mornaques
		case Roles.ANTHARES:
			return new Anthares(player, name);
		case Roles.LEGIA:
			return new Legia(player, name);
		case Roles.MONARQUEBETES:
			return new MonarqueBetes(player, name);

		// Solo
		case Roles.ARCHITECT:
			return new Architecte(player, name);
		case Roles.SUNGJINWOO:
			return new SungJinWoo(player, name);
			
		// Monstre
		case Roles.BERU:
			return new Beru(player, name);
		case Roles.VULCAN:
			return new Vulcan(player, name);
		default:
			return null;
		}
	}
}
