package nl.Steffion.BlockHunt;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import nl.Steffion.BlockHunt.DisguiseDelegate.DISGUISE_TYPE;
import nl.Steffion.BlockHunt.Managers.MessageM;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LibsDisguiseWrapper implements WrapperInterface
{
	public boolean CheckEnvironment(Server server)
	{
		if (server != null)
		{
			if (!server.getPluginManager()
					.isPluginEnabled("LibsDisguises"))
			{
				MessageM.broadcastFMessage(ConfigC.error_disguisesPluginNotInstalled);
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
	}

	public void SetupDisguiseCraft(Server server)
	{
		if(!CheckEnvironment(server))
		{
			return;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean IsDisguised(Player player)
	{
		return DisguiseAPI.isDisguised(player);
	}

	@SuppressWarnings("deprecation")
	public void Disguise(Player player, DISGUISE_TYPE type, ItemStack block)
	{
		if(player != null && block != null)
		{
			if(type == DISGUISE_TYPE.TYPE_FALLING_BLOCK)
			{
				MiscDisguise disguise = new MiscDisguise(
						DisguiseType.FALLING_BLOCK, block.getTypeId(),
						block.getDurability());
				DisguiseAPI.disguiseToAll(player, disguise);
			}
			else
			{
				MobDisguise mobDisguise = new MobDisguise(
						GetDisguiseType(block), true, true);
				DisguiseAPI.disguiseToAll(player, mobDisguise);
			}
		}
	}

	public void UnDisguise(Player player)
	{
		if (player != null)
		{
			DisguiseAPI.undisguiseToAll(player);
		}
	}
	
	public boolean IsBlockOrModCanDisguise(ItemStack item)
	{
		if(item != null)
		{
			if(item.getType().isBlock())
			{
				return true;
			}
			else if(IsMobDisguise(item))
			{
				return true;
			}
		}
		
		return false;
	}

	public boolean IsMobDisguise(ItemStack item)
	{
		if (item != null)
		{
			Material mat = item.getType();
			if (mat == Material.COOKED_CHICKEN)
			{
				return true;
			}
			else if (mat == Material.COOKED_BEEF)
			{
				return true;
			}
			else if (mat == Material.GRILLED_PORK)
			{
				return true;
			}
			else if (mat == Material.RAW_CHICKEN)
			{
				return true;
			}
			else if (mat == Material.RAW_BEEF)
			{
				return true;
			}
			else if (mat == Material.PORK)
			{
				return true;
			}
			else if(mat == Material.COOKED_FISH)
			{
				return true;
			}
			else if (mat == Material.RAW_FISH)
			{
				return true;
			}
		}

		return false;
	}

	public DisguiseType GetDisguiseType(ItemStack item)
	{
		if(item != null)
		{
			Material mat = item.getType();
			if (mat == Material.COOKED_CHICKEN
					|| mat == Material.RAW_CHICKEN)
			{
				return DisguiseType.CHICKEN;
			}
			else if (mat == Material.COOKED_BEEF
					|| mat == Material.RAW_BEEF)
			{
				return DisguiseType.COW;
			}
			else if (mat == Material.PORK
					|| mat == Material.GRILLED_PORK)
			{
				return DisguiseType.PIG;
			}
			else if(mat == Material.COOKED_FISH
					|| mat == Material.RAW_FISH)
			{
				return DisguiseType.OCELOT;
			}
		}

		return DisguiseType.GHAST;
	}
}
