package fr.farkas.Main.Characters.SungJinWoo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.farkas.Main.Start;

public class DarkPower {

    private Map<Player, Long> lastDarkTime = new HashMap<>();
    private Start plugin;

    public DarkPower(Start plugin) {
        this.plugin = plugin;
    }

    public boolean canUseAbility(Player player) {
        Long lastTime = lastDarkTime.get(player);
        if (lastTime == null) {
            return true; // Player has never used the ability
        }
        long currentTime = System.currentTimeMillis();
        long diff = currentTime - lastTime;
        long hoursElapsed = TimeUnit.MILLISECONDS.toHours(diff);
        if (hoursElapsed >= 24) {
            return true; // Player can use the ability again
        } else {
            long remainingHours = 24 - hoursElapsed;
            player.sendMessage(ChatColor.RED + "You can only use the Dark Power ability once per day. You can use it again in " + remainingHours + " hours.");
            return false; // Player cannot use the ability again yet
        }
    }


    public void useAbility(Player player) {
        if (canUseAbility(player)) {
    	player.sendMessage(ChatColor.GOLD + "You used the dark ability!");
        lastDarkTime.put(player, System.currentTimeMillis());
        
        

        // Apply effects
        player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 0, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 0, false, false));

        // Schedule the effects to expire
        new BukkitRunnable() {
            @Override
            public void run() {
                player.removePotionEffect(PotionEffectType.SPEED);
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                player.sendMessage(ChatColor.GRAY + "Your dark ability has expired.");
            }
            
        }.runTaskLater(plugin, 2400);
       }else {
    	   player.sendMessage(ChatColor.GOLD + "You can't use the dark ability!");
       }
    }

    public long getLastDarkTime(Player player) {
        Long lastTime = lastDarkTime.get(player);
        if (lastTime == null) {
            return 0;
        }
        return lastTime;
    }
}
