package fr.farkas.Main.Characters.Fragments.GoGunHee;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.farkas.Main.Characters.Fragments.Fragments;

public class GoGunHee extends Fragments {

    private static final String DESCRIPTION = "Go Gun Hee";
    private String name;

    public GoGunHee(Player player, String key) {
        super(player, key);
        this.name = "GoGunHee";
        player.getInventory().addItem(createMat(Material.NETHER_STAR, "§6Transformation"));
    }

    public String getName() { // Add a getter method for the name field
        return name;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
    private ItemStack createMat(Material mat,String Display) {
    	ItemStack crea = new ItemStack(mat);
    	ItemMeta creaMeta = crea.getItemMeta();
    	creaMeta.setDisplayName(Display);
    	crea.setItemMeta(creaMeta);
    	return crea;
    }
}
