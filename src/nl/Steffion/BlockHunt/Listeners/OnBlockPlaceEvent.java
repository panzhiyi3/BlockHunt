package nl.Steffion.BlockHunt.Listeners;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.BlockHunt;
import nl.Steffion.BlockHunt.W;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class OnBlockPlaceEvent implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();

		for (Arena arena : W.arenaList) {
			if (arena.playersInArena.contains(player)) {
				event.setCancelled(true);
				return;
			}
		}

		ItemStack is = event.getItemInHand();
		if(is != null
				&& is.getItemMeta() != null
				&& is.getItemMeta().hasDisplayName()
				&& is.getItemMeta().getDisplayName().equals("UnlimitedArrowWorks"))
		{
			Block block = event.getBlockPlaced();
			if(block != null)
			{
				FixedMetadataValue meta = new FixedMetadataValue(BlockHunt.plugin, 0);
				block.setMetadata("UnlimitedArrowWorks", meta);
			}
		}
	}
}
