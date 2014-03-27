package nl.Steffion.BlockHunt.Listeners;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.Arena.ArenaState;
import nl.Steffion.BlockHunt.W;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

public class OnEntityShootBowEvent implements Listener {
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityShootBowEvent(EntityShootBowEvent event) {
		Entity entity = event.getProjectile();
		if(entity instanceof Arrow)
		{
			Arrow arrow = (Arrow) entity;
			LivingEntity le = arrow.getShooter();
			if(le instanceof Player)
			{
				Player player = (Player) le;
				Location loc = entity.getLocation();
				Vector dir = loc.getDirection();
				Vector vec = dir.multiply(new Vector(1,0,0));
				loc.add(vec);
				
				for (Arena arena : W.arenaList)
				{
					if (arena.playersInArena.contains(player)
							&& arena.gameState.equals(ArenaState.INGAME))
					{
						arrow.teleport(loc);
						event.setProjectile(arrow);
					}
				}
			}
		}
	}
}
