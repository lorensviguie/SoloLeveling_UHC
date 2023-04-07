package fr.farkas.Main.commands;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.farkas.Main.UHCListeners;
import fr.farkas.Main.UHCRule.ApplyRules;
import fr.farkas.Main.configuration.BasicInventoryConfig;
import fr.farkas.Main.game.Scoreboard;

public class CommandSpawn implements CommandExecutor {
	
	private BasicInventoryConfig basicInventory;
	private Scoreboard scoreboard;
	private Map<String, List<String>> configdata;
	
	public CommandSpawn(Map<String, List<String>> configData, BasicInventoryConfig basicInventory, Scoreboard scoreboard) {
		this.basicInventory = basicInventory;
		this.scoreboard = scoreboard;
		this.configdata = configData;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.isOp()) {
			if (args[0].equalsIgnoreCase("start")) {
				this.scoreboard.GetTimer().Start();
				UHCListeners.onstart();
				ApplyRules uhcrule = new ApplyRules(configdata);
				uhcrule.Applyallrules();
				return true;
			}
			if (args[0].equalsIgnoreCase("config")) {
				basicInventory.editConfig((Player) sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("stop")) {
				scoreboard.GetTimer().Stop();
				for (Player player : Bukkit.getOnlinePlayers()) {
				    // Kill the player
					player.getInventory().clear();
				    player.setHealth(0);
				}
				return true;
			}
		}
		return false;
	}

}
