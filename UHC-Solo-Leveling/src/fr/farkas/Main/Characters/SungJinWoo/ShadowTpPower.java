package fr.farkas.Main.Characters.SungJinWoo;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import fr.farkas.Main.Start;

public class ShadowTpPower {
    private ArrayList<Player> shadows = new ArrayList<>();
    @SuppressWarnings("unused")
	private Start plugin;

    public ShadowTpPower(Start plugin) {
        this.plugin = plugin;
    }

    public void useAbility(Player player, Player target) {
        player.teleport(target);
    }

	public ArrayList<Player> getShadows() {
		return shadows;
	}

	public void setShadows(ArrayList<Player> shadows) {
		this.shadows = shadows;
	}
}
