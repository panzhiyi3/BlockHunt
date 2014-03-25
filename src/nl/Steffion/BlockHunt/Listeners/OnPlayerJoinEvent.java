package nl.Steffion.BlockHunt.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.permissions.PermissionDefault;

public class OnPlayerJoinEvent implements Listener
{

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoinEvent(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if (player != null && !player.hasPermission(PermissionDefault.OP.toString()))
		{
			if(!player.getInventory().contains(Material.WRITTEN_BOOK))
			{
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
				BookMeta bm = (BookMeta) book.getItemMeta();
				bm.setTitle("规则");
				String p1 = "1.Seeker会自动积攒经验条,使用武器攻击时正确命中和未命中Seeker都回消耗经验条,若经验条为空,再次攻击时会被扣血";
				String p2 = "2.Hider静止不动5秒后会变成物品兰最右侧物品所示的方块";
				String p3 = "3.Hider可以使用糖来恢复体力,同时会发出猫叫吸引Hider注意";
				String p4 = "4.Hider可以发射烟花来嘲讽Hider并且获得加速跑BUFF";
				bm.addPage(p1, p2, p3, p4);
				book.setItemMeta(bm);
				player.getInventory().addItem(book);
			}
		}
	}
}
