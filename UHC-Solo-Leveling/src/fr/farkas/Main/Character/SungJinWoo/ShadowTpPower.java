package fr.farkas.Main.Character.SungJinWoo;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.farkas.Main.Start;

public class ShadowTpPower {
    private ArrayList<Player> shadows = new ArrayList<>();
    private Start plugin;

    public ShadowTpPower(Start plugin) {
        this.plugin = plugin;
    }

    public void useAbility(Player player, Player target) {
        player.teleport(target);
    }
}
