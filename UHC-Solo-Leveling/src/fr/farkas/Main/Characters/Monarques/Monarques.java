package fr.farkas.Main.Characters.Monarques;

import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;

public abstract class Monarques extends Character{
	
	public Monarques(Player player, String key) {
		super(player,key);
		setCamp(3);
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
