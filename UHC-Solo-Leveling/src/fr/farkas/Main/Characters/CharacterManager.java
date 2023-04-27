package fr.farkas.Main.Characters;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.farkas.Main.Start;
import fr.farkas.Main.Characters.SungJinWoo.DarkPower;

public class CharacterManager {
    private static Map<Player, Character> playerCharacters;
    private DarkPower darkPower;
    
    public void SungJinWoo(Start plugin) {
        this.darkPower = new DarkPower(plugin);
    }
    public CharacterManager() {
        playerCharacters = new HashMap<>();
    }
    
    public static Map<Player,Character> getPlayerCharacters(){
    	return playerCharacters;
    }

    public void chooseCharacter(Player player, Character character) {
        playerCharacters.put(player, character);
    }

    public void removeCharacter(Player player) {
        playerCharacters.remove(player);
    }

    public String getCharacterName(Player player) {
        return playerCharacters.get(player).getName();
    }
    public Character getCharacter(Player player) {
        return playerCharacters.get(player);
    }

    public void clearAll() {
        playerCharacters.clear();
    }

    public void removeDeadCharacters() {
        playerCharacters.entrySet().removeIf(entry -> {
            if (entry.getKey().isDead()) {
                System.out.println("Removing dead player " + entry.getKey().getName() + " from character manager.");
                return true;
            } else {
                return false;
            }
        });
    }

    public void removeAllEffects(Player player) {
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
    }

    public void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }

	public void specialAbility(Player player) {		
	}
	
    public void useAbility(Player player) {
        if (darkPower.canUseAbility(player)) {
            darkPower.useAbility(player);
        } else {
            long lastDarkTime = darkPower.getLastDarkTime(player);
            long timeSinceLastDark = System.currentTimeMillis() - lastDarkTime;
            long remainingTime = 120000 - timeSinceLastDark;
            int seconds = (int) (remainingTime / 1000);
            player.sendMessage(ChatColor.RED + "You cannot use the dark ability yet. Please wait " + seconds + " seconds.");
        }
    }
}
