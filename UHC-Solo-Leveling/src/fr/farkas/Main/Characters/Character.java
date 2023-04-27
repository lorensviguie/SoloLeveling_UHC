package fr.farkas.Main.Characters;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class Character {
    
    private Player player;
    private String key;
    private int camp;
    
    public abstract String getName();
    public Character(Player player, String key) {
        this.setPlayer(player);
        this.setKey(key);
        this.camp = 0;
    }

    public double getHealth() {
        return player.getHealth();
    }
    public int getCamp() {
    	return camp;
    }
    public void setCamp(int Camp) {
    	this.camp = Camp;
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

    public abstract void isDying();

    public abstract void getAbility(Player player);

}
