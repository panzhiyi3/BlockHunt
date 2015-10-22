package nl.Steffion.BlockHunt.Commands;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.W;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDtp extends DefaultCMD
{
	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args)
	{
		if(args.length == 2)
		{
			for (Arena arena : W.arenaList)
			{
				if(arena.arenaName.equals(args[1]))
				{
					player.teleport(arena.lobbyWarp);
					break;
				}
			}
		}
		return true;
	}
}
