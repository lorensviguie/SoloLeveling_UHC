package fr.farkas.Main.Characters.SungJinWoo;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;

public class SungJinWoo extends Character {

    private static final String DESCRIPTION = "Sung Jin Woo";
    private String name;
    
    public SungJinWoo(Player player, String key) {
        super(player, "SungJinWoo");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + SungJinWoo.getDescription());
        player.setMaxHealth(30.0);
        player.setHealth(player.getMaxHealth());
        this.name = "SungJinWoo";
        setCamp(4);
    }


    @Override
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
	@Override
	public void getAbility(Player player) {
	}


	@Override
	public void isDying() {
		// TODO Auto-generated method stub
		
	}

}
