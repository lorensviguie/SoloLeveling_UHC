package fr.farkas.Main.Commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.farkas.Main.Start;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.Chasseurs;
import fr.farkas.Main.Characters.Chasseurs.ChaHaeIn.ChaHaeIn;
import fr.farkas.Main.Characters.Chasseurs.GotoRyuji.GotoRyuji;
import fr.farkas.Main.Characters.Chasseurs.MinByung.MinByung;
import fr.farkas.Main.Characters.Chasseurs.Selner.Selner;
import fr.farkas.Main.Characters.Chasseurs.WooChinjul.WooChinjul;
import fr.farkas.Main.Characters.Chasseurs.YooJinho.YooJinHo;
import fr.farkas.Main.Characters.Fragments.ChristopherReed.ChristopherReed;
import fr.farkas.Main.Characters.Monarques.ListMonarques;
import fr.farkas.Main.Characters.Monstres.Monstres;
import fr.farkas.Main.Characters.Monstres.Esil.Esil;
import fr.farkas.Main.Characters.Solos.Architecte.Architecte;
import fr.farkas.Main.Characters.SungJinWoo.SungJinWoo;
import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.GeneralVariable;

public class SlDarkCommands implements CommandExecutor {
	private CharacterManager characterManager;
	private Game game;
	public SlDarkCommands(CharacterManager characterManager,Game game) {
        this.characterManager = characterManager;
        this.game = game;
    }
//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        
//		if (!(sender instanceof Player)) {
//            sender.sendMessage("This command can only be run by a player.");
//            return true;
//        }
//        Player player = (Player) sender;
//        try {
//        if (characterManager.getCharacterName(player) == null) {
//            player.sendMessage(ChatColor.RED + "You must be playing as a character to use this command.");
//            return true;
//        }
//        }catch (NullPointerException e) {
//            // handle the error gracefully, for example:
//            player.sendMessage(ChatColor.RED + "You must be playing as a character to use this command.");
//            return true;
//        }
//        if (args.length < 1) {
//            player.sendMessage(ChatColor.RED + "Usage: /sl <ability>");
//            return true;
//        }
//        if (args[0].equalsIgnoreCase("special")) {
//        	characterManager.getCharacter(player).getAbility(player);
//        	return true;
//        }
        
//        if (args[0].equalsIgnoreCase("enchant")) {
//        	if (!(characterManager.getCharacterName(player).equals(Roles.GOTORYUJI))) {
//            	player.sendMessage(ChatColor.RED + "You must be playing Goto Ryuji to use this command.");
//            	return true;
//            }else {
//            	GotoRyuji gotoryuji = (GotoRyuji) characterManager.getCharacter(player);
//            	gotoryuji.UseAbility();
//            	return true;
//            }
//        }
        
//        if (args[0].equalsIgnoreCase("sentir")) {
//        	
//            if (!(characterManager.getCharacterName(player).equals(Roles.CHAHAEIN))) {
//            	player.sendMessage(ChatColor.RED + "You must be playing as Cha Hae In to use this command.");
//            	return true;
//            }else {
//            	boolean eveil = false;
//            	int day = game.getScoreboard().GetTimer().getDay();
//            	Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
//            	ChaHaeIn chaHaeIn = (ChaHaeIn) characterManager.getCharacter(player);
//            	if (args[1] == null) {
//            		player.sendMessage("You must tag a player with your command retry");
//            	}else {
//            		Player test =	Bukkit.getServer().getPlayer(args[1]);
//            		if (characterManager.getCharacterName(test) == Roles.SUNGJINWOO) {
//            			eveil =true;
//            		}
//            	}
//            	chaHaeIn.sentir(player, plugin,day,eveil);
//            	return true;
//            }     
//        }
        
//        if (args[0].equalsIgnoreCase("trouver")) {
//        	if (!(characterManager.getCharacterName(player).equals(Roles.WOOCHINJUL))) {
//            	player.sendMessage(ChatColor.RED + "You must be playing as WooChinJul to use this command.");
//            	return true;
//        	}else {
//        		int day = game.getScoreboard().GetTimer().getDay();
//        		Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
//        		WooChinjul woochinjul = (WooChinjul) characterManager.getCharacter(player);
//        		if (args[1] == null) {
//            		player.sendMessage("You must tag a player with your command retry");
//            		return true;
//            	}else {
//            		Player test =	Bukkit.getServer().getPlayer(args[1]);
//            		woochinjul.trouver(player, test, plugin, day);
//            		return true;
//            	}
//
//        	}
//        }
        
//        if (args[0].equalsIgnoreCase("buff")) {
//            if (!(characterManager.getCharacterName(player).equals(Roles.MINBYUNG))) {
//                player.sendMessage(ChatColor.RED + "You must be playing as MinByung to use this command.");
//                return true;
//            } else {
//                int day = game.getScoreboard().GetTimer().getDay();
//                MinByung minbyung = (MinByung) characterManager.getCharacter(player);
//                Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
//                if (targetPlayer == null) {
//                    player.sendMessage(ChatColor.RED + "Player not found or not online. Retry");
//                    return true;
//                }
//                
//                minbyung.giveBuff(characterManager.getCharacter(targetPlayer),targetPlayer,day, player);
//                return true;
//            }
//        }

        
//        if (args[0].equalsIgnoreCase("touch")) {
//        	if (!(characterManager.getCharacterName(player).equals(Roles.SELNER))) {
//            	player.sendMessage(ChatColor.RED + "You must be playing as Selner to use this command.");
//            	return true;
//        	}else {
//        		int day = game.getScoreboard().GetTimer().getDay();
//        		Selner selner = (Selner) characterManager.getCharacter(player);
//        		selner.setPeople(args[1],day,player);
//        		return true;
//        	}
//        }
//        if (args[0].equalsIgnoreCase("levelup")) {
//            if (!(characterManager.getCharacterName(player).equals(Roles.SELNER))) {
//                player.sendMessage(ChatColor.RED + "You must be playing as Selner to use this command.");
//                return true;
//            } else {
//                Selner selner = (Selner) characterManager.getCharacter(player);
//                Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
//                fr.farkas.Main.Characters.Character targetCharacter = characterManager.getCharacter(targetPlayer);
//                if (targetCharacter instanceof Chasseurs) {
//                    Chasseurs chasseurTarget = (Chasseurs) targetCharacter;
//                    selner.eveilAbility(chasseurTarget, targetPlayer, player);
//                    return true;
//                }else {
//                	selner.setlevel(true);
//                	player.sendMessage(GeneralVariable.MessagePrefix+" You Use your levelUp power");
//                }
//            }
//        }

//        if (args[0].equalsIgnoreCase("Liste")) {
//        	if (characterManager.getCharacter(player) != null) {
//        		if (characterManager.getCharacterName(player) == Roles.ANTHARES) {
//        			ListMonarques.SetupList(characterManager,player);
//        		}
//        	}
//        	return true;
//        }
//        if (args[0].equalsIgnoreCase("Fire")) {
//        	if (!(characterManager.getCharacterName(player).equals(Roles.CHRISTOPHERREED))) {
//        		player.sendMessage(ChatColor.RED + "You must be Christopher Reed as Selner to use this command.");
//        	}else {
//        		ChristopherReed christopherReed = (ChristopherReed) characterManager.getCharacter(player);
//        		christopherReed.setFire(player);
//        	}
//        }
//        if (args[0].equalsIgnoreCase("eveil")) {
//            if (!(characterManager.getCharacterName(player).equals(Roles.SUNGJINWOO))) {
//            	player.sendMessage(ChatColor.RED + "You must be playing as SungJinWoo to use this command.");
//            	return true;
//            }else {
//            	if (game.getScoreboard().GetTimer().getDay() >= 3) {
//            	SungJinWoo sungJinWoo = (SungJinWoo) characterManager.getCharacter(player);
//            	sungJinWoo.setEveil(true);
//            	return true;
//            	}
//            	player.sendMessage("Trop tôt");
//            	return true;
//            }     
//        }
//        if (args[0].equalsIgnoreCase("BlackHearth")) {
//            if (!(characterManager.getCharacterName(player).equals(Roles.ARCHITECT))) {
//            	player.sendMessage(ChatColor.RED + "You must be playing as Architect to use this command.");
//            	return true;
//            }else {
//            	if (characterManager.getPlayerWithSungJinWoo() != null) {
//            		//SungJinWoo sungjinwoo = (SungJinWoo) characterManager.getCharacter(characterManager.getPlayerWithSungJinWoo());
//            		if (game.getScoreboard().GetTimer().getDay() >= 5) {
//            			Architecte architect = (Architecte) characterManager.getCharacter(player);
//            			architect.BlackHearth(player, characterManager.getPlayerWithSungJinWoo());
//            			characterManager.setBlackHearth(true);
//            			return true;
//            		}
//            		player.sendMessage("Trop tôt");
//            		return true;
//            	}     
//            }
//        }

        
//        switch (args[0].toLowerCase()) {
//        case "help":
//            player.sendMessage(ChatColor.GOLD + "-------- SLDark's Commands --------");
//            player.sendMessage(ChatColor.GREEN + "/sl special - Use your character's special ability");
//            player.sendMessage(ChatColor.GREEN + "/sl help - Show this help message");
//            break;
//        default:
//            player.sendMessage(ChatColor.RED + "Invalid command. Use /sl help for a list of commands.");
//            break;
//    }
//       
//		return false;
//	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (!(sender instanceof Player)) {
	        sender.sendMessage(GeneralVariable.MessagePrefix+"This command can only be run by a player.");
	        return true;
	    }
	    Player player = (Player) sender;

	    try {
	        if (characterManager.getCharacterName(player) == null) {
	            player.sendMessage(GeneralVariable.MessagePrefix+ChatColor.RED + "You must be playing as a character to use this command.");
	            return true;
	        }
	    } catch (NullPointerException e) {
	        player.sendMessage(GeneralVariable.MessagePrefix+ChatColor.RED + "You must be playing as a character to use this command.");
	        return true;
	    }

	    if (args.length < 1) {
	        player.sendMessage(GeneralVariable.MessagePrefix+ChatColor.RED + "Usage: /sl <ability>");
	        return true;
	    }
	    if (args[0].equalsIgnoreCase("special")) {
	    	characterManager.getCharacter(player).getAbility(player);
        	return true;
	    }

	    String roleName = characterManager.getCharacterName(player);

	    switch (roleName) {
	        case Roles.MINBYUNG:
	            if (args[0].equalsIgnoreCase("buff")) {	            	
	            	int day = game.getScoreboard().GetTimer().getDay();
	                MinByung minbyung = (MinByung) characterManager.getCharacter(player);
	                Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
	                if (targetPlayer == null) {
	                	player.sendMessage(GeneralVariable.MessagePrefix+ChatColor.RED + "Player not found or not online. Retry");
	                    return true;
	                }
	                minbyung.giveBuff(characterManager.getCharacter(targetPlayer),targetPlayer,day, player);
	                return true;
	            }
	            break;
	        case Roles.SELNER:
	            if (args[0].equalsIgnoreCase("touch")) {	            
	            	int day = game.getScoreboard().GetTimer().getDay();
	            	Selner selner = (Selner) characterManager.getCharacter(player);
	           		selner.setPeople(args[1],day,player);
	           		return true;
	            } else if (args[0].equalsIgnoreCase("levelup")) {
	            	Selner selner = (Selner) characterManager.getCharacter(player);
	                Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
	                fr.farkas.Main.Characters.Character targetCharacter = characterManager.getCharacter(targetPlayer);
	                if (targetCharacter instanceof Chasseurs) {
	                    Chasseurs chasseurTarget = (Chasseurs) targetCharacter;
	                    selner.eveilAbility(chasseurTarget, targetPlayer, player);
	                    return true;
	                }else {
	                	selner.setlevel(true);
	                	player.sendMessage(GeneralVariable.MessagePrefix+" tu a utilisé ton LevelUp");
	                }
	            }
	            break;
	        case Roles.GOTORYUJI:
	            if (args[0].equalsIgnoreCase("enchant")) {
	            	GotoRyuji gotoryuji = (GotoRyuji) characterManager.getCharacter(player);
	            	gotoryuji.UseAbility(player);
	            	return true;
	            }
	            break;
	        case Roles.CHAHAEIN:
	            if (args[0].equalsIgnoreCase("sentir")) {
	            	boolean eveil = false;
	            	int day = game.getScoreboard().GetTimer().getDay();
	            	Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
	            	ChaHaeIn chaHaeIn = (ChaHaeIn) characterManager.getCharacter(player);
	            	if (args[1] == null) {
	            		player.sendMessage(GeneralVariable.MessagePrefix+"You must tag a player with your command retry");
	            	}else {
	            		Player test =	Bukkit.getServer().getPlayer(args[1]);
	            		if (characterManager.getCharacterName(test) == Roles.SUNGJINWOO) {
	            			eveil =true;
	            		}
	            	}
	            	chaHaeIn.sentir(player, plugin,day,eveil);
	            	return true;
	            }
	            break;
	        case Roles.WOOCHINJUL:
	            if (args[0].equalsIgnoreCase("trouver")) {
	            	int day = game.getScoreboard().GetTimer().getDay();
	        		Start plugin = (Start) Bukkit.getServer().getPluginManager().getPlugin("UHC SoloLeveling");
	        		WooChinjul woochinjul = (WooChinjul) characterManager.getCharacter(player);
	        		if (args[1] == null) {
	            		player.sendMessage(GeneralVariable.MessagePrefix+"You must tag a player with your command retry");
	            		return true;
	            	}else {
	            		Player test =	Bukkit.getServer().getPlayer(args[1]);
	            		woochinjul.trouver(player, test, plugin, day);
	            		return true;
	            	}
	            }
	            break;
	        case Roles.ANTHARES:
	        	if (args[0].equalsIgnoreCase("liste")) {
	        		ListMonarques.SetupList(characterManager,player);
	        	}
	        	break;
	        case Roles.CHRISTOPHERREED:
	        	if (args[0].equalsIgnoreCase("Fire")) {
	        		ChristopherReed christopherReed = (ChristopherReed) characterManager.getCharacter(player);
        			christopherReed.setFire(player);
	    		}
        	
	        case Roles.BERU:
	        	if (args[0].equalsIgnoreCase("liste")) {
	        		Monstres.sendlist(player);
	        	}
	        	break;
	        case Roles.BARUKA:
	        	if (args[0].equalsIgnoreCase("liste")) {
	        		Monstres.sendlist(player);
	        	}
	        	break;
	        case Roles.VULCAN:
	        	if (args[0].equalsIgnoreCase("liste")) {
	        		Monstres.sendlist(player);
	        	}
	        	break;
	        case Roles.ESIL:
	        	if (args[0].equalsIgnoreCase("liste")) {
	        		Monstres.sendlist(player);
	        	}else if (args[0].equalsIgnoreCase("teleport")) {
	        		Esil esil = (Esil) characterManager.getCharacter(player);
	        		if (args[1] == null) {
	            		player.sendMessage(GeneralVariable.MessagePrefix+"You must tag a player with your command retry");
	            	}else {
	            		Player test =	Bukkit.getServer().getPlayer(args[1]);
	            		int day = game.getScoreboard().GetTimer().getDay();
	            		esil.tpintoPortal(player, test, day);
	            	}
	        	}
	        	break;
	        	
	        case Roles.SUNGJINWOO:
	        	if (game.getScoreboard().GetTimer().getDay() >= 3) {
	            	SungJinWoo sungJinWoo = (SungJinWoo) characterManager.getCharacter(player);
	            	sungJinWoo.setEveil(true);
	            	return true;
	            }
	            player.sendMessage("Trop tôt");
	            return true;
	        case Roles.YOOJINHO:
	        	if (args[0].equalsIgnoreCase("spec")) {
	        		if (args[1] == null) {
	            		player.sendMessage(GeneralVariable.MessagePrefix+"You must tag a player with your command retry");
	            		return true;
	            	}else {
	            		int day = game.getScoreboard().GetTimer().getDay();
	            		Player test =	Bukkit.getServer().getPlayer(args[1]);
	            		YooJinHo yojinho = (YooJinHo) characterManager.getCharacter(player);
	            		if (characterManager.getCharacter(test) instanceof Chasseurs) {
	            			Chasseurs chasseur = (Chasseurs)characterManager.getCharacter(test);
	            			yojinho.spec(player,test,characterManager.getCharacterName(test),chasseur.getRank(),day);
	            		}else {
	            			yojinho.spec(player,test,characterManager.getCharacterName(test),"E",day);
	            		}
	            	}
	        	}
	        // Add cases for other roles as needed
	        case Roles.ARCHITECT:
	        	if (characterManager.getPlayerWithSungJinWoo() != null) {
            		//SungJinWoo sungjinwoo = (SungJinWoo) characterManager.getCharacter(characterManager.getPlayerWithSungJinWoo());
            		if (game.getScoreboard().GetTimer().getDay() >= 5) {
            			Architecte architect = (Architecte) characterManager.getCharacter(player);
            			architect.BlackHearth(player, characterManager.getPlayerWithSungJinWoo());
            			characterManager.setBlackHearth(true);
            			return true;
            		}
            		player.sendMessage("Trop tôt");
	        	}
            	return true;
	        default:
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
	    return true;
	}
}

