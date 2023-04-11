package fr.farkas.Main.Characters;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Fragments.GoGunHee.GoGunHee;
import fr.farkas.Main.Characters.Fragments.ThomasAndre.ThomasAndre;
import fr.farkas.Main.Characters.Monarques.Legia.Legia;
import fr.farkas.Main.Characters.Monarques.MonarqueBetes.MonarqueBetes;
import fr.farkas.Main.Characters.Solos.Architecte.Architecte;
import fr.farkas.Main.Characters.SungJinWoo.SungJinWoo;

public class DistribRole {
	public static void giveRole(List<String> roles, CharacterManager characterManager) {
		List<Player> noRolePlayers = (List<Player>) Bukkit.getOnlinePlayers();
		
		for(Player player : noRolePlayers) {
			Random r = new Random();
			int rNum = r.nextInt(roles.size());
			String role = roles.get(rNum);
			roles.remove(rNum);
			characterManager.chooseCharacter(player, convertNameToRole(role, player));
		}	
	}
	
	public static Character convertNameToRole(String name, Player player) {
		System.out.print(name + " " + player.getName());
		switch (name){
		// Fragment
		case "ThomasAndre":
			return new ThomasAndre(player, name);
		case "GoGunHee":
			return new GoGunHee(player, name);

		// Mornaques
		case "Legia":
			return new Legia(player, name);
		case "MonarquesBetes":
			return new MonarqueBetes(player, name);

		// Solo
		case "Architect":
			return new Architecte(player, name);
		case "SungJinWoo":
			return new SungJinWoo(player, name);


		default:
			return null;
		}
	}
}