package fr.farkas.Main.General.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.Characters.Monstres.Baruka.Baruka;
import fr.farkas.Main.Characters.Monstres.Esil.Esil;
import fr.farkas.Main.General.GeneralVariable;


public class Portal implements Listener {
	private int centerX;
	private int centerZ;
	
	private static List<Player>defender = new ArrayList<>();
	private Plugin plugin;
	
	
	public Portal(Plugin plugin, CharacterManager characterManager) {
	    System.out.print("portal YEHOU");
	    this.plugin = plugin;
	    Random random = new Random();
	    this.centerX = random.nextInt(501) - 250; 
	    this.centerZ = random.nextInt(501) - 250;
	    this.selectDefender(GeneralVariable.portalDefender);
	    this.genPortal(Bukkit.getWorld("Game"));
	    new BukkitRunnable() {
	        @Override
	        public void run() {
	            System.out.print("portal DELETE");
	            deletePortal(Bukkit.getWorld("Game"));
	            GeneralVariable.PortailDefenseSucces = true;
	            if (characterManager.getPlayerWithBaruka() != null) {
    				Player baruka = characterManager.getPlayerWithBaruka();
    				Baruka bbaruka = (Baruka) characterManager.getCharacter(baruka);
    				bbaruka.planifierTache(plugin,baruka);
    			}
	            if (characterManager.getPlayerEsil() != null) {
    				Player esil = characterManager.getPlayerEsil();
    				Esil eesil = (Esil) characterManager.getCharacter(esil);
    				eesil.giveSpeedAndStrength(esil,plugin);
    			}
	            for (Player player : Bukkit.getWorld("world_the_end").getPlayers()) {
	                teleport.teleportPlayerRandomly(player,Bukkit.getWorld("Game"), plugin);
	                player.sendMessage(GeneralVariable.MessagePrefix+"Vous avez été téléporté car le donjon est fermé");
	            }
	            if (!GeneralVariable.hisGameinprogress) {
	                return;
	            }
	            new BukkitRunnable() {
	                @Override
	                public void run() {
	                    if (GeneralVariable.hisGameinprogress) {
	                    	GeneralVariable.PortailDefenseSucces = false;
	                        new Portal(plugin, characterManager);
	                    }
	                }
	            }.runTaskLater(plugin, GeneralVariable.portalCooldown);
	        }
	    }.runTaskLater(plugin, GeneralVariable.portalDuration);
	}
	public void eventEnd() {
		System.out.print("portal DELETE");
        deletePortal(Bukkit.getWorld("Game"));
        for (Player player : Bukkit.getWorld("world_the_end").getPlayers()) {
            teleport.teleportPlayerRandomly(player,Bukkit.getWorld("Game"), plugin);
            player.sendMessage(GeneralVariable.MessagePrefix+"Vous avez été téléporté car le donjon est Vaincu");
        }
	}


    
	public void genPortal(World world) {
	    int radius = 3;
	    int centerY = 80;
	    for (int x = this.centerX - radius; x <= this.centerX + radius; x++) {
	        for (int z = this.centerZ - radius; z <= this.centerZ + radius; z++) {
	            if (Math.pow(x - this.centerX, 2) + Math.pow(z - this.centerZ, 2) <= Math.pow(radius, 2)) {
	                Block block = world.getBlockAt(x, centerY, z);
	                block.setType(Material.EMERALD_BLOCK);
	            } else if (Math.abs(x - this.centerX) == radius || Math.abs(z - this.centerZ) == radius) {
	                Block block = world.getBlockAt(x, centerY, z);
	                block.setType(Material.OBSIDIAN);
	            }
	        }
	    }
	}
    public String getPos() {
    	return String.format("X : %d | Z : %d", this.centerX, this.centerZ);
    }
    
    public void deletePortal(World world) {
        int radius = 3;
        int centerY = 0;
        for (int x = this.centerX - radius; x <= this.centerX + radius; x++) {
            for (int z = this.centerZ - radius; z <= this.centerZ + radius; z++) {
                for (int y = centerY; y <= centerY + 80; y++) {
                    Block block = world.getBlockAt(x, y, z);
                    if (block.getType() == Material.EMERALD_BLOCK || block.getType() == Material.OBSIDIAN) {
                        block.setType(Material.AIR);
                    }
                }
            }
        }
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
    	for(Player player : defender) {
    		player.sendMessage("§6Your portal spawn at " + this.getPos());
    	}
    }
    
    public static List<Player> getDefender(){
    	return defender;
    }
}
