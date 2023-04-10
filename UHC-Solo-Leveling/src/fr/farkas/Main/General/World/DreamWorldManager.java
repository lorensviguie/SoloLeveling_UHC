package fr.farkas.Main.General.World;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;

public class DreamWorldManager {
	public static void DreamWorldinitilisation() {
	World dreamWorld = Bukkit.getWorld("world_the_end");
    Entity enderDragon = dreamWorld.getEntitiesByClass(EnderDragon.class).stream().findFirst().orElse(null);
    if (enderDragon != null) {
    }
	}
}
