package fr.farkas.Main.PluginManager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.farkas.Main.Characters.CharacterListeners;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Fragments.GoGunHee.GoGunHee;
import fr.farkas.Main.Characters.SungJinWoo.SungJinWoo;
import fr.farkas.Main.Characters.Monarques.Legia.Legia;
import fr.farkas.Main.Characters.Monarques.MonarqueBetes.MonarqueBetes;
import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.Configuration.BasicInventoryConfig;
import fr.farkas.Main.General.World.MapManager;

public class UHCListeners implements Listener {
		
    private CharacterManager characterManager;
    private BasicInventoryConfig basicinventoryconfig;
    private MapManager mapManager;
    private Game game;
    private CharacterListeners characterListeners;


	public UHCListeners(CharacterManager characterManager, BasicInventoryConfig basicInventory,MapManager mapManager, Game game) {
		this.basicinventoryconfig = basicInventory;
		this.characterManager = characterManager;
		this.mapManager = mapManager;
		this.game = game;
		this.characterListeners = new CharacterListeners(game);
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
			
			inv.setItem(0, characterItem);
			
			inv.setItem(9, characterItem2);
			inv.setItem(10, characterItem3);
			
			inv.setItem(18, characterItem12);
			
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
		if (game.DidgameStart()){
			Player player = event.getPlayer();
			characterListeners.CharacterClick(event, player);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		if(current == null) return;
		System.out.println(game.DidgameStart());		
			if(inv.getName().equalsIgnoreCase("§eLocked Inventory")){
				basicinventoryconfig.BasicInventoryClick(event);
			}
			if(inv.getName().equalsIgnoreCase("§6UHC Basic Rules")){
				basicinventoryconfig.BasicInventoryClick(event);
			}
			if(inv.getName().equalsIgnoreCase("§6Border Config")){
				basicinventoryconfig.BasicInventoryClick(event);
			}
			if(inv.getName().equalsIgnoreCase("§6Forme Inventory")){
				System.out.print("Hey");
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
				case ANVIL:
					MonarqueBetes monarqueBetes = new MonarqueBetes(player, "GoGunHee");
					characterManager.chooseCharacter(player, monarqueBetes);
					System.out.println(characterManager.getCharacter(player));
				
					player.closeInventory();
				 	player.sendMessage(ChatColor.GREEN + "You are now playing as " + MonarqueBetes.getDescription());
				 	break;
				 	
				default:break;
				}

			
			}
		
	}
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.setMaxHealth(20);
        player.getInventory().clear();
        characterManager.removeCharacter(player);
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

public ItemStack getItem(Material material, String customDisplayName) {
	ItemStack it = new ItemStack(material, 1);
	ItemMeta itM = it.getItemMeta();
	if(customDisplayName != null) itM.setDisplayName(customDisplayName);
	it.setItemMeta(itM);
	return it;
}
}