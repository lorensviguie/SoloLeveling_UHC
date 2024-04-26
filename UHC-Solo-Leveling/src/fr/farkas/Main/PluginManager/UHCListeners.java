package fr.farkas.Main.PluginManager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import fr.farkas.Main.Characters.CharacterListeners;
import fr.farkas.Main.Characters.CharacterManager;
import fr.farkas.Main.Characters.Roles;
import fr.farkas.Main.Characters.Chasseurs.BaekYonho.BaekYoonho;
import fr.farkas.Main.Characters.Chasseurs.ChaHaeIn.ChaHaeIn;
import fr.farkas.Main.Characters.Chasseurs.GotoRyuji.GotoRyuji;
import fr.farkas.Main.Characters.Chasseurs.LiuZhigang.LiuZhigang;
import fr.farkas.Main.Characters.Chasseurs.Selner.Selner;
import fr.farkas.Main.Characters.Chasseurs.WooChinjul.WooChinjul;
import fr.farkas.Main.Characters.Fragments.ChristopherReed.ChristopherReed;
import fr.farkas.Main.Characters.Monarques.MonarqueBetes.MonarqueBetes;
import fr.farkas.Main.Characters.Solos.Architecte.Architecte;
import fr.farkas.Main.Characters.SungJinWoo.SungJinWoo;
import fr.farkas.Main.General.Game;
import fr.farkas.Main.General.GeneralVariable;
import fr.farkas.Main.General.Tptogame;
import fr.farkas.Main.General.Configuration.BasicInventoryConfig;
import fr.farkas.Main.General.World.MapManager;
import fr.farkas.Main.General.World.teleport;

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
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {

        if (event.getLocation().getWorld().getEnvironment() == World.Environment.THE_END) {
        	if (event.getEntityType() == EntityType.ENDERMAN) {
                event.setCancelled(true);
            }else if (event.getEntityType() == EntityType.ENDER_DRAGON) {
				event.setCancelled(true);
			}
        }
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
				Block clickedBlock = event.getClickedBlock();
		        if (player.getWorld().getEnvironment() == World.Environment.THE_END && clickedBlock != null) {
		        	System.out.print("youhou on est dans l'end");
		            if (clickedBlock.getType() == Material.SKULL) {
		            	System.out.print(clickedBlock.getType());
		                teleport.teleportPlayerRandomly(player, Bukkit.getWorld("Game"), game.getPlugin());   
		            }
		        }
				characterListeners.CharacterClick(event, player);
		}
	}
	
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        if (player.getWorld().getEnvironment() == World.Environment.THE_END &&
                to.getY() < 0) {
                teleport.teleportPlayerRandomly(player, Bukkit.getWorld("Game"), game.getPlugin());
        }
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) {
        	player.sendMessage(GeneralVariable.MessagePrefix+"You are teleported in a portal");
        	Location loc = new Location(Bukkit.getWorld(GeneralVariable.DonjonWorld), 222, 44, 51);
        	player.teleport(loc);
        }
    }
	
	@EventHandler
	public void ondestroy(BlockBreakEvent blockBreak) {
		if (blockBreak.getBlock().getType() == Material.BEACON && blockBreak.getBlock().getWorld().getEnvironment() == World.Environment.THE_END) {
			blockBreak.setCancelled(true); 
			game.getPortal().eventEnd();
			GeneralVariable.PortailDefenseSucces = false;	
        }
		if (blockBreak.getPlayer().getWorld().getEnvironment() == World.Environment.THE_END) {
			blockBreak.setCancelled(true);
        }else {
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
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getWorld().getEnvironment() == World.Environment.THE_END) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        if (event.getPlayer().getWorld().getEnvironment() == World.Environment.THE_END) {
            if (event.getBucket() == Material.WATER_BUCKET || event.getBucket() == Material.LAVA_BUCKET) {
                event.setCancelled(true);
            }
        }
    }
	
	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent event) {
	    if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
	        Player attacker = (Player) event.getDamager();
	        Player victim = (Player) event.getEntity();
	        
	        if ((characterManager.getCharacterName(attacker).equals(Roles.SELNER))) {
	        	Selner selner = (Selner) characterManager.getCharacter(attacker);
	        	if (selner.getTouch()) {
	        		selner.touchAbility(attacker, victim.getDisplayName(),characterManager.getCharacter(victim),characterManager);
	        	}
	        }
	        if ((characterManager.getCharacterName(attacker).equals(Roles.BERU))) {
	            if (Math.random() < 0.2) { // 20% chance
	                PotionEffect poisonEffect = new PotionEffect(PotionEffectType.POISON, 2 * 40, 0);
	                victim.addPotionEffect(poisonEffect);
	            }
	        }
	        if ((characterManager.getCharacterName(attacker).equals(Roles.GOTORYUJI))) {
	            
	        	if (Math.random() < 0.3) { // 20% chance
	        		GotoRyuji gotoryuji = (GotoRyuji) characterManager.getCharacter(attacker);
	        		if (gotoryuji.GetEnchant() == true) {
	        			PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 2 * 40, 0);
		                victim.addPotionEffect(slow);
		                if (gotoryuji.getSolo() == true) {
		                	PotionEffect poisson = new PotionEffect(PotionEffectType.POISON, 2 * 40, 0);
			                victim.addPotionEffect(poisson);
		                }
	        		}
	                
	            }
	        }
	        if ((characterManager.getCharacterName(attacker).equals(Roles.BAEKYOONHO))) {
	        	BaekYoonho baekyoonho = (BaekYoonho) characterManager.getCharacter(attacker);
	        	if (baekyoonho.getEveil()) {
		            if (Math.random() < 0.3) { // 30% chance
		                PotionEffect sloweffect = new PotionEffect(PotionEffectType.SLOW, 3 * 40, 0);
		                victim.addPotionEffect(sloweffect);
		            }		
	        	}else {
	        		if ((characterManager.getCharacterName(victim).equals(Roles.MINBYUNG))) {
	        			baekyoonho.setEveil(true, attacker);
	        		}
	        	}

	        }
	        if ((characterManager.getCharacterName(attacker).equals(Roles.CHRISTOPHERREED))) {
	        	ChristopherReed christopherReed = (ChristopherReed) characterManager.getCharacter(attacker);
	            if (christopherReed.getFire()) { 
	                victim.setFireTicks(200);
	            }
	        }
	        if (characterManager.getCharacterName(attacker).equals(Roles.CHAHAEIN)) {
	            ChaHaeIn chaehaein = (ChaHaeIn) characterManager.getCharacter(attacker);
	            int randomChance = (int) (Math.random() * 100);
	            if (randomChance <= 30 && chaehaein.getEveil() == true) {
	            	attacker.sendMessage("§2Your Dance Power Work");
	                Location victimLocation = victim.getLocation();
	                double victimX = victimLocation.getX();
	                double victimY = victimLocation.getY();
	                double victimZ = victimLocation.getZ();
	                Location teleportLocation = new Location(victim.getWorld(), victimX, victimY, victimZ, victim.getLocation().getYaw(), victim.getLocation().getPitch());
	                teleportLocation.add(victim.getLocation().getDirection().multiply(-1).normalize().multiply(2));
	                attacker.teleport(teleportLocation);
	                attacker.teleport(attacker.getLocation().setDirection(victimLocation.toVector().subtract(attacker.getLocation().toVector()).normalize()));
	            }
	        }
	        // Insérez le code à exécuter lorsque le joueur attaque un autre joueur ici
	    }
	}

	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerInventory inventory = player.getInventory();
		
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(player.getInventory())) {
            boolean hasHelmet = hasArmorEquipped(inventory.getHelmet());
            boolean hasChestplate = hasArmorEquipped(inventory.getChestplate());
            boolean hasLeggings = hasArmorEquipped(inventory.getLeggings());
            boolean hasBoots = hasArmorEquipped(inventory.getBoots());
            if (!hasHelmet || !hasChestplate || !hasLeggings || !hasBoots) {
                if (characterManager.getCharacter(player) != null ) {
                    if (characterManager.getCharacterName(player) == Roles.SELNER) {
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
	}
    @EventHandler
    public void onArrowHit(EntityDamageByEntityEvent event) {
    	if (event.getEntity().getWorld().getEnvironment() == World.Environment.THE_END) {
            
        }else {
        	Entity attacker = event.getDamager();
            Entity target = event.getEntity();
            if (attacker instanceof Arrow && target instanceof LivingEntity) {
                Arrow arrow = (Arrow) attacker;
                LivingEntity victim = (LivingEntity) target;
                if (arrow.getShooter() instanceof LivingEntity) {
                    Player tireur = (Player) arrow.getShooter();
        	        if ((characterManager.getCharacterName(tireur).equals(Roles.BARUKA))) {
        	        	if (Math.random() <= 0.2) {
        		        	victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
        		        }
        	        	Location hitLocation = victim.getLocation();
        	            int victimX = hitLocation.getBlockX();
        	            int victimY = hitLocation.getBlockY() - 1;
        	            int victimZ = hitLocation.getBlockZ();
        	            World world = victim.getWorld();
        	            for (int x = victimX - 3; x <= victimX + 3; x++) {
        	                for (int z = victimZ - 3; z <= victimZ + 3; z++) {
        	                    Location blockLocation = new Location(world, x, victimY, z);
        	                    blockLocation.getBlock().setType(Material.ICE);
        	                }
        	            }
        	        }
                }
            }
        }  
    }
	
    private boolean hasArmorEquipped(ItemStack item) {
        return item != null && isArmor(item.getType());
    }

    private boolean isArmor(Material material) {
        return material == Material.IRON_HELMET || material == Material.IRON_CHESTPLATE
                || material == Material.IRON_LEGGINGS || material == Material.IRON_BOOTS
                || material == Material.DIAMOND_HELMET || material == Material.DIAMOND_CHESTPLATE
                || material == Material.DIAMOND_LEGGINGS || material == Material.DIAMOND_BOOTS;
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
    	Player player = event.getEntity();
    	Entity killer = player.getKiller();
    	if (killer instanceof Player) {
    	if ((characterManager.getCharacterName(player).equalsIgnoreCase(Roles.SUNGJINWOO)&& characterManager.getBlackHearth()== true)||(characterManager.getCharacterName(player).equalsIgnoreCase(Roles.ARCHITECT)&& characterManager.getBlackHearth() == true)) {
    		event.setKeepInventory(true);
    		characterManager.setBlackHearth(false);
    		Player attacker = (Player) killer;
    		if (characterManager.getCharacterName(player).equalsIgnoreCase(Roles.SUNGJINWOO)) {
    			attacker.setMaxHealth(30);
    			player.spigot().respawn();
    			Tptogame.TPtoGame(player);
    			Tptogame.TPtoGame(attacker);
    		}else {
    			player.spigot().respawn();
    			Tptogame.TPtoGame(player);
    			Tptogame.TPtoGame(attacker);
    			Architecte architect = (Architecte) characterManager.getCharacter(player);
    			architect.Igris(player);
    			SungJinWoo sungJinWoo = (SungJinWoo) characterManager.getCharacter(attacker);
    			sungJinWoo.MonarquesOmbres();
    		}
    		event.setKeepInventory(false);
    		return;
    	}else {
        
        player.setMaxHealth(20);
        player.spigot().respawn();
        player.setGameMode(GameMode.SPECTATOR);
        
        	Player attacker = (Player) killer;
        	player.teleport(attacker.getLocation());
        	if (characterManager.getCharacterName(attacker).equalsIgnoreCase(Roles.LIUZHIGANG)) {
        		LiuZhigang liuZhigang = (LiuZhigang) characterManager.getCharacter(attacker);
        		if(characterManager.getCharacter(player).getCamp() == 5 ){
        			liuZhigang.setEveil(true, player);
        		}
        	}
        	if (characterManager.getCharacterName(attacker).equalsIgnoreCase(Roles.GOTORYUJI)) {
        		GotoRyuji gotoryuji = (GotoRyuji) characterManager.getCharacter(attacker);
        		if(characterManager.getCharacter(player).getName() == "Beru" ){
        			gotoryuji.setSolo(attacker);
        		}
        	}
        	if (characterManager.getPlayerWithWooChinjul() != null) {
        		Player woochinjul = characterManager.getPlayerWithWooChinjul();
        		WooChinjul wooChinjul = (WooChinjul) characterManager.getCharacter(woochinjul);
        		wooChinjul.addArrowIndicator(woochinjul, attacker,game.getPlugin(),player);
        	}
        }
    	}else {
    		player.setMaxHealth(20);
            player.spigot().respawn();
            player.setGameMode(GameMode.SPECTATOR);
        }

        event.setDeathMessage("\n§b>============ §6Warning §b============<\n"+GeneralVariable.MessagePrefix+ "§6 " + player.getName() + "§4 is DEAD is role was §b" + characterManager.getCharacter(player).getName()+"\n§b>============ §6Warning §b============<\n");

        characterManager.getCharacter(player).isDying();
        characterManager.removeCharacter(player);
        
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
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
    }
    
    @EventHandler
    public void fall(EntityDamageEvent e) {
    	if (e.getCause() != DamageCause.FALL) {
    		return; // not fall damage.
    	}

    	if (!(e.getEntity() instanceof Player)) {
    		return; // not a player
    	}
    	e.setCancelled(true);
}
}