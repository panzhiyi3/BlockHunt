package nl.Steffion.BlockHunt.Listeners;

import java.util.ListIterator;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.Arena.ArenaState;
import nl.Steffion.BlockHunt.ArenaHandler;
import nl.Steffion.BlockHunt.Common;
import nl.Steffion.BlockHunt.ConfigC;
import nl.Steffion.BlockHunt.InventoryHandler;
import nl.Steffion.BlockHunt.PermissionsC.Permissions;
import nl.Steffion.BlockHunt.SignsHandler;
import nl.Steffion.BlockHunt.SolidBlockHandler;
import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Managers.MessageM;
import nl.Steffion.BlockHunt.Managers.PermissionsM;
import nl.Steffion.BlockHunt.Serializables.LocationSerializable;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnPlayerInteractEvent implements Listener
{

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteractEvent(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();

		if (player != null && PermissionsM.hasPerm(player, Permissions.create, false))
		{
			processWandEvent(event, player, block);
		}

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			processWallSign(event, player);
		}

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK
				|| event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			processOpenBlock(event, player, block);
		}

		if (event.getAction() == Action.LEFT_CLICK_BLOCK
				|| event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			processClickedHider(event, player);
		}

		// Use the Nyan sugar
		if (player != null
				&& player.getInventory().getItemInHand() != null
				&& player.getInventory().getItemInHand().getType()
						.equals(Material.SUGAR))
		{
			processNyanSugar(player);
		}

		// Use fireworks
		if (player != null
				&& player.getInventory().getItemInHand() != null
				&& player.getInventory().getItemInHand().getType()
						.equals(Material.FIREWORK))
		{
			processFirework(event, player, block);
		}

		// If attacking innocent block
		if (player != null && event.getAction() == Action.LEFT_CLICK_BLOCK
				&& player.getItemInHand() != null)
		{
			processInnocentBlock(player, block);
		}

		for (Arena arena : W.arenaList)
		{
			processBlockChoose(event, arena, player);
		}

	}

	private void setFireworks(Arena arena)
	{
		for(LocationSerializable loc : arena.singularPoints)
		{
			World world = arena.pos1.getWorld();
			Firework fw = (Firework) world.spawnEntity(loc, EntityType.FIREWORK);
			FireworkMeta fm = fw.getFireworkMeta();
			fm.addEffect(FireworkEffect.builder()
					.flicker(true).trail(true)
					.with(FireworkEffect.Type.BALL_LARGE)
					.withColor(Color.YELLOW).build());
			fm.setPower(2);
			fw.setFireworkMeta(fm);
		}
	}

	private void processWandEvent(PlayerInteractEvent event, Player player, Block block)
	{
		ItemStack item = player.getItemInHand();
		if (item.getType() != Material.AIR)
		{
			if (item.getItemMeta().hasDisplayName())
			{
				ItemMeta im = item.getItemMeta();
				if (im.getDisplayName().equals(
						MessageM.replaceAll((String) W.config
								.get(ConfigC.wandName))))
				{
					Action action = event.getAction();
					if (event.hasBlock())
					{
						LocationSerializable location = new LocationSerializable(
								event.getClickedBlock().getLocation());
						if (action.equals(Action.LEFT_CLICK_BLOCK))
						{
							event.setCancelled(true);
							if (W.pos1.get(player) == null
									|| !W.pos1.get(player).equals(location))
							{
								MessageM.sendFMessage(
										player,
										ConfigC.normal_wandSetPosition,
										"number-1",
										"pos-%N(%A" + location.getBlockX()
												+ "%N, %A"
												+ location.getBlockY()
												+ "%N, %A"
												+ location.getBlockZ()
												+ "%N)",
										"x-" + location.getBlockX(), "y-"
												+ location.getBlockY(),
										"z-" + location.getBlockZ());
								W.pos1.put(player, location);
							}
						}
						else if (action.equals(Action.RIGHT_CLICK_BLOCK))
						{
							event.setCancelled(true);
							if (W.pos2.get(player) == null
									|| !W.pos2.get(player).equals(location))
							{
								MessageM.sendFMessage(
										player,
										ConfigC.normal_wandSetPosition,
										"number-2",
										"pos-%N(%A" + location.getBlockX()
												+ "%N, %A"
												+ location.getBlockY()
												+ "%N, %A"
												+ location.getBlockZ()
												+ "%N)",
										"x-" + location.getBlockX(), "y-"
												+ location.getBlockY(),
										"z-" + location.getBlockZ());
								W.pos2.put(player, location);
							}
						}
					}
				}
				else if (im.getDisplayName().equals(
						MessageM.replaceAll((String) W.config
								.get(ConfigC.singularWandName))))
				{
					Action action = event.getAction();
					if (action.equals(Action.LEFT_CLICK_BLOCK))
					{
						if (event.hasBlock())
						{
							if(block != null)
							{
								event.setCancelled(true);
								LocationSerializable location = new LocationSerializable(
										event.getClickedBlock().getLocation());
								if (W.singularPos.get(player) == null
										|| !W.singularPos.get(player).equals(location))
								{
									MessageM.sendFMessage(
											player,
											ConfigC.normal_singularWandSetPosition,
											"pos-%N(%A" + location.getBlockX()
													+ "%N, %A"
													+ location.getBlockY()
													+ "%N, %A"
													+ location.getBlockZ()
													+ "%N)",
											"x-" + location.getBlockX(), "y-"
													+ location.getBlockY(),
											"z-" + location.getBlockZ());
									W.singularPos.put(player, location);
								}
							}
						}
					}
				}
			}
		}
		if (item.getItemMeta() != null && item.getItemMeta().hasDisplayName())
		{
			ItemMeta im = item.getItemMeta();
			{
				if (im.getDisplayName().equals(
						MessageM.replaceAll((String) W.config
								.get(ConfigC.singularWandName))))
				{
					Action action = event.getAction();
					if (action.equals(Action.RIGHT_CLICK_BLOCK))
					{
						// Set fireworks on all singular points in the closest arena
						for (Arena arena : W.arenaList)
						{
							if (arena.pos1 != null
									&& arena.pos1
											.getWorld()
											.getName()
											.equals(player.getWorld().getName()))
							{
								boolean xPass = false;
								boolean zPass = false;

								// test x
								if(arena.pos1.getX() >= arena.pos2.getX())
								{
									if( player.getLocation().getX() <= arena.pos1.getX()
											&& player.getLocation().getX() >= arena.pos2.getX())
									{
										xPass = true;
									}
								}
								else
								{
									if( player.getLocation().getX() >= arena.pos1.getX()
											&& player.getLocation().getX() <= arena.pos2.getX())
									{
										xPass = true;
									}
								}

								// test z
								if(arena.pos1.getZ() >= arena.pos2.getZ())
								{
									if( player.getLocation().getZ() <= arena.pos1.getZ()
											&& player.getLocation().getZ() >= arena.pos2.getZ())
									{
										zPass = true;
									}
								}
								else
								{
									if( player.getLocation().getZ() >= arena.pos1.getZ()
											&& player.getLocation().getZ() <= arena.pos2.getZ())
									{
										zPass = true;
									}
								}
								
								if(xPass && zPass)
								{
									setFireworks(arena);
									break;
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void processWallSign(PlayerInteractEvent event, Player player)
	{
		if (event.getClickedBlock() != null)
		{
			if (event.getClickedBlock().getType()
					.equals(Material.SIGN_POST)
					|| event.getClickedBlock().getType()
							.equals(Material.WALL_SIGN))
			{
				if (SignsHandler.isSign(new LocationSerializable(event
						.getClickedBlock().getLocation())))
				{
					Sign sign = (Sign) event.getClickedBlock().getState();
					if (sign.getLine(1) != null)
					{
						if (sign.getLine(1)
								.equals(MessageM
										.replaceAll(W.config
												.getFile()
												.getStringList(
														ConfigC.sign_LEAVE.location)
												.get(1))))
						{
							if (PermissionsM.hasPerm(player,
									Permissions.joinsign, true))
							{
								ArenaHandler.playerLeaveArena(player, true,
										true);
							}
						}
						else if (sign.getLine(1).equals(
								MessageM.replaceAll(W.config
										.getFile()
										.getStringList(
												ConfigC.sign_SHOP.location)
										.get(1))))
						{
							if (PermissionsM.hasPerm(player,
									Permissions.shop, true))
							{
								InventoryHandler.openShop(player);
							}
						}
						else
						{
							for (Arena arena : W.arenaList)
							{
								if (sign.getLines()[1]
										.contains(arena.arenaName))
								{
									if (PermissionsM.hasPerm(player,
											Permissions.joinsign, true))
									{
										ArenaHandler.playerJoinArena(
												player, arena.arenaName);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void processOpenBlock(PlayerInteractEvent event, Player player, Block block)
	{
		Material mat = block.getType();
		if (mat != Material.AIR)
		{
			if(mat.equals(Material.CHEST))
			{
				boolean found = false;
				for (Arena arena : W.arenaList)
				{
					if (arena.playersInArena.contains(player))
					{
						event.setCancelled(true);
						found = true;
						break;
					}
				}

				if(block.hasMetadata("UnlimitedArrowWorks"))
				{
					if(!found)
					{
						if(block.getState() instanceof Chest)
						{
							Chest chest = (Chest) block.getState();
							fillUnlimitedArrowWorks(chest);
						}
					}
				}
			}
			else if (mat.equals(Material.ENCHANTMENT_TABLE)
					|| mat.equals(Material.WORKBENCH)
					|| mat.equals(Material.FURNACE)
					|| mat.equals(Material.ANVIL)
					|| mat.equals(Material.ENDER_CHEST)
					|| mat.equals(Material.JUKEBOX)
					|| block.getFace(block).equals(Material.FIRE))
			{
				for (Arena arena : W.arenaList)
				{
					if (arena.playersInArena.contains(player))
					{
						event.setCancelled(true);
						break;
					}
				}
			}
		}
	}

	private void fillUnlimitedArrowWorks(Chest chest)
	{
		int size = chest.getInventory().getSize();
		int maxSize = chest.getInventory().getMaxStackSize();
		if(size < maxSize)
		{
			int maxBowNum = (int) ((float) maxSize * 0.3);
			int maxArrowNum = maxSize - maxBowNum;
			int bowNum = 0;
			int arrowNum = 0;

			ListIterator<ItemStack> itr = chest.getInventory().iterator();
			for(; itr.hasNext();)
			{
				ItemStack item = itr.next();
				if(item != null)
				{
					if(item.getType() == Material.ARROW)
					{
						arrowNum++;
					}
					else if(item.getType() == Material.BOW)
					{
						bowNum++;
					}
				}
			}
			for(; arrowNum < maxArrowNum; arrowNum++)
			{
				chest.getInventory().addItem(new ItemStack(Material.ARROW, 64));
			}
			for(; bowNum < maxBowNum; bowNum++)
			{
				chest.getInventory().addItem(new ItemStack(Material.BOW, 1));
			}
		}
	}

	private void processClickedHider(PlayerInteractEvent event, Player player)
	{
		for (Arena arena : W.arenaList)
		{
			if (arena.seekers.contains(player))
			{
				for (Player pl : arena.playersInArena)
				{
					if (W.hiddenLoc.get(pl) != null)
					{
						Block pLoc = event.getClickedBlock();
						Block moveLocBlock = W.hiddenLoc.get(pl).getBlock();
						if (moveLocBlock.getX() == pLoc.getX()
								&& moveLocBlock.getY() == pLoc.getY()
								&& moveLocBlock.getZ() == pLoc.getZ())
						{
							W.moveLoc.put(pl, new Location(pl.getWorld(),
									0, 0, 0));
							pl.getWorld().playSound(player.getLocation(),
									Sound.HURT_FLESH, 1, 1);
							SolidBlockHandler.makePlayerUnsolid(pl);
						}
					}
				}
			}
		}
	}

	private void processNyanSugar(Player player)
	{
		for (Arena arena : W.arenaList)
		{
			if (arena.playersInArena.contains(player)
					&& arena.gameState.equals(ArenaState.INGAME))
			{
				int cd = arena.nyanCooldown.get(player);
				if (cd != 0)
				{
					MessageM.sendMessage(player, "You can use Nyan in "
							+ cd + " second(s)");
					continue;
				}
				arena.nyanCooldown.put(player,
						(Integer) W.config.get(ConfigC.nyanCooldown));

				player.getWorld().playSound(player.getLocation(),
						Sound.CAT_MEOW, 1, 1);
				PotionEffect pe = new PotionEffect(
						PotionEffectType.REGENERATION, 120, 1);
				player.addPotionEffect(pe);
			}
		}
	}
	
	private void processFirework(PlayerInteractEvent event, Player player, Block block)
	{
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK
				&& block.getType() != Material.AIR)
		{
			for (Arena arena : W.arenaList)
			{
				if (arena.playersInArena.contains(player)
						&& arena.gameState.equals(ArenaState.INGAME))
				{
					PotionEffect pe = new PotionEffect(
							PotionEffectType.SPEED, 60, 1);
					player.addPotionEffect(pe);
				}
			}
		}
	}
	
	private void processInnocentBlock(Player player, Block block)
	{
		if (player.getItemInHand().getType() == Common.SeekerWeapon)
		{
			for (Arena arena : W.arenaList)
			{
				if (arena.playersInArena.contains(player)
						&& arena.seekers.contains(player))
				{
					if (block instanceof Player)
					{
						Player hider = (Player) block;
						if (arena.playersInArena.contains(hider)
								&& !arena.seekers.contains(hider))
						{
							float exp = player.getExp();
							exp -= Common.SWORD_HITHIDER_COST_EXP;
							if (exp <= 0.0f)
							{
								exp = 0.0f;

								player.damage((double) Common.WRONG_ATTACK_DAMAGE);
							}
							player.setExp(exp);

							break;
						}
					}
					else if (block.getType() != Material.AIR)
					{
						float exp = player.getExp();
						exp -= Common.SWORD_ONEHIT_COST_EXP;
						if (exp <= 0.0f)
						{
							exp = 0.0f;

							player.damage((double) Common.WRONG_ATTACK_DAMAGE);
						}
						player.setExp(exp);
					}
				}
			}
		}
	}

	private void processBlockChoose(PlayerInteractEvent event, Arena arena, Player player)
	{
		if (arena.playersInArena.contains(player)
				&& (arena.gameState.equals(ArenaState.WAITING) || arena.gameState
						.equals(ArenaState.STARTING)))
		{
			event.setCancelled(true);
			ItemStack item = player.getInventory().getItemInHand();
			if (item.getType() != Material.AIR)
			{
				if (item.getItemMeta().getDisplayName() != null)
				{
					if (item.getItemMeta()
							.getDisplayName()
							.equals(MessageM.replaceAll((String) W.config
									.get(ConfigC.shop_blockChooserv1Name))))
					{
						Inventory blockChooser = Bukkit
								.createInventory(
										null,
										36,
										MessageM.replaceAll("\u00A7r"
												+ W.config
														.get(ConfigC.shop_blockChooserv1Name)));
						if (arena.disguiseBlocks != null)
						{
							for (int i = arena.disguiseBlocks.size(); i > 0; i = i - 1)
							{
								blockChooser.setItem(i - 1,
										arena.disguiseBlocks.get(i - 1));
							}
						}

						player.openInventory(blockChooser);
					}

					if (item.getItemMeta()
							.getDisplayName()
							.equals(MessageM.replaceAll((String) W.config
									.get(ConfigC.shop_BlockHuntPassv2Name))))
					{
						Inventory BlockHuntPass = Bukkit
								.createInventory(
										null,
										9,
										MessageM.replaceAll("\u00A7r"
												+ W.config
														.get(ConfigC.shop_BlockHuntPassv2Name)));
						ItemStack BlockHuntPassSEEKER = new ItemStack(
								Material.WOOL, 1, (short) 11);
						ItemMeta BlockHuntPassIM = BlockHuntPassSEEKER
								.getItemMeta();
						BlockHuntPassIM.setDisplayName(MessageM
								.replaceAll("&eSEEKER"));
						BlockHuntPassSEEKER.setItemMeta(BlockHuntPassIM);
						BlockHuntPass.setItem(1, BlockHuntPassSEEKER);

						ItemStack BlockHuntPassHIDER = new ItemStack(
								Material.WOOL, 1, (short) 14);
						BlockHuntPassIM.setDisplayName(MessageM
								.replaceAll("&eHIDER"));
						BlockHuntPassHIDER.setItemMeta(BlockHuntPassIM);
						BlockHuntPass.setItem(7, BlockHuntPassHIDER);

						player.openInventory(BlockHuntPass);
					}
				}
			}
		}
	}
}
