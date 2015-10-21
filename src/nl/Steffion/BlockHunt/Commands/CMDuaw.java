package nl.Steffion.BlockHunt.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CMDuaw extends DefaultCMD {

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args)
	{
		ItemStack uaw = new ItemStack(
				Material.CHEST, 1);
		ItemMeta meta = uaw.getItemMeta();
		meta.setDisplayName("UnlimitedArrowWorks");
		uaw.setItemMeta(meta);
		player.getInventory().addItem(uaw);
		return true;
	}
}
