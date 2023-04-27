package fr.farkas.Main.Characters.Fragments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.farkas.Main.Characters.Character;
import fr.farkas.Main.Characters.Monstres.Monstres;

public class Fragments extends Character{
	
	private static List<Player> members = new ArrayList<Player>();
	
	public Fragments(Player player, String key) {
		super(player,key);
		setCamp(2);
		addMember(player);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addMember(Player player) {
		System.out.print(player.getName() + " est ajouté au camp des Fragments");
		members.add(player);
	}
	
	public static List<Player> getMembers(){
		System.out.print("Les Fragments sont : " + members);
		return Fragments.members;
	}

	@Override
	public void getAbility(Player player) {
	}
    public static ItemStack createMat(Material mat,String Display) {
    	ItemStack crea = new ItemStack(mat);
    	ItemMeta creaMeta = crea.getItemMeta();
    	creaMeta.setDisplayName(Display);
    	crea.setItemMeta(creaMeta);
    	return crea;
    }
}
