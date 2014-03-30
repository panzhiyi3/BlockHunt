package nl.Steffion.BlockHunt.Listeners;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.W;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnEntityDeathEvent implements Listener
{
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDeathEvent(EntityDeathEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			if(player != null)
			{
				for (Arena arena : W.arenaList)
				{
					if (arena.playersInArena.contains(player)
							&& arena.seekers.contains(player))
					{
						event.getDrops().clear();
						event.setDroppedExp(0);
					}
				}
			}
		}
	}
}
