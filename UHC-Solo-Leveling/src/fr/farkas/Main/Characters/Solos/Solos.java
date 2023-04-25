package fr.farkas.Main.Characters.Solos;

import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;

public class Solos extends Character{
	
	public Solos(Player player, String key) {
		super(player,key);
		setCamp(4);
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
