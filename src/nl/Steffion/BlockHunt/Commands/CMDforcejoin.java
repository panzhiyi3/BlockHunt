package nl.Steffion.BlockHunt.Commands;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.ArenaHandler;
import nl.Steffion.BlockHunt.BlockHunt;
import nl.Steffion.BlockHunt.ConfigC;
import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Managers.MessageM;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDforcejoin extends DefaultCMD
{

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args)
	{
		if (player != null)
		{
			if (args.length <= 1)
			{
				MessageM.sendFMessage(player, ConfigC.error_notEnoughArguments,
						"syntax-" + BlockHunt.CMDforcejoin.usage);
			}
			else
			{
				if(args[1].equals("random"))
				{
					int size = W.arenaList.size();
					int rand = W.random.nextInt(size);
					Arena arena = W.arenaList.get(rand);
					if(arena != null)
					{
						MessageM.broadcastMessage("OP:" + player.getName()
								+ "is forcing all players to join arena:"
								+ arena.arenaName);
						Player[] players = org.bukkit.Bukkit.getOnlinePlayers();
						for(Player pl : players)
						{
							ArenaHandler.playerJoinArena(pl, arena.arenaName);
						}
					}
				}
				else
				{
					for( Arena arena : W.arenaList)
					{
						if(arena.arenaName.equals(args[1]))
						{
							MessageM.broadcastMessage("OP:" + player.getName()
									+ "is forcing all players to join arena:"
									+ arena.arenaName);
							Player[] players = org.bukkit.Bukkit.getOnlinePlayers();
							for(Player pl : players)
							{
								ArenaHandler.playerJoinArena(pl, arena.arenaName);
							}
							break;
						}
					}
				}
			}
		}
		else
		{
			MessageM.sendFMessage(player, ConfigC.error_onlyIngame);
		}
		return true;
	}
}
