package fr.farkas.Main.General.World;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.General.GeneralVariable;

public class Portal implements Listener {
	private int x;
	private int z;
	
	private static List<Player>defender = new ArrayList<>();
	private Plugin plugin;
	
	
	public Portal(Plugin plugin) {
		this.plugin = plugin;
		Random random = new Random();
		this.x = random.nextInt(300 + 1 + 300) - 300;
		this.z = random.nextInt(300 + 1 + 300) - 300;
		this.selectDefender(GeneralVariable.portalDefender);
		this.genPortal();
		
		new BukkitRunnable() {
        	@Override
        	public void run() {
        		removePortal();
        		new BukkitRunnable() {
        			@Override
        			public void run() {
        				new Portal(plugin);
        			}
        		}.runTaskLater(plugin, GeneralVariable.portalCooldown);
        	}
    	}.runTaskLater(plugin, GeneralVariable.portalDuration);
	}
    
    public void genPortal() {
    	System.out.print("Gen portal 100 " + x + z);
    	org.bukkit.World world = (org.bukkit.World) Bukkit.getWorld("Game");
    	world.getBlockAt(this.x,100,this.z).setType(Material.GOLD_BLOCK);
    }
    
    public String getPos() {
    	return String.format("X : %d | Z : %d", x, z);
    }
    
    public void removePortal() {
    	// Remove portall
    	System.out.print("Remove portal 100 " + x + z);
    	org.bukkit.World world = (org.bukkit.World) Bukkit.getWorld("Game");
    	world.getBlockAt(this.x,100,this.z).setType(Material.AIR);    	
    }
    
    public void selectDefender(int n) {
    	defender.clear();
    	// Select n random defenders in monsters list
    	for(int i = 0; i<n; i++) {
    		Random random = new Random();
    		int monstreIndex = random.nextInt(Monstres.getMembers().size());
    		if(!defender.contains(Monstres.getMembers().get(monstreIndex))) {
        		defender.add(Monstres.getMembers().get(monstreIndex));
    		}else {
    			i--;
    		}
    	}
    	// Send portal coord to defenders
    	for(Player player : defender) {
    		player.sendMessage("§6Your portal spawn at " + this.getPos());
    	}
    }
    
    public static List<Player> getDefender(){
    	return defender;
    }
    
}
