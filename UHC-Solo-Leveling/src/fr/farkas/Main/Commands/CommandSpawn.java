package fr.farkas.Main.Commands;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.Configuration.ApplyRules;
import fr.farkas.Main.General.Configuration.BasicInventoryConfig;
import fr.farkas.Main.General.Scoreboard.Scoreboard;
import fr.farkas.Main.General.World.BorderManager;
import fr.farkas.Main.General.World.Portal;
import fr.farkas.Main.PluginManager.UHCListeners;

public class CommandSpawn implements CommandExecutor {
	
	private BasicInventoryConfig basicInventory;
	private Scoreboard scoreboard;
	private Map<String, List<String>> configdata;
	private World world;
	private Game game;
	
	public CommandSpawn(Map<String, List<String>> configData, BasicInventoryConfig basicInventory, Scoreboard scoreboard, World world,Game game) {
		this.basicInventory = basicInventory;
		this.scoreboard = scoreboard;
		this.configdata = configData;
		this.world = world;
		this.game = game;}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.isOp()) {
			if (args[0].equalsIgnoreCase("start")) {
				game.SetGameStatus(true);
				BorderManager.createBorder(world, configdata);
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
				game.SetGameStatus(false);
				BorderManager.destroyBorder(world);
				scoreboard.GetTimer().Stop();
				for (Player player : Bukkit.getOnlinePlayers()) {
				    // Kill the player
					player.getInventory().clear();
				    player.setHealth(0);
				}
				return true;
			}
			

			if (args[0].equalsIgnoreCase("portal")) {
				Portal portal = new Portal();
				sender.sendMessage(portal.getPos());
				return true;
			}
		}
		return false;
	}

}
