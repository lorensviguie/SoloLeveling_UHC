package fr.farkas.Main.General.World;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;

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
    
    public void genPortal() {
    	System.out.print("Gen portal 100" + x + z);
    	org.bukkit.World world = (org.bukkit.World) Bukkit.getWorld("World");
    	world.getBlockAt(this.x,100,this.z).setType(Material.GOLD_BLOCK);
    }
    
    public String getPos() {
    	return String.format("X : %d | Z : %d", x, z);
    }
    
    public void removePortal() {
    	System.out.print("Gen portal 100" + x + z);
    	org.bukkit.World world = (org.bukkit.World) Bukkit.getWorld("World");
    	world.getBlockAt(this.x,100,this.z).setType(Material.AIR);
    }
}
