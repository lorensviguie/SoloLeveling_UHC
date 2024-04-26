package fr.farkas.Main.Commands;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.farkas.Main.Characters.Monarques.Monarques;
import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.GeneralVariable;
import fr.farkas.Main.General.Configuration.ApplyRules;
import fr.farkas.Main.General.Configuration.BasicInventoryConfig;
import fr.farkas.Main.General.Scoreboard.Scoreboard;
import fr.farkas.Main.General.World.BorderManager;
import fr.farkas.Main.PluginManager.UHCListeners;

public class CommandSpawn implements CommandExecutor {
	
	private BasicInventoryConfig basicInventory;
	private Scoreboard scoreboard;
	private Map<String, List<String>> configdata;
	private World world;
	private Game game;
	
	public CommandSpawn(Map<String, List<String>> configData, BasicInventoryConfig basicInventory, Scoreboard scoreboard, World world ,Game game) {
		this.basicInventory = basicInventory;
		this.scoreboard = scoreboard;
		this.configdata = configData;
		this.world = world;
		this.game = game;
		}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp()) {
			if (args[0].equalsIgnoreCase("start")) {
				if (!GeneralVariable.hisGameinprogress) {
					World Area = Bukkit.getWorld("Game");
					
					if((Bukkit.getOnlinePlayers().size() != configdata.get("CharacterList").size())) {
						sender.sendMessage("§2Le nombre de role present dans la partie est different du nombre de joueur present");
						return false;
					}else {
					this.game.StartGame();
					GeneralVariable.hisGameinprogress = true;
					BorderManager.createBorder(Area, configdata);
					this.scoreboard.GetTimer().Start();
					System.out.print(Bukkit.getWorld("Game").getTime());
					for(Player player : world.getPlayers()) {
						player.setFoodLevel(20);
			            player.setHealth(player.getMaxHealth());
					    player.setNoDamageTicks(20*10);
					    player.teleport(new Location(Area, 0, 120, 0)); // teleport each player to the specified location
					    player.setNoDamageTicks(20*10);
					    Area.setTime(0);
					}

					UHCListeners.onstart();
					ApplyRules uhcrule = new ApplyRules(configdata);
					uhcrule.Applyallrules();
					return true;
					}
				}else {
					sender.sendMessage(GeneralVariable.MessagePrefix+" !Une Game est deja en cour!");
				}
				
			}
			if (args[0].equalsIgnoreCase("config")) {
				basicInventory.editConfig((Player) sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("stop")) {
				World Area = Bukkit.getWorld("Game");
				game.StopGame();
				GeneralVariable.hisGameinprogress = false;
				BorderManager.destroyBorder(Area);
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
					game.getCharacterManager().removeCharacter(player);
					player.setMaxHealth(20);
					player.setHealth(20);
					player.getInventory().clear();
				}
			    for(Player player : Area.getPlayers()) { // get all players in the first loaded world
			    	player.setGameMode(GameMode.SURVIVAL);
			        player.teleport(new Location(world, 5, 128, 5)); // teleport each player to the specified location
			    }
			    GeneralVariable.hisGameinprogress = false;
			    for (Player player : Bukkit.getOnlinePlayers()) {
			        player.playSound(player.getLocation(), "Sound.Stop.ogg", 1.0f, 1.0f);
			    }

				return true;
			}
			
			if(args[0].equalsIgnoreCase("anthD")) {
				Monarques.campToSolo();
			}
			
		}
		return false;
	}

}
