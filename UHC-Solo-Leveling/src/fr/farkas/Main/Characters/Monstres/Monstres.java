package fr.farkas.Main.Characters.Monstres;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.farkas.Main.Characters.Character;

public class Monstres extends Character{
	
	private static List<Player> members = new ArrayList<Player>();
	
	public Monstres(Player player, String key) {
		super(player,key);
		setCamp(5);
		addMember(player);
	}

	@Override
	public void getAbility(Player player) {
	}
	
	public void addMember(Player player) {
		System.out.print(player.getName() + " est ajouté au camp des Monstres");
		members.add(player);
	}
	
	public static List<Player> getMembers(){
		System.out.print("Les Monstres sont : " + members);
		return Monstres.members;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void isDying() {
		// TODO Auto-generated method stub
		
	}
	
    public static ItemStack createMat(Material mat,String Display) {
    	ItemStack crea = new ItemStack(mat);
    	ItemMeta creaMeta = crea.getItemMeta();
    	creaMeta.setDisplayName(Display);
    	crea.setItemMeta(creaMeta);
    	return crea;
    }
}
