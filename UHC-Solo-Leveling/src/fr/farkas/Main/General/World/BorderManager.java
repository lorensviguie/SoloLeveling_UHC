package fr.farkas.Main.General.World;

import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.WorldBorder;

public class BorderManager {

    public static void createBorder(World world, Map<String, List<String>> configData) {
        WorldBorder border = world.getWorldBorder();
        List<String> rules = configData.get("Border");
        int size = Integer.parseInt(rules.get(0));
        int minimumSize = 1000;
        int adjustedSize = Math.max(size, minimumSize); // Ensure the size is at least 1000

        border.setCenter(0, 0);
        border.setSize(adjustedSize);

        // Adjust the center of the border if it exceeds the limits
        double xCenter = Math.max(Math.min(0, border.getSize() / 2 - 500), -500);
        double zCenter = Math.max(Math.min(0, border.getSize() / 2 - 500), -500);
        border.setCenter(xCenter, zCenter);
    }

    public static void destroyBorder(World world) {
        WorldBorder border = world.getWorldBorder();
        border.setCenter(0, 0);
        border.setSize(1500);
    }
}
