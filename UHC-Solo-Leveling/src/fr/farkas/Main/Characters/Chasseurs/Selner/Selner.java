package fr.farkas.Main.Characters.Chasseurs.Selner;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.Character;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.General.GeneralVariable;

public class Selner extends Chasseurs {
    private static final String DESCRIPTION = "Selner";
    private String name;
    private int day;
    private int dayPower;
    private String people;
    private Boolean touch;
    private boolean level;
    
	public Selner(Player player, String key) {
		super(player, Roles.SELNER);
		player.sendMessage(GeneralVariable.MessagePrefix + "You are now playing as §6" + Selner.getDescription());
        this.name = Roles.SELNER;
		this.day = 0;
		this.level = false;
		getAbility(player);
		this.people = "";
		this.dayPower = 0;
		this.Eveil = true;
		this.touch = false;
		// TODO Auto-generated constructor stub
	}
	
    public String getName() { // Add a getter method for the name field
        return name;
    }
    public Boolean getTouch() {
    	return touch;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    
    public void touchAbility(Player player, String Name, Character victim, CharacterManager characterManager) {
        System.out.print(people);
        System.out.print(Name);
        System.out.print(this.touch);
        if (this.touch && Name.equals(people)) {
            if (victim instanceof Chasseurs) {
                if (!((Chasseurs) victim).getEveil()) {
                    player.sendMessage(GeneralVariable.MessagePrefix+"Ce joueur peut etre levelup son rang est §6" + ((Chasseurs) victim).getRank());
                }else {
                    player.sendMessage(GeneralVariable.MessagePrefix+"Ce joueur ne peut etre levelup son rang est §6"+ ((Chasseurs) victim).getRank());
                }
            } else {
                player.sendMessage(GeneralVariable.MessagePrefix+"Ce joueur ne peut etre upgrade sonr rang est S ou +");
            }
            this.touch = false;
        } else if (Name.equals(people)) {
            player.sendMessage(GeneralVariable.MessagePrefix+"tu a deja taper ta cible");
        }
    }

   
    
    public void setPeople(String NAAme,int days,Player player) {
    	if (days == dayPower) {
    		player.sendMessage(GeneralVariable.MessagePrefix+"Tu a deja utilisé ton pouvoir cette Journée");
    	}else {
    		dayPower = days;
    		this.touch = true;
    		this.people = NAAme;
    		player.sendMessage(GeneralVariable.MessagePrefix+"tu a choisie "+ this.people);
    	}
    }
    
    public void eveilAbility(Chasseurs player, Player player2, Player player3) {
    	if (player.getEveil() == false && this.level == false) {
    		player.setEveil(true,player2);
    		this.level = true;
    		player3.sendMessage(GeneralVariable.MessagePrefix+" tu a utilisé ton LevelUp");
    	}
    }
    @Override
    public void setEveil(boolean change, Player player) {
        this.Eveil = change;
        player.sendMessage("Nouvelle implémentation de la méthode setEveil dans MaClasseChasseur");
    }
    
    public void setlevel(boolean value) {
    	this.level = value;
    }
    
    public void makeInvisibleIfNoArmor(Player player,int days) {
        if (hasArmor(player)) {
        	if (days == day) {
        		player.sendMessage(GeneralVariable.MessagePrefix+"Tu a deja été invisible cette Journée");
        	}
        	else {
        		day = days;
        		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5 * 60 * 20, 0,false,false));
        	}
    	}
    }
    private boolean hasArmor(Player player) {
        ItemStack[] armorContents = player.getInventory().getArmorContents();
        for (ItemStack armor : armorContents) {
            if (armor != null && armor.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }
    
    public void useAbility(Player player,String string) {
    	player.sendMessage(GeneralVariable.MessagePrefix+"Tu a regardé un membre des "+ string);
    }
    
    public void getAbility(Player player) {
    	player.sendMessage("§6-------------------------\n"
    			+GeneralVariable.MessagePrefix+"§bTu joue §6Selner §bTu gagne avec les Chasseurs\n"
    			+ "§6-------------------------\n"
    			+ "§5Tu possède 2 pouvoir :\n"
    			+ "§b- tu peux devenir invisible pendant 5 min une fois par jour en enlevant ton armure\n"
    			+ "§b- Une fois par jour tu peux marquer une personne et si tu la touche tu obtiendra son aura\n"
    			+ " §4 pour cela utilise la commande /sl touch <joueur> \n"
    			+ "§4Une fois dans la partie du declencher l'eveil d'une personne avec la commande /sl levelup <joueur>\n"
    			+ "§6-------------------------");
    
    
    }

}
