package fr.farkas.Main.WorldManager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;

public class GenerateRoof {
    
    public static void changeBiomeInCenter(World world) {
        Plugin worldEditPlugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (!(worldEditPlugin instanceof WorldEditPlugin)) {
            System.out.println("WorldEdit plugin not found!");
            return;
        }

        WorldEditPlugin worldEdit = (WorldEditPlugin) worldEditPlugin;

        Region region = null;
        try {
            region = worldEdit.getSession(BukkitAdapter.adapt(world)).getSelection(BukkitAdapter.adapt(world)).getRegionSelector().getRegion().expand(BlockVector3.at(250, 0, 250)).shift(Vector3.at(-250, 0, -250));
        } catch (IncompleteRegionException e) {
            e.printStackTrace();
            return;
        }

        // Get the clipboard to store the original blocks
        Clipboard clipboard = worldEdit.getWorldEdit().getClipboardFormats().getReader(worldEdit.getWorldEdit().getClipboardFormats().findByAlias("schematic")).read(null, getClass().getResourceAsStream("/schematics/empty.schem"));

        // Save the original blocks in the clipboard
        LocalSession session = worldEdit.getSession(BukkitAdapter.adapt(world));
        session.setClipboard(new ClipboardHolder(clipboard, worldEdit.getWorldEdit().getServer().createWorldData()));

        // Change the biome to plains in the region
        worldEdit.getWorldEdit().getBiomeClipboard().setBiomes(region, worldEdit.getWorldEdit().getServer().createWorldData().getBiome("plains"));

        // Paste the modified blocks back into the world
        worldEdit.getSession(BukkitAdapter.adapt(world)).paste(session.getClipboard().getClipboard(), region.getMinimumPoint(), false, true, null);
    }
}
