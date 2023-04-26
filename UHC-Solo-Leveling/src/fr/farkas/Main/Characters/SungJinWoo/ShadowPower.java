package fr.farkas.Main.Characters.SungJinWoo;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.farkas.Main.Start;

public class ShadowPower {

    private ArrayList<Player> shadows = new ArrayList<>();
    @SuppressWarnings("unused")
	private Start plugin;

    public ShadowPower(Start plugin) {
        this.plugin = plugin;
    }

    public void useAbility(Player player, Player target) {
    	player.sendMessage(ChatColor.GOLD + "You put a shadows on " + target.getName() +"!");
        shadows.add(target);

    }
}