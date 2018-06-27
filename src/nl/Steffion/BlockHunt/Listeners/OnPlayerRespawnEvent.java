package nl.Steffion.BlockHunt.Listeners;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.W;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawnEvent implements Listener
{
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerRespawnEvent(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		if(player != null && W.BattleMode)
		{
			boolean notInBM = false;

			for (Arena arena : W.arenaList)
			{
				if (arena.playersInArena.contains(player))
				{
					notInBM = true;
					break;
				}
			}
			
			if(!notInBM)
			{
				W.playerTobeRespawn.add(player);
			}
		}
	}
}
