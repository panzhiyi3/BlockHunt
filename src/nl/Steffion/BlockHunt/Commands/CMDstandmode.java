package nl.Steffion.BlockHunt.Commands;

import nl.Steffion.BlockHunt.W;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDstandmode extends DefaultCMD
{
	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args)
	{
		if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("on"))
			{
				W.TheStandMode = true;
			}
			else if(args[1].equalsIgnoreCase("off"))
			{
				W.TheStandMode = false;
			}
		}
		return true;
	}
}
