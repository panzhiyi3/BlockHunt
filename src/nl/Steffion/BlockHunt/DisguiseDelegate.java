package nl.Steffion.BlockHunt;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DisguiseDelegate
{
	static private DisguiseDelegate m_instance = null;

	WrapperInterface m_wrapper = null;
	
	final DELEGATE_WRAPPER m_wrapperType = DELEGATE_WRAPPER.WRAPPER_LIBSDISGUISE;

	public enum DELEGATE_WRAPPER
	{
		WRAPPER_LIBSDISGUISE,
		WRAPPER_DISGUISECRAFT,
	}

	public enum DISGUISE_TYPE
	{
		TYPE_FALLING_BLOCK,
		TYPE_MOB,
	}

	static public DisguiseDelegate GetSingleton()
	{
		if(m_instance == null)
		{
			m_instance = new DisguiseDelegate();
		}
		return m_instance;
	}

	public void SetupDisguiseCraft(Server server)
	{
		switch (m_wrapperType)
		{
		case WRAPPER_LIBSDISGUISE:
		{
			m_wrapper = new LibsDisguiseWrapper();
			break;
		}
		case WRAPPER_DISGUISECRAFT:
		{
			m_wrapper = new DisguiseCraftWrapper();
			break;
		}
		}
		
		m_wrapper.SetupDisguiseCraft(server);
	}

	public boolean IsDisguised(Player player)
	{
		return m_wrapper.IsDisguised(player);
	}

	public void Disguise(Player player, DISGUISE_TYPE type, ItemStack block)
	{
		m_wrapper.Disguise(player, type, block);
	}

	public void UnDisguise(Player player)
	{
		m_wrapper.UnDisguise(player);
	}
	
	public boolean IsBlockOrModCanDisguise(ItemStack item)
	{
		return m_wrapper.IsBlockOrModCanDisguise(item);
	}
	
	public boolean IsMobDisguise(ItemStack item)
	{
		return m_wrapper.IsMobDisguise(item);
	}
}
