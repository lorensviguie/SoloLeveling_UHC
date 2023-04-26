package fr.farkas.Main.PluginManager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.farkas.Main.Characters.CharacterListeners;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Chasseurs.ChaHaeIn.ChaHaeIn;
import fr.farkas.Main.Characters.Chasseurs.Selner.Selner;
import fr.farkas.Main.Characters.Fragments.GoGunHee.GoGunHee;
import fr.farkas.Main.Characters.Fragments.ThomasAndre.ThomasAndre;
import fr.farkas.Main.Characters.Monarques.Legia.Legia;
import fr.farkas.Main.Characters.Monarques.MonarqueBetes.MonarqueBetes;
import fr.farkas.Main.Characters.Solos.Architecte.Architecte;
import fr.farkas.Main.Characters.SungJinWoo.SungJinWoo;
import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.Configuration.BasicInventoryConfig;
import fr.farkas.Main.General.World.MapManager;

public class UHCListeners implements Listener {
		
    private CharacterManager characterManager;
    private BasicInventoryConfig basicinventoryconfig;
    private MapManager mapManager;
    private Game game;
    private CharacterListeners characterListeners;
    private DiamondLimit diamondCheck;


	public UHCListeners(CharacterManager characterManager, BasicInventoryConfig basicInventory,MapManager mapManager, Game game) {
		this.basicinventoryconfig = basicInventory;
		this.characterManager = characterManager;
		this.mapManager = mapManager;
		this.game = game;
		this.characterListeners = new CharacterListeners(game);
		this.diamondCheck = new DiamondLimit();
	}


	public static void onstart() {
		List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());

		for (Player player : players) {
		ItemStack customsword = new ItemStack(Material.COMPASS, 1);
		ItemMeta customM = customsword.getItemMeta();
		customM.setDisplayName("§dConfigurations");
		customM.addEnchant(Enchantment.DAMAGE_ALL, 9, true);
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customsword.setItemMeta(customM);
		
		player.getInventory().setItem(2, customsword);
				
		player.updateInventory();
		}		
	}
	
	
	@EventHandler
	 public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		ItemStack it = event.getItem();
		if(it == null)return;
		
		
		if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§dConfigurations")) {
			Inventory inv = Bukkit.createInventory(null, 36, "§7Config");
						
			ItemStack characterItem = getItem(Material.OBSIDIAN, "§6Sung Jin Woo");
			ItemStack characterItem12 = getItem(Material.DAYLIGHT_DETECTOR, "§6Go Gun Hee");
			ItemStack characterItem2 = getItem(Material.BOOKSHELF, "§6Legia");
			ItemStack characterItem3 = getItem(Material.ANVIL, "§6Monarque des Betes");
			ItemStack characterItem4 = getItem(Material.IRON_SWORD, "§6Thomas Andre");
			ItemStack characterItem666 = getItem(Material.BLAZE_ROD, "§0Architecte");
			ItemStack characterItem29 = getItem(Material.GOLD_SWORD, "§2Cha Hae In");
			ItemStack characterItem39 = getItem(Material.BEACON, "§2Selner");
			inv.setItem(0, characterItem);
			
			inv.setItem(9, characterItem2);
			inv.setItem(10, characterItem3);
			inv.setItem(19, characterItem4);
			inv.setItem(22, characterItem666);
			inv.setItem(18, characterItem12);
			inv.setItem(2, characterItem29);
			inv.setItem(3, characterItem39);
			
			player.openInventory(inv);
		}
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
		game.getScoreboard().Display(event.getPlayer());
        Player player = event.getPlayer();
        mapManager.tptoSpawn(player);
    }
	
	@EventHandler
	public void onattack(PlayerInteractEvent event) {
		if (characterManager.getCharacter(event.getPlayer()) == null ) {
		}else if (game.DidgameStart()){
				Player player = event.getPlayer();
				characterListeners.CharacterClick(event, player);
		}
	}
	
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // Teleportation si rentre dans le portail (Material Block a changer par un block perso
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GOLD_BLOCK) {
        	player.sendMessage("You are teleported in a portal");
        	Location loc = new Location(Bukkit.getWorld("world_nether"), 0, 100, 0);
        	player.teleport(loc);
        }
    }
	
	@EventHandler
	public void ondestroy(BlockBreakEvent blockBreak) {
		Player player = blockBreak.getPlayer();
	    Block block = blockBreak.getBlock();
	    if (block.getType() == Material.DIAMOND_ORE) {
	    	diamondCheck.checkdiamondlimit(blockBreak, player ,block);
	    }else  if (block.getType() == Material.IRON_ORE) {
	    	autoFurnace.Iron(blockBreak, player, block);
	    }else if (block.getType() == Material.GOLD_ORE) {
	    	autoFurnace.Gold(blockBreak, player ,block);
	    }
	    
	}
	@EventHandler
	public void onCraftItem(CraftItemEvent event) {
	    ItemStack item = event.getRecipe().getResult();
	    if (item.getType() == Material.DIAMOND_PICKAXE || item.getType() == Material.IRON_PICKAXE) {
	        item.addEnchantment(Enchantment.DIG_SPEED, 3);
	        item.addEnchantment(Enchantment.DURABILITY, 3);
	        event.setCurrentItem(item);
	    }
	}
	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent event) {
	    if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
	        Player attacker = (Player) event.getDamager();
	        Player victim = (Player) event.getEntity();
	        
	        if ((characterManager.getCharacterName(attacker).equals("Selner"))) {
	        	Selner selner = (Selner) characterManager.getCharacter(attacker);
	        	if (selner.getTouch()) {
	        		selner.touchAbility(attacker, victim.getName(),characterManager.getCharacterName(victim));
	        	}
	        }
	        if ((characterManager.getCharacterName(attacker).equals("Beru"))) {
	            if (Math.random() < 0.1) { // 10% chance
	                PotionEffect poisonEffect = new PotionEffect(PotionEffectType.POISON, 2 * 40, 0);
	                victim.addPotionEffect(poisonEffect);
	            }
	        }
	        // Insérez le code à exécuter lorsque le joueur attaque un autre joueur ici
	    }
	}

	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        ItemStack cursorItem = event.getCursor();
        if (characterManager.getCharacter(player) != null ) {
        if (characterManager.getCharacterName(player) == "Selner") {
        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            // The player is removing armor
            if (cursorItem != null && isArmor(cursorItem.getType())) {
            	Selner selner = (Selner) characterManager.getCharacter(player);
            	int day = game.getScoreboard().GetTimer().getDay();
            	selner.makeInvisibleIfNoArmor(player,day);
            }
        }
        }
        }
		
		Inventory inv = event.getInventory();
		ItemStack current = event.getCurrentItem();
		if(current == null) return;
			if(inv.getName().equalsIgnoreCase("§eLocked Inventory")){
				basicinventoryconfig.BasicInventoryClick(event, mapManager);
			}
			if(inv.getName().equalsIgnoreCase("§6UHC Basic Rules")){
				basicinventoryconfig.BasicInventoryClick(event, null);
			}
			if(inv.getName().equalsIgnoreCase("§6Border Config")){
				basicinventoryconfig.BasicInventoryClick(event, null);
			}
			if(inv.getName().equalsIgnoreCase("§6Character Liste")) {
				basicinventoryconfig.BasicInventoryClick(event, null);
			}
			if(inv.getName().equalsIgnoreCase("§6Forme Inventory")){
				MonarqueBetes monarqueBetes = (MonarqueBetes) game.getCharacterManager().getCharacter((Player) event.getWhoClicked());
				monarqueBetes.getFormeInventory().FormeInventoryClick(event);
			}
			if(inv.getName().equalsIgnoreCase("§7Config")){
			
				event.setCancelled(true);
				player.closeInventory();
				switch(current.getType()) {
				case APPLE:
					player.setGameMode(GameMode.CREATIVE);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give @"+ player.getDisplayName()+"minecraft:diamond 2");
				
				break;
			case BOOKSHELF:
				// Assuming you have a player object called "player"
				Legia legia = new Legia(player, "legia");
				characterManager.chooseCharacter(player, legia);
				System.out.println(characterManager.getCharacter(player));

				player.closeInventory();
                player.sendMessage(ChatColor.GREEN + "You are now playing as " + Legia.getDescription());
                break;
			case OBSIDIAN:
				// Assuming you have a player object called "player"
				SungJinWoo sungJinWoo = new SungJinWoo(player, "sungjinwoo");
				characterManager.chooseCharacter(player, sungJinWoo);
				System.out.println(characterManager.getCharacter(player));

					player.closeInventory();
                	player.sendMessage(ChatColor.GREEN + "You are now playing as " + SungJinWoo.getDescription());
                	break;
				case DAYLIGHT_DETECTOR:
					GoGunHee goGunHee = new GoGunHee(player, "GoGunHee");
					characterManager.chooseCharacter(player, goGunHee);
					System.out.println(characterManager.getCharacter(player));
				
					player.closeInventory();
				 	player.sendMessage(ChatColor.GREEN + "You are now playing as " + GoGunHee.getDescription());
				 	break;
				case IRON_SWORD:
					ThomasAndre thomasAndre = new ThomasAndre(player,"ThomasAndre");
					characterManager.chooseCharacter(player, thomasAndre);
					System.out.println(characterManager.getCharacter(player));
					player.closeInventory();
                	player.sendMessage(ChatColor.GREEN + "You are now playing as " + ThomasAndre.getDescription());
					break;
				case ANVIL:
					MonarqueBetes monarqueBetes = new MonarqueBetes(player, "GoGunHee");
					characterManager.chooseCharacter(player, monarqueBetes);
					System.out.println(characterManager.getCharacter(player));
				
					player.closeInventory();
				 	player.sendMessage(ChatColor.GREEN + "You are now playing as " + MonarqueBetes.getDescription());
				 	break;
				case BLAZE_ROD:
					Architecte architecte = new Architecte(player, "Architecte");
					characterManager.chooseCharacter(player, architecte);
					player.closeInventory();
					player.sendMessage(ChatColor.GREEN + "You are now playing as " + Architecte.getDescription());
					
					break;
				case GOLD_SWORD:
					ChaHaeIn chaHaeIn = new ChaHaeIn(player, "ChaHaeIn");
					characterManager.chooseCharacter(player, chaHaeIn);
					player.closeInventory();
					player.sendMessage(ChatColor.GREEN + "You are now playing as " + ChaHaeIn.getDescription());
					break;
				case BEACON:
					Selner selner = new Selner(player, "Selner");
					characterManager.chooseCharacter(player, selner);
					player.closeInventory();
					player.sendMessage(ChatColor.GREEN + "You are now playing as " + Selner.getDescription());
					break;					
				default:break;
				}

			
			}
		
	}
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Entity killer = player.getKiller();
        player.setMaxHealth(20);
        player.getInventory().clear();
        characterManager.removeCharacter(player);
        player.spigot().respawn();
        player.setGameMode(GameMode.SPECTATOR);
        if (killer instanceof Player) {
        	Player attacker = (Player) killer;
        	player.teleport(attacker.getLocation());
        }
        
        Deathvictory.Victory(player,characterManager,game,characterListeners,mapManager);
    }
    @EventHandler
    public void OnRespawn(PlayerRespawnEvent event) {
    	//Player player = event.getPlayer();
        //SpawnManager.tptoSpawn(player, mapManager.takeLobby());
    }
public ItemStack getItem(Material material, String customDisplayName) {
	ItemStack it = new ItemStack(material, 1);
	ItemMeta itM = it.getItemMeta();
	if(customDisplayName != null) itM.setDisplayName(customDisplayName);
	it.setItemMeta(itM);
	return it;
}
private boolean isArmor(Material material) {
    return material.name().endsWith("_HELMET") ||
           material.name().endsWith("_CHESTPLATE") ||
           material.name().endsWith("_LEGGINGS") ||
           material.name().endsWith("_BOOTS");
}
}