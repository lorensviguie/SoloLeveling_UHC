package fr.farkas.Main.Characters.Chasseurs;

import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;

public class Chasseurs extends Character{
	
	public Chasseurs(Player player, String key) {
		super(player,key);
		setCamp(1);
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
