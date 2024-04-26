package fr.farkas.Main.Characters.Chasseurs.GotoRyuji;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.General.GeneralVariable;

public class GotoRyuji extends Chasseurs {
	
    private static final String DESCRIPTION = "Goto Ryuji";
    private String name;
    private Boolean Solo;
    private Boolean Enchant; 

	public GotoRyuji(Player player, String key) {
	    super(player, Roles.GOTORYUJI);
	    player.sendMessage(GeneralVariable.MessagePrefix+ "Tu joue mtn en tant que §6" + GotoRyuji.getDescription());
	    this.name = Roles.GOTORYUJI;
	    this.Solo = false;
	    this.Enchant = false;
	    this.Eveil = true;
	    super.setRank("S");
	    getAbility(player);
	    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
	}
    @Override
    public void setEveil(boolean change, Player player) {
        this.Eveil = change;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    public Boolean GetEnchant() {
    	return this.Enchant;
    }
    public Boolean getSolo() {
    	return this.Solo;
    }

    public String getName() {
    	return this.name;
    }
    
    public void setSolo(Player player) {
    	this.Solo = true;
    	setCamp(4);
    	player.setMaxHealth(30);
    	getAbility(player);
    }
    
    public void UseAbility(Player player) {
    	if (this.Enchant == true) {
    		player.sendMessage(GeneralVariable.MessagePrefix+"Ton pouvoir est desactivé");
    		this.Enchant = false;
    	}else {
    		player.sendMessage(GeneralVariable.MessagePrefix+"Ton pouvoir est activé");
			this.Enchant = true;
		}
    }
	
    public void getAbility(Player player) {
    	if (Solo == false) {
    		player.sendMessage("§6-------------------------\n"
    			+GeneralVariable.MessagePrefix+"§bTu joue §6Goto Ryuji§b Tu gagne avec les Chasseurs\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 2 pouvoir :\n"
    			+ "§b- tu possede Force permanent\n"
    			+"-§b avec la commane /sl enchant tu peux appliquer slowness ou non pendant 2 sec avec 30% de chance\n"
    			+ "§4si tu tue Beru tu passe Solo\n"
    			+ "§6-------------------------");
    	}else {
    		player.sendMessage( "§6-------------------------\n"
    				+GeneralVariable.MessagePrefix+"§bTu joue §6Goto Ryuji §bTu gagne en solo\n"
        			+ "§6-------------------------\n"
        			+ "§5Tu possède 2 pouvoir :\n"
        			+ "§b- tu possede Force permanent\n"
        			+"-§b avec la commane /sl enchant tu peux appliquer slowness ou non pendant 2 sec avec 30% de chance\n"
        			+ "§4mtn tu a 5 coeur en plus et tu 30% d'empoisonée par coup avec /sl enchant\n"
        			+ "§6-------------------------");
    	}
    }
	
}