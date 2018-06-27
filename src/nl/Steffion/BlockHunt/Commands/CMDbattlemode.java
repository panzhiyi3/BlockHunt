package nl.Steffion.BlockHunt.Commands;

import nl.Steffion.BlockHunt.DisguiseDelegate;
import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Serializables.LocationSerializable;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDbattlemode extends DefaultCMD
{
	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args)
	{
		if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("on"))
			{
				W.BattleMode = true;
			}
			else if(args[1].equalsIgnoreCase("off"))
			{
				W.BattleMode = false;
				for (Player pl : Bukkit
						.getOnlinePlayers())
				{
					DisguiseDelegate.GetSingleton().UnDisguise(pl);
				}
			}
			else if(args[1].equalsIgnoreCase("setspawn"))
			{
				LocationSerializable location = new LocationSerializable(
						player.getLocation());
				W.battleField.spawnPoints.add(location);
				
				W.battlefieldCfg.getFile().set("battlefield", W.battleField);
				W.battlefieldCfg.save();
			}
			else if(args[1].equalsIgnoreCase("clearspawn"))
			{
				W.battleField.spawnPoints.clear();
				
				W.battlefieldCfg.getFile().set("battlefield", W.battleField);
				W.battlefieldCfg.save();
			}
		}
		return true;
	}
}
