package nl.Steffion.BlockHunt.Listeners;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Managers.MessageM;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnAsyncPlayerChatEvent implements Listener
{
	@EventHandler(priority = EventPriority.NORMAL)
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event)
	{
		//����Ч��̫�ȡ��
//		Player pl = event.getPlayer();
//		if(pl != null)
//		{
//			for (Arena arena : W.arenaList)
//			{
//				if (arena.gameState == Arena.ArenaState.INGAME
//						&& arena.playersInArena.contains(pl)
//						&& !arena.seekers.contains(pl))
//				{
//					String msg = event.getMessage();
//					if(msg.contains("���޵�")
//							|| msg.contains("���Ѿ��޵�"))
//					{
//						MessageM.broadcastMessage(pl.getName() + " �������Ѿ��޵�! ��èè֮�����ĸ��ߴ��," + getPlayerHideTips(pl));
//					}
//
//					break;
//				}
//			}
//		}
	}
	
	private String getPlayerHideTips(Player player)
	{
		if(player == null)
		{
			return "����Ҳ�����!";
		}
		
		World world = player.getWorld();
		if(world == null)
		{
			return "The wolrd!";
		}
		
		Location loc = player.getLocation();

		int count = 0;
		//  X
		// X*X
		//  X
		Block blk = world.getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ());
		Block blk2 = world.getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ());
		Block blk3 = world.getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1);
		Block blk4 = world.getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1);

		if(blk != null && blk.getType() != Material.AIR)
		{
			count++;
		}
		if(blk2 != null && blk2.getType() != Material.AIR)
		{
			count++;
		}
		if(blk3 != null && blk3.getType() != Material.AIR)
		{
			count++;
		}
		if(blk4 != null && blk4.getType() != Material.AIR)
		{
			count++;
		}

		switch(count)
		{
		case 4:
			return "�������ܱ�ʲô����������Χ��������!";
		case 3:
			return "��������ʲô����Χ����!";
		case 2:
			return "����������ʲô����!";
		case 1:
			return "����������ʲô!";
		}
		
		// ͷ���ж���
		//   X
		//   X
		//   *
		blk = world.getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ());
		blk = world.getBlockAt(loc.getBlockX(), loc.getBlockY() + 3, loc.getBlockZ());
		blk = world.getBlockAt(loc.getBlockX(), loc.getBlockY() + 4, loc.getBlockZ());
		if(blk != null && blk.getType() != Material.AIR)
		{
			return "��ͷ���ж���!";
		}
		if(blk2 != null && blk2.getType() != Material.AIR)
		{
			return "��ͷ���ƺ���ʲô!";
		}

		return "��TMҲ��֪��������!";
	}
}
