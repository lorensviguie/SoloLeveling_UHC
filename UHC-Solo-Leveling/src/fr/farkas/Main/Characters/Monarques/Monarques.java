package fr.farkas.Main.Characters.Monarques;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;
import fr.farkas.Main.Characters.Monstres.Monstres;

public abstract class Monarques extends Character{
	
	private static List<Player> members = new ArrayList<Player>();
	
	public Monarques(Player player, String key) {
		super(player,key);
		setCamp(3);
		addMember(player);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addMember(Player player) {
		System.out.print(player.getName() + " est ajouté au camp des Monarques");
		members.add(player);
	}
	
	public static List<Player> getMembers(){
		System.out.print("Les Monarques sont : " + members);
		return Monarques.members;
	}

	@Override
	public void getAbility(Player player) {
	}
}
