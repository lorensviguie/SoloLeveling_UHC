package fr.farkas.Main.Characters.Monarques.MonarqueBetes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Start;
import fr.farkas.Main.Characters.DistribRole;
import fr.farkas.Main.Characters.Monarques.Monarques;
import fr.farkas.Main.General.GeneralVariable;
import fr.farkas.Main.General.World.Portal;

public class MonarqueBetes extends Monarques{
	
	private String currentForme;
	private FormeInventory formeInventory;
    private String name = "MonarqueBetes";
    private boolean inUltimeCoolDown = false; 

    private static final String DESCRIPTION = "Monarque des Bêtes";
	
	public MonarqueBetes(Player player, String key) {
		super(player,"MonarqueBetes");
        player.sendMessage(ChatColor.GREEN + "You are now playing as " + MonarqueBetes.getDescription());
		super.getPlayer().getInventory().addItem(createMat(Material.NETHER_STAR, "§6Formes"));
		this.currentForme = "humain";
		this.formeInventory = new FormeInventory(this);
	}

    @Override
    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }

    
    public void turnToHumain() {
    	switch (this.currentForme){
	    	case "loup":
	        	super.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	        	super.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	    		break;
	    	case "ours":
	        	super.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	        	super.getPlayer().setMaxHealth(20);
	    		break;
	    	case "utlime":
	        	super.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	        	super.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	        	super.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	        	super.getPlayer().setMaxHealth(20);
	    		break;
			default:
				break;	
    	}
    	this.currentForme = "humain";
		super.getPlayer().sendMessage("§6You tooked Human forme");
    }
    
    public FormeInventory getFormeInventory() {
    	return this.formeInventory;
    }
    
    public void turnToLoup() {
    	switch (this.currentForme){
	    	case "ours":
	    		super.getPlayer().sendMessage("§6You tooked Wolf forme");
	        	super.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	        	super.getPlayer().setMaxHealth(20);
	    		break;
			default:
				break;	
    	}
    	this.currentForme = "loup";
		super.getPlayer().sendMessage("§6You tooked Wolf forme");
    	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    }
    
    
    public void turnToOurs() {
    	switch (this.currentForme){
	    	case "loup":
	        	super.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	        	super.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	    		break;
			default:
				break;	
    	}
    	this.currentForme = "ours";
		super.getPlayer().sendMessage("§6You tooked Bear forme");
    	super.getPlayer().setMaxHealth(26);
    	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
    	if(super.getPlayer().getHealth() == 20) {
        	super.getPlayer().setHealth(26);
    	}
    }
    
    public void turnToUltime() {
    	if(Monarques.areSolo && !this.inUltimeCoolDown) {
    		switch (this.currentForme){
	    	case "loup":
	        	super.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	        	super.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	    		break;
	    	case "ours":
	        	super.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	        	super.getPlayer().setMaxHealth(20);
	    		break;
			default:
				break;	
    		}

        	this.currentForme = "ultime";
    		super.getPlayer().sendMessage("§6You tooked Ultime forme");
        	super.getPlayer().setMaxHealth(26);
        	if(super.getPlayer().getHealth() == 20) {
            	super.getPlayer().setHealth(26);
        	}
        	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, GeneralVariable.ultimeEffect_MonarqueBetes, 0));
        	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, GeneralVariable.ultimeEffect_MonarqueBetes, 0));
        	super.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, GeneralVariable.ultimeEffect_MonarqueBetes, 0));
        	
        	Player player = super.getPlayer();
        	
        	new BukkitRunnable() {
            	@Override
            	public void run() {
            		player.sendMessage("§6Your strength leaves you, you return to your human form.");
            		turnToHumain();
            		inUltimeCoolDown = true;
            		
            		new BukkitRunnable() {
            			@Override
            			public void run() {
            				inUltimeCoolDown = false;
                    		player.sendMessage("§6You can reuse your ultime form.");
            			}
            		}.runTaskLater(Start.getPlugin(), GeneralVariable.ultimeCooldown_MonarqueBetes);
            		
            	}
        	}.runTaskLater(Start.getPlugin(), GeneralVariable.ultimeEffect_MonarqueBetes);
    		
    	}
    }
    
    public String getForme() {
    	return this.currentForme;
    }
    
    public boolean isInUltimeCooldown() {
    	return this.inUltimeCoolDown;
    }
    

    private ItemStack createMat(Material mat,String Display) {
    	ItemStack crea = new ItemStack(mat);
    	ItemMeta creaMeta = crea.getItemMeta();
    	creaMeta.setDisplayName(Display);
    	crea.setItemMeta(creaMeta);
    	return crea;
    }
    public void getAbility(Player player) {
    	player.sendMessage("§2Tu joue Le monarques des bêtes Tu gagne avec les Monarques\n"
    			+ "§6-------------------------\n"
    			+ "§9Tu possede 3 formes :\n"
    			+ "§2 Huamin -aucun effet\n"
    			+ "§2 Loup -force 1 speed 1\n"
    			+ "§2 ours -3coeur en plus et resistance\n"
    			+ "§6si tu passe solo tu debloque la forme ultime qui te donne tout les effet mais tu ne peux lactiver que pendant 5 min par jour"
    			+ "§6-------------------------");
    
    }
}
