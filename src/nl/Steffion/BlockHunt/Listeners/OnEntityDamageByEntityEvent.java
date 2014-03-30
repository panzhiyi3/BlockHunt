package nl.Steffion.BlockHunt.Listeners;

import me.libraryaddict.disguise.DisguiseAPI;
import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.Arena.ArenaState;
import nl.Steffion.BlockHunt.ArenaHandler;
import nl.Steffion.BlockHunt.Common;
import nl.Steffion.BlockHunt.ConfigC;
import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Managers.MessageM;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnEntityDamageByEntityEvent implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event)
	{
		Player player = null;
		if (event.getEntity() instanceof Player)
		{
			player = (Player) event.getEntity();
		}

		Player damager = null;
		if (event.getDamager() instanceof Player)
		{
			damager = (Player) event.getDamager();
		}
		
		Animals animals = null;
		if (event.getEntity() instanceof Animals)
		{
			animals = (Animals) event.getEntity();
			event.setCancelled(true);
			
			if (damager != null)
			{
				for (Arena arena : W.arenaList)
				{
					if (arena.playersInArena.contains(damager)
							&& arena.seekers.contains(damager))
					{
						float exp = damager.getExp();
						exp -= Common.SWORD_HITHIDER_COST_EXP;
						if (exp <= 0.0f)
						{
							exp = 0.0f;

							// Attack innocence animals, get twice of damage
							damager.damage((double) 2 * Common.WRONG_ATTACK_DAMAGE);
						}
						damager.setExp(exp);

						break;
					}
				}
			}
		}

		if (animals != null)
		{
			for (Arena arena : W.arenaList)
			{
				if (arena.playersInArena.contains(damager))
				{
					if (arena.gameState == ArenaState.INGAME)
					{
						event.setCancelled(true);
					}
				}
			}
		}

		if (player != null)
		{
			for (Arena arena : W.arenaList)
			{
				if (arena.playersInArena.contains(player))
				{
					if (arena.gameState == ArenaState.WAITING
							|| arena.gameState == ArenaState.STARTING)
					{
						event.setCancelled(true);
					}
					else
					{
						// seeker attacks seeker
						if (arena.seekers.contains(player)
								&& arena.seekers.contains(event.getDamager()))
						{
							event.setCancelled(true);
						}
						// hider attacks hider
						else if (arena.playersInArena.contains(player)
								&& arena.playersInArena.contains(event
										.getDamager())
								&& !arena.seekers.contains(event.getDamager())
								&& !arena.seekers.contains(player))
						{
							event.setCancelled(true);
						}
						else
						{
							player.getWorld().playSound(player.getLocation(),
									Sound.HURT_FLESH, 1, 1);

							if (event.getDamage() >= player.getHealth())
							{
								player.setHealth(20);
								event.setCancelled(true);

								DisguiseAPI.undisguiseToAll(player);
								W.pBlock.remove(player);

								// hider die
								if (!arena.seekers.contains(player))
								{
									if (W.shop.getFile().get(
											damager.getName() + ".tokens") == null)
									{
										W.shop.getFile().set(
												damager.getName() + ".tokens",
												0);
										W.shop.save();
									}
									int damagerTokens = W.shop.getFile()
											.getInt(damager.getName()
													+ ".tokens");
									W.shop.getFile().set(
											damager.getName() + ".tokens",
											damagerTokens + arena.killTokens);
									W.shop.save();

									MessageM.sendFMessage(damager,
											ConfigC.normal_addedToken,
											"amount-" + arena.killTokens);

									if (W.shop.getFile().get(
											player.getName() + ".tokens") == null)
									{
										W.shop.getFile()
												.set(player.getName()
														+ ".tokens", 0);
										W.shop.save();
									}
									int playerTokens = W.shop.getFile().getInt(
											player.getName() + ".tokens");
									float addingTokens = ((float) arena.hidersTokenWin - (((float) arena.timer / (float) arena.gameTime) * (float) arena.hidersTokenWin));
									W.shop.getFile().set(
											player.getName() + ".tokens",
											playerTokens + (int) addingTokens);
									W.shop.save();

									MessageM.sendFMessage(player,
											ConfigC.normal_addedToken,
											"amount-" + (int) addingTokens);

									arena.seekers.add(player);
									player.setFoodLevel(20);
									ArenaHandler
											.sendFMessage(
													arena,
													ConfigC.normal_ingameHiderDied,
													"playername-"
															+ player.getName(),
													"killername-"
															+ damager.getName(),
													"left-"
															+ (arena.playersInArena
																	.size() - arena.seekers
																	.size()));
								}
								else // seeker die
								{
									ArenaHandler.sendFMessage(arena,
											ConfigC.normal_ingameSeekerDied,
											"playername-" + player.getName(),
											"killername-" + damager.getName(),
											"secs-" + arena.waitingTimeSeeker);
								}

								player.getInventory().clear();
								player.updateInventory();

								if (arena.seekers.size() >= arena.playersInArena
										.size())
								{
									ArenaHandler.seekersWin(arena);
								}
								else
								{
									DisguiseAPI.undisguiseToAll(player);
									W.seekertime.put(player,
											arena.waitingTimeSeeker);
									player.teleport(arena.seekersWarp);
									player.setGameMode(GameMode.SURVIVAL);
								}
							}
						}
					}
				}
			}
		}
	}
}
