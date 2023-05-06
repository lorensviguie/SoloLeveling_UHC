package fr.farkas.Main.Characters.Solos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;

public class Solos extends Character{
	
	private static List<Player> members = new ArrayList<Player>();
	
	public Solos(Player player, String key) {
		super(player,key);
		setCamp(4);
		addMember(player);
	}

	@Override
	public String getName() {
		return null;
	}
	
	public static void addMember(Player player) {
		System.out.print(player.getName() + " est ajouté au camp des Solos");
		members.add(player);
	}
	
	public static List<Player> getMembers(){
		System.out.print("Les Solos sont : " + members);
		return Solos.members;
	}

	@Override
	public void getAbility(Player player) {
	}

	@Override
	public void isDying() {
		// TODO Auto-generated method stub
		
	}
}
