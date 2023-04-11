package fr.farkas.Main.Commands;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
<<<<<<< HEAD
	private World Lobby;
=======
	private Portal portal;
>>>>>>> 074fa6c7cdd9f551df3bfab3d41e22e984d96c3e
	
	public CommandSpawn(Map<String, List<String>> configData, BasicInventoryConfig basicInventory, Scoreboard scoreboard, World world,Game game,World Lobby) {
		this.basicInventory = basicInventory;
		this.scoreboard = scoreboard;
		this.configdata = configData;
		this.world = world;
		this.Lobby = Lobby;
		this.game = game;}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.isOp()) {
			if (args[0].equalsIgnoreCase("start")) {
				this.game.StartGame();
				BorderManager.createBorder(world, configdata);
<<<<<<< HEAD
				this.scoreboard.GetTimer().Start();
				    for(Player player : Lobby.getPlayers()) { // get all players in the first loaded world
				        player.teleport(new Location(world, 0, 120, 0)); // teleport each player to the specified location
				        player.setNoDamageTicks(20000);
				    }

=======
>>>>>>> 074fa6c7cdd9f551df3bfab3d41e22e984d96c3e
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
			    for(Player player : world.getPlayers()) { // get all players in the first loaded world
			        player.teleport(new Location(Lobby, 5, 128, 5)); // teleport each player to the specified location
			    }
				scoreboard.GetTimer().Stop();
				for (Player player : Bukkit.getOnlinePlayers()) {
				    // Kill the player
					player.getInventory().clear();
				    player.setHealth(20);
				}
				return true;
			}
			

			if (args[0].equalsIgnoreCase("portal")) {
				this.portal = new Portal();
				sender.sendMessage(portal.getPos());
				return true;
			}

			if (args[0].equalsIgnoreCase("dportal")) {
				this.portal.removePortal();
				return true;
			}
		}
		return false;
	}

}
