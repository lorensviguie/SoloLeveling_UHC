package fr.farkas.Main.Characters.Chasseurs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;

public class Chasseurs extends Character{
	
	private static List<Player> members = new ArrayList<Player>();
	
	public Chasseurs(Player player, String key) {
		super(player,key);
		setCamp(1);
		addMember(player);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addMember(Player player) {
		System.out.print(player.getName() + " est ajouté au camp des Chasseurs");
		members.add(player);
	}
	
	public static List<Player> getMembers(){
		System.out.print("Les Chasseurs sont : " + members);
		return Chasseurs.members;
	}

	@Override
	public void getAbility(Player player) {
	}

	@Override
	public void isDying() {
		// TODO Auto-generated method stub
		
	}

}
