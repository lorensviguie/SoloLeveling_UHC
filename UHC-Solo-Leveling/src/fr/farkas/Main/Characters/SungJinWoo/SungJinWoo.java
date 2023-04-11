package fr.farkas.Main.Characters.SungJinWoo;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Character;
import fr.farkas.Main.Characters.Monarques.Legia.Legia;

public class SungJinWoo extends Character {

    private static final String DESCRIPTION = "Sung Jin Woo";
    private String name;

    public SungJinWoo(Player player, String key) {
        super(player, "SungJinWoo");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + this.getDescription());
        player.setMaxHealth(30.0);
        player.setHealth(player.getMaxHealth());
        this.name = "SungJinWoo";
    }


    @Override
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    public String getAbility() {
    	return ChatColor.GREEN + "SungJinWoo has many powers:\n" +
    		       ChatColor.DARK_PURPLE + "- The Dark: " + ChatColor.WHITE + "This ability grants SungJinWoo Speed I and Strength I for 2 minutes.";

    }

}
