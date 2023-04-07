package fr.farkas.Main.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.farkas.Main.Start;
import fr.farkas.Main.Character.CharacterManager;
import fr.farkas.Main.Character.SungJinWoo.DarkPower;
import fr.farkas.Main.Character.SungJinWoo.ShadowPower;
import fr.farkas.Main.Character.SungJinWoo.ShadowTpPower;

public class SlDarkCommands implements CommandExecutor {
	private CharacterManager characterManager;
    
    public SlDarkCommands(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
		if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }
        Player player = (Player) sender;
        try {
        if (characterManager.getCharacterName(player) == null) {
            player.sendMessage(ChatColor.RED + "You must be playing as a character to use this command.");
            return true;
        }
        }catch (NullPointerException e) {
            // handle the error gracefully, for example:
            player.sendMessage(ChatColor.RED + "You must be playing as a character to use this command.");
            return true;
        }
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /sl <ability>");
            return true;
        }
        if (args[0].equalsIgnoreCase("dark")) {
            if (!(characterManager.getCharacterName(player).equals("SungJinWoo"))) {
            	player.sendMessage(ChatColor.RED + "You must be playing as SungJinWoo to use this command.");
            	return true;
            }else {
            	
            	Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
            	DarkPower darkPower = new  DarkPower(plugin);
            	darkPower.useAbility(player);
            	return true;
            }     
        }
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
        
        switch (args[0].toLowerCase()) {
        case "special":
        	player.sendMessage(characterManager.specialAbility(player));
            break;
        case "help":
            player.sendMessage(ChatColor.GOLD + "-------- SLDark's Commands --------");
            player.sendMessage(ChatColor.GREEN + "/sl special - Use your character's special ability");
            player.sendMessage(ChatColor.GREEN + "/sl help - Show this help message");
            break;
        default:
            player.sendMessage(ChatColor.RED + "Invalid command. Use /sl help for a list of commands.");
            break;
    }
       
		return false;
	}

}
