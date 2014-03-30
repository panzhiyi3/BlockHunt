package nl.Steffion.BlockHunt;

import nl.Steffion.BlockHunt.DisguiseDelegate.DISGUISE_TYPE;
import nl.Steffion.BlockHunt.Managers.MessageM;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pgDev.bukkit.DisguiseCraft.DisguiseCraft;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;
import pgDev.bukkit.DisguiseCraft.disguise.Disguise;
import pgDev.bukkit.DisguiseCraft.disguise.DisguiseType;

public class DisguiseCraftWrapper implements WrapperInterface
{
	protected DisguiseCraftAPI m_dcAPI = null;

	public boolean CheckEnvironment(Server server)
	{
		if (server != null)
		{
			if (!server.getPluginManager()
					.isPluginEnabled("DisguiseCraft"))
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
		if (!CheckEnvironment(server))
		{
			return;
		}
		m_dcAPI = DisguiseCraft.getAPI();
	}

	public boolean IsDisguised(Player player)
	{
		if(m_dcAPI != null)
		{
			return m_dcAPI.isDisguised(player);
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public void Disguise(Player player, DISGUISE_TYPE type, ItemStack block)
	{
		if(m_dcAPI == null)
		{
			return;
		}

		if(player != null && block != null)
		{
			Disguise dis = null;
			if (type == DISGUISE_TYPE.TYPE_FALLING_BLOCK)
			{
				 String data = "blockID:" + block.getTypeId();
				 dis = new Disguise(m_dcAPI.newEntityID(), data,
				 DisguiseType.FallingBlock);
			}
			else
			{
				 dis = new Disguise(m_dcAPI.newEntityID(),
				 GetDisguiseType(block));
			}

			m_dcAPI.disguisePlayer(player, dis);
		}
	}

	public void UnDisguise(Player player)
	{
		if (player != null && m_dcAPI != null)
		{
			m_dcAPI.undisguisePlayer(player);
		}
	}

	public boolean IsBlockOrModCanDisguise(ItemStack item)
	{
		if (item != null)
		{
			if (item.getType().isBlock())
			{
				return true;
			}
			else if (IsMobDisguise(item))
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
			else if (mat == Material.COOKED_FISH)
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
		if (item != null)
		{
			Material mat = item.getType();
			if (mat == Material.COOKED_CHICKEN || mat == Material.RAW_CHICKEN)
			{
				return DisguiseType.Chicken;
			}
			else if (mat == Material.COOKED_BEEF || mat == Material.RAW_BEEF)
			{
				return DisguiseType.Cow;
			}
			else if (mat == Material.PORK || mat == Material.GRILLED_PORK)
			{
				return DisguiseType.Pig;
			}
			else if (mat == Material.COOKED_FISH || mat == Material.RAW_FISH)
			{
				return DisguiseType.Ocelot;
			}
		}

		return DisguiseType.Ghast;
	}
}
