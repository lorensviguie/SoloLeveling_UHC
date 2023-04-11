package fr.farkas.Main.General.World;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.minecraft.server.v1_8_R3.World;

public class Portal implements Listener {
	private int x;
	private int z;
	
	public Portal() {
		Random random = new Random();
		this.x = random.nextInt(300 + 1 + 300) - 300;
		this.z = random.nextInt(300 + 1 + 300) - 300;
		this.getPos();
		this.genPortal();
	}
	
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // Teleportation si rentre dans le portail (Material Block a changer par un block perso
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GOLD_BLOCK) {
        	player.sendMessage("You are teleported in a portal");
        	Location loc = new Location(Bukkit.getWorld("world_nether"), 0, 100, 0);
        	player.teleport(loc);
        }
    }
    
    public void genPortal() {
    	System.out.print("Gen portal 100" + x + z);
    	org.bukkit.World world = (org.bukkit.World) Bukkit.getWorld("World");
    	world.getBlockAt(this.x,100,this.z).setType(Material.GOLD_BLOCK);
    }
    
    public String getPos() {
    	return String.format("X : %d | Z : %d", x, z);
    }

}
