package fr.farkas.Main.Characters.Monstres;

import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;

public class Monstres extends Character{
	
	public Monstres(Player player, String key) {
		super(player,key);
		setCamp(5);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAbility(Player player) {
	}
}
