package nl.Steffion.BlockHunt.Commands;

import nl.Steffion.BlockHunt.DisguiseDelegate;
import nl.Steffion.BlockHunt.W;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDname extends DefaultCMD
{
	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args)
	{
		if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("off"))
			{
				W.NameOffMode = true;
//				for (Player pl : Bukkit
//						.getOnlinePlayers())
//				{
//					for (Player plTgt : Bukkit
//							.getOnlinePlayers())
//					{
//						if(!plTgt.equals(pl))
//						{
//							plTgt.hidePlayer(pl);
//						}
//					}
//					DisguiseDelegate.GetSingleton().DisguiseAsPlayer(pl);
//				}
			}
			else if(args[1].equalsIgnoreCase("on"))
			{
				W.NameOffMode = false;
				for (Player pl : Bukkit
						.getOnlinePlayers())
				{
//					for (Player plTgt : Bukkit
//							.getOnlinePlayers())
//					{
//						if(!plTgt.equals(pl))
//						{
//							plTgt.showPlayer(pl);
//						}
//					}
					DisguiseDelegate.GetSingleton().UnDisguise(pl);
				}
			}
		}
		return true;
	}
}
