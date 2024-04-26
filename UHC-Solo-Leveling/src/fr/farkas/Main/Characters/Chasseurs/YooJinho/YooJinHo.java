package fr.farkas.Main.Characters.Chasseurs.YooJinho;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Fragments.Fragments;
import fr.farkas.Main.General.GeneralVariable;
import fr.farkas.Main.General.World.teleport;

public class YooJinHo extends Chasseurs {
	
    private static final String DESCRIPTION = "Yoo Jinho";
    private String name;
    private int dayspec = 0;
	private int teleportedCount = 0;
	private int lastTeleportDay = 0;
	
	public YooJinHo(Player player, String key) {
		super(player, Roles.YOOJINHO);
        player.sendMessage( GeneralVariable.MessagePrefix+"You are now playing as §6" + YooJinHo.getDescription());
        getAbility(player);
        this.name = Roles.YOOJINHO;
        super.setRank("A");
        player.getInventory().addItem(Fragments.createMat(Material.NETHER_STAR, "§6TPALLMAP"));
	}


	public void useTP(Player yojinho, int currentDay, Plugin game) {
	    if (teleportedCount >= 3) {
	        yojinho.sendMessage(GeneralVariable.MessagePrefix+"tu a deja utiliser ton pouvoir 3fois");
	        return;
	    }
	    if (currentDay - lastTeleportDay < 1) {
	        yojinho.sendMessage(GeneralVariable.MessagePrefix+"tu a deja utiliser ton pouvoir aujourd'hui");
	        return;
	    }
	    if (!this.getEveil()) {
	        yojinho.sendMessage(GeneralVariable.MessagePrefix+"tu dois etre de rang A pour utiliser ce pouvoir");
	        return;
	    }
	    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
	    	teleport.teleportPlayerRandomly(player,Bukkit.getWorld("Game"),game);
	        player.sendMessage(GeneralVariable.MessagePrefix+"Vous avez été teleporter par Yoo Jin Ho!");
	    }
	    teleportedCount++;
	    lastTeleportDay = currentDay;

	    yojinho.sendMessage(GeneralVariable.MessagePrefix+"Teleportation utilisé avec succes!");
	}

	
	
	public void spec(Player yojinho, Player spectate, String rolespec, String rank,int day) {
	    if (this.dayspec == day) {
	    	yojinho.sendMessage(GeneralVariable.MessagePrefix+"Tu a deja utiliser ton pouvoir aujourd'hui");
	    }else {
	    	this.dayspec = day;
	    	PlayerInventory inventory = spectate.getInventory();
	    	int goldenAppleCount = 0;
	    	for (ItemStack item : inventory.getContents()) {
	        	if (item != null && item.getType() == Material.GOLDEN_APPLE) {
	            	goldenAppleCount += item.getAmount();
	        	}
	    	}

	    	StringBuilder effectsList = new StringBuilder();
	    	for (PotionEffect effect : spectate.getActivePotionEffects()) {
	        	effectsList.append(effect.getType().getName()).append(", ");
	    	}
	    	if (effectsList.length() > 0) {
	        	effectsList.deleteCharAt(effectsList.length() - 2); // Remove the last comma and space
	    	}

	    	String message = "Tu regarde" + spectate.getName() + ". Il a §6" +
	                     	goldenAppleCount + "§b Pommes d'or et les effets suivant actuelement: §4" +
	                     	effectsList.toString() + ".";
	    	yojinho.sendMessage(GeneralVariable.MessagePrefix+message);
	    	if (rank.equalsIgnoreCase("S")) {
	    		spectate.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 3));
	    		this.setEveil(true, yojinho);
	    	}

	    	if (this.getEveil()) {
	        	String eveilMessage = "Il joue en tant que " + rolespec + ".";
	        	yojinho.sendMessage(GeneralVariable.MessagePrefix+eveilMessage);
	    	}
	    	spectate.sendMessage(GeneralVariable.MessagePrefix+"Vous avez été spec par Yoo Jin Ho.");
	    }
	}
    @Override
    public void getAbility(Player player) {
        player.sendMessage("§6-------------------------\n"
        		+GeneralVariable.MessagePrefix+"§bTu joues §6Yoo Jinho §bTu gagnes avec les Chasseurs\n"
                + "§6-------------------------\n"
                + "§5Tu possèdes 2 pouvoirs :\n"
                + "§b- tu as la possibilité 3 fois dans la partie 1fois par jour de tp tlm de facon aleatoire\n"
                + "§b- tu possede la commande /sl spec <joueur> qui te permet d'obtenir ces effets actuel et son nombre de pomme d'or\n"
                + "§4 si tu spec un chasseur de classe S tu obtient ton eveil et tous les chasseurs de classe s qui tu va spec recevront 3 pomme d'or\n"
                + "§6-------------------------");
    }


    @Override
    public String getName() {
        return name;
    }
    public static String getDescription() {
        return DESCRIPTION;
    }
    @Override
    public void setEveil(boolean change, Player player) {
    	player.sendMessage(GeneralVariable.MessagePrefix+"§6 tu a trouver un chasseur de rang S");
        this.Eveil = change;
        super.setRank("A");
    }
}