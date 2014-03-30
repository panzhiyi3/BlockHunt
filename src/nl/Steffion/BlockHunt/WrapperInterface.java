package nl.Steffion.BlockHunt;

import nl.Steffion.BlockHunt.DisguiseDelegate.DISGUISE_TYPE;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface WrapperInterface
{
	public void SetupDisguiseCraft(Server server);
	
	public boolean IsDisguised(Player player);
	
	public void Disguise(Player player, DISGUISE_TYPE type, ItemStack block);
	
	public void UnDisguise(Player player);
	
	public boolean IsBlockOrModCanDisguise(ItemStack item);
	
	public boolean IsMobDisguise(ItemStack item);
}
