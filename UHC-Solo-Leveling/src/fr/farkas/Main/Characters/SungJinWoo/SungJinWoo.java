package fr.farkas.Main.Characters.SungJinWoo;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Character;

public class SungJinWoo extends Character {

    private static final String DESCRIPTION = "Sung Jin Woo";
    private String name;
    private Boolean Eveil;
    private Boolean Monarque;
    private Player Player;
    
    public SungJinWoo(Player player, String key) {
        super(player, "SungJinWoo");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + SungJinWoo.getDescription());
        player.setMaxHealth(20.0);
        player.setHealth(player.getMaxHealth());
        this.name = "SungJinWoo";
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0, false, false));
        setCamp(1);
        this.Eveil = false;
        this.Player = player;
    }


    @Override
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }

	@Override
	public void isDying() {
		// TODO Auto-generated method stub
		
	}
    public void getAbility(Player player) {
    	player.sendMessage("§2Tu joue SungJinWoo Tu gagne avec les Chasseurs\n"
    			+ "§6-------------------------\n"
    			+ "§5Lis la Doc\n"
    			+ "§6-------------------------");
    
    
    }


	public Boolean getEveil() {
		return Eveil;
	}
	public void MonarquesOmbres() {
		setCamp(6);
		Player.sendMessage("§2Tu es maintenant Le Monarques des Ombres GG");
		Player.setMaxHealth(30);
		this.Monarque = true;
	}


	public void setEveil(Boolean eveil) {
		Eveil = true;
		Player.sendMessage("§2Tu es maintenant de rang S");
		Player.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
    	Player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
    	Player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
	}


	public Boolean getMonarque() {
		return Monarque;
	}


	public void setMonarque(Boolean monarque) {
		Monarque = monarque;
	}
}
