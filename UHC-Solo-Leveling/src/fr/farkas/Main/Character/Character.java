package fr.farkas.Main.Character;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class Character {
    
    private Player player;
    private String key;
    
    public abstract String getName();
    public Character(Player player, String key) {
        this.setPlayer(player);
        this.setKey(key);
    }

    public double getHealth() {
        return player.getHealth();
    }

    public void setHealth(int health) {
        player.setHealth(health);
    }

    public Inventory getInventory() {
        return player.getInventory();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public abstract String getAbility();

}
