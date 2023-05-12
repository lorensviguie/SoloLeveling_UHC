package fr.farkas.Main.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.farkas.Main.Start;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Chasseurs.ChaHaeIn.ChaHaeIn;
import fr.farkas.Main.Characters.Chasseurs.Selner.Selner;
import fr.farkas.Main.Characters.Fragments.ChristopherReed.ChristopherReed;
import fr.farkas.Main.Characters.Monarques.ListMonarques;
import fr.farkas.Main.Characters.Solos.Architecte.Architecte;
import fr.farkas.Main.Characters.SungJinWoo.SungJinWoo;
import fr.farkas.Main.General.Game;

public class SlDarkCommands implements CommandExecutor {
	private CharacterManager characterManager;
	private Game game;
    
    public SlDarkCommands(CharacterManager characterManager,Game game) {
        this.characterManager = characterManager;
        this.game = game;
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
        if (args[0].equalsIgnoreCase("special")) {
        	characterManager.getCharacter(player).getAbility(player);
        	return true;
        }
        
        if (args[0].equalsIgnoreCase("sentir")) {
        	
            if (!(characterManager.getCharacterName(player).equals("ChaHaeIn"))) {
            	player.sendMessage(ChatColor.RED + "You must be playing as Cha Hae In to use this command.");
            	return true;
            }else {
            	boolean eveil = false;
            	int day = game.getScoreboard().GetTimer().getDay();
            	Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
            	ChaHaeIn chaHaeIn = (ChaHaeIn) characterManager.getCharacter(player);
            	if (args[1] == null) {
            	}else {
                Player test =	Bukkit.getServer().getPlayer(args[1]);
                if (characterManager.getCharacterName(test) == "SungJinWoo") {
                eveil =true;
                }
            	}
            	chaHaeIn.sentir(player, plugin,day,eveil);
            	return true;
            }     
        }
        if (args[0].equalsIgnoreCase("touch")) {
        	if (!(characterManager.getCharacterName(player).equals("Selner"))) {
            	player.sendMessage(ChatColor.RED + "You must be playing as Selner to use this command.");
            	return true;
        	}else {
        		int day = game.getScoreboard().GetTimer().getDay();
        		Selner selner = (Selner) characterManager.getCharacter(player);
        		selner.setPeople(args[1],day,player);
        		player.sendMessage("tu a choisie "+ args[1]);
        		return true;
        	}
        }
        if (args[0].equalsIgnoreCase("Liste")) {
        	if (characterManager.getCharacter(player) != null) {
        		if (characterManager.getCharacterName(player) == "Anthares") {
        			ListMonarques.SetupList(characterManager,player);
        		}
        	}
        	return true;
        }
        if (args[0].equalsIgnoreCase("Fire")) {
        	if (!(characterManager.getCharacterName(player).equals("ChristopherReed"))) {
        		player.sendMessage(ChatColor.RED + "You must be Christopher Reed as Selner to use this command.");
        	}else {
        		ChristopherReed christopherReed = (ChristopherReed) characterManager.getCharacter(player);
        		christopherReed.setFire(player);
        	}
        }
        if (args[0].equalsIgnoreCase("eveil")) {
            if (!(characterManager.getCharacterName(player).equals("SungJinWoo"))) {
            	player.sendMessage(ChatColor.RED + "You must be playing as SungJinWoo to use this command.");
            	return true;
            }else {
            	if (game.getScoreboard().GetTimer().getDay() == 3) {
            	SungJinWoo sungJinWoo = (SungJinWoo) characterManager.getCharacter(player);
            	sungJinWoo.setEveil(true);
            	return true;
            	}
            	player.sendMessage("Trop tôt");
            	return true;
            }     
        }
        if (args[0].equalsIgnoreCase("BlackHearth")) {
            if (!(characterManager.getCharacterName(player).equals("Architect"))) {
            	player.sendMessage(ChatColor.RED + "You must be playing as Architect to use this command.");
            	return true;
            }else {
            	if (characterManager.getPlayerWithSungJinWoo() != null) {
            		//SungJinWoo sungjinwoo = (SungJinWoo) characterManager.getCharacter(characterManager.getPlayerWithSungJinWoo());
            		if (game.getScoreboard().GetTimer().getDay() == 5) {
            			Architecte architect = (Architecte) characterManager.getCharacter(player);
            			architect.BlackHearth(player, characterManager.getPlayerWithSungJinWoo());
            			characterManager.setBlackHearth(true);
            			return true;
            		}
            		player.sendMessage("Trop tôt");
            		return true;
            	}     
            }
        }

        
        switch (args[0].toLowerCase()) {
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
