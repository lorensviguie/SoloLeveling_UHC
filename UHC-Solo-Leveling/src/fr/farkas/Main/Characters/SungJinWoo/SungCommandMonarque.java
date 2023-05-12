package fr.farkas.Main.Characters.SungJinWoo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.farkas.Main.Start;
import fr.farkas.Main.Characters.CharacterManager;

public class SungCommandMonarque {
	public boolean SungUseCommand(CharacterManager characterManager,Player player,String[] args) {
	SungJinWoo sungjinwoo = (SungJinWoo) characterManager.getCharacter(player);
	if (sungjinwoo.getMonarque()){
    if (args[0].equalsIgnoreCase("shadow")) {
        if (!(characterManager.getCharacterName(player).equals("SungJinWoo"))) {
        	player.sendMessage(ChatColor.RED + "You must be playing as SungJinWoo to use this command.");
        	return true;
        }else {
            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Usage: /sl shadow <target>");
                return true;
            }
            Player target = (Player) Bukkit.getPlayer(args[1]);
        	Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
        	ShadowPower shadowpower = new  ShadowPower(plugin);
        	shadowpower.useAbility(player, target);
        	return true;
        }     
    }
    if (args[0].equalsIgnoreCase("shadowtp")) {
        if (!(characterManager.getCharacterName(player).equals("SungJinWoo"))) {
        	player.sendMessage(ChatColor.RED + "You must be playing as SungJinWoo to use this command.");
        	return true;
        }else {
            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Usage: /sl shadowtp <target>");
                return true;
            }
            Player target = (Player) Bukkit.getPlayer(args[1]);
        	Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
        	ShadowTpPower shadowtppower = new  ShadowTpPower(plugin);
        	shadowtppower.useAbility(player, target);
        	return true;
        }     
    }
	}
	player.sendMessage("§2 My Lord not your Time");
	return false;
	}
}
