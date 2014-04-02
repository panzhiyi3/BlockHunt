package nl.Steffion.BlockHunt.Listeners;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.Arena.ArenaState;
import nl.Steffion.BlockHunt.ArenaHandler;
import nl.Steffion.BlockHunt.Common;
import nl.Steffion.BlockHunt.ConfigC;
import nl.Steffion.BlockHunt.DisguiseDelegate;
import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Managers.MessageM;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

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
		
		Arrow arrow = null;
		if (event.getCause() == DamageCause.PROJECTILE
				&& event.getDamager() instanceof Arrow)
		{
			arrow = (Arrow) event.getDamager();
			if(arrow.getShooter() instanceof Player)
			{
				damager = (Player) arrow.getShooter();
			}
		}

		// Attacking innocence animals
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

							damager.damage((double) Common.WRONG_ATTACK_ANIMAL_DAMAGE);
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
								&& arena.seekers.contains(damager))
						{
							event.setCancelled(true);
							return;
						}

						// hider attacks hider
						if (arena.playersInArena.contains(player)
								&& arena.playersInArena.contains(damager)
								&& !arena.seekers.contains(damager)
								&& !arena.seekers.contains(player))
						{
							event.setCancelled(true);
							return;
						}


						player.getWorld().playSound(player.getLocation(),
								Sound.HURT_FLESH, 1, 1);

						double damageReduc = Common.getDamageReduced(player);
						if (event.getDamage() * (1.0 - damageReduc) >= player.getHealth())
						{
							player.setHealth(20);
							event.setCancelled(true);

							// DisguiseAPI.undisguiseToAll(player);
							DisguiseDelegate.GetSingleton().UnDisguise(player);
							W.pBlock.remove(player);

							// hider die
							if (!arena.seekers.contains(player))
							{
								if (W.shop.getFile().get(
										damager.getName() + ".tokens") == null)
								{
									W.shop.getFile().set(
											damager.getName() + ".tokens", 0);
									W.shop.save();
								}
								int damagerTokens = W.shop.getFile().getInt(
										damager.getName() + ".tokens");
								W.shop.getFile().set(
										damager.getName() + ".tokens",
										damagerTokens + arena.killTokens);
								W.shop.save();

								MessageM.sendFMessage(damager,
										ConfigC.normal_addedToken, "amount-"
												+ arena.killTokens);

								if (W.shop.getFile().get(
										player.getName() + ".tokens") == null)
								{
									W.shop.getFile().set(
											player.getName() + ".tokens", 0);
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
										ConfigC.normal_addedToken, "amount-"
												+ (int) addingTokens);

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
							else
							// seeker die
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
								// DisguiseAPI.undisguiseToAll(player);
								DisguiseDelegate.GetSingleton().UnDisguise(
										player);
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
