package fr.farkas.Main.Characters;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Chasseurs.WooChinjul.WooChinjul;
import fr.farkas.Main.Characters.Monstres.Baruka.Baruka;
import fr.farkas.Main.Characters.Monstres.Esil.Esil;
import fr.farkas.Main.Characters.SungJinWoo.SungJinWoo;

public class CharacterManager {
    private static Map<Player, Character> playerCharacters;

    private Boolean BlackHearth = false; 
    

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
    public Player getPlayerWithWooChinjul() {
        for (Map.Entry<Player, Character> entry : playerCharacters.entrySet()) {
            if (entry.getValue() instanceof WooChinjul) {
                return entry.getKey();
            }
        }
        return null; // no player found with WooChinjul character
    }
    
    public Player getPlayerWithBaruka() {
        for (Map.Entry<Player, Character> entry : playerCharacters.entrySet()) {
            if (entry.getValue() instanceof Baruka) {
                return entry.getKey();
            }
        }
        return null; // no player found with WooChinjul character
    }
    
    public Player getPlayerEsil() {
        for (Map.Entry<Player, Character> entry : playerCharacters.entrySet()) {
            if (entry.getValue() instanceof Esil) {
                return entry.getKey();
            }
        }
        return null; // no player found with WooChinjul character
    }
    
    public Player getPlayerWithSungJinWoo() {
        for (Map.Entry<Player, Character> entry : playerCharacters.entrySet()) {
            if (entry.getValue() instanceof SungJinWoo) {
                return entry.getKey();
            }
        }
        return null; // no player found with WooChinjul character
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
	
	public Boolean getBlackHearth() {
		return BlackHearth;
	}
	public void setBlackHearth(Boolean blackHearth) {
		BlackHearth = blackHearth;
	}
}
