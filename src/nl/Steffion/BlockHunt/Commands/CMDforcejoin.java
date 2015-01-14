package nl.Steffion.BlockHunt.Commands;

import java.util.ArrayList;

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
					Arena arena = getRandArena();
					if(arena != null)
					{
						MessageM.broadcastMessage("֪ͨ:" + player.getName()
								+ "��(ǿ)��(��)��λ��ҽ����èè����:"
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
					for(Arena arena : W.arenaList)
					{
						if(arena.arenaName.equals(args[1]))
						{
							MessageM.broadcastMessage("֪ͨ: " + player.getName()
									+ " ��(ǿ)��(��)��λ��ҽ����èè����:"
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
	
	private Arena getRandArena()
	{
		long start = System.currentTimeMillis();
		ArrayList<Arena> workList = W.arenaList;
		ArrayList<Arena> nextList = new ArrayList<Arena>();
		if(workList.size() == 0)
		{
			MessageM.broadcastMessage("����:�����������ڶ�èè����,����ϵ����Ա��������!");
			return null;
		}
		while(workList.size() > 1)
		{
			if(System.currentTimeMillis() - start >= 500) //if costs more than 500ms, get random directly
			{
				int size = W.arenaList.size();
				int rand = W.random.nextInt(size);
				return W.arenaList.get(rand);
			}
			nextList.clear();
			for(Arena arena : workList)
			{
				if(W.random.nextFloat() > 0.5)
				{
					nextList.add(arena); //�Ҳ���Java���Ҳ�֪���ڱ����������C++һ����ȫ��ɾ����������
				}
				if(nextList.isEmpty())
				{
					nextList.add( workList.get( W.random.nextInt( workList.size() ) ) );
				}
			}
			workList = nextList;
		}
		if(workList.size() == 1)
		{
			return workList.get(0);
		}
		return null;
	}
}
