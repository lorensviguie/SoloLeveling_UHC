package fr.farkas.Main.Characters.Monarques;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Character;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.Characters.Solos.Solos;

public class Monarques extends Character{
	
	private static List<Player> members = new ArrayList<Player>();
	public static boolean areSolo = false;
	
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
    
    public void turnToSolo() {
    }
	
	public static void campToSolo() {
		areSolo = true;
		for(Player player : members) {
			player.sendMessage("§8Anthares §4 is dead, now you have to win alone !");
    		Solos.addMember(player);
    		((Monarques) CharacterManager.getPlayerCharacters().get(player)).turnToSolo();
		}
    	members.clear();
	}

	@Override
	public void getAbility(Player player) {
	}

	@Override
	public void isDying() {
		// TODO Auto-generated method stub
		
	}
}
