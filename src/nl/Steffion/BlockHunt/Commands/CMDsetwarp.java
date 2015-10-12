package nl.Steffion.BlockHunt.Commands;

import nl.Steffion.BlockHunt.Arena;
import nl.Steffion.BlockHunt.ArenaHandler;
import nl.Steffion.BlockHunt.BlockHunt;
import nl.Steffion.BlockHunt.ConfigC;
import nl.Steffion.BlockHunt.W;
import nl.Steffion.BlockHunt.Managers.MessageM;
import nl.Steffion.BlockHunt.Serializables.LocationSerializable;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDsetwarp extends DefaultCMD
{

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args)
	{
		if (player != null)
		{
			if (args.length <= 2)
			{
				MessageM.sendFMessage(player, ConfigC.error_notEnoughArguments,
						"syntax-" + BlockHunt.CMDsetwarp.usage);
			}
			else
			{
				String arenaname = args[2];
				String warpname = args[1];
				String singularParam = "";
				if(args.length >= 4)
				{
					singularParam = args[3];
				}

				Arena arena = null;
				for (Arena arena2 : W.arenaList)
				{
					if (arena2.arenaName.equalsIgnoreCase(arenaname))
					{
						arena = arena2;
					}
				}
				if (arena != null)
				{
					LocationSerializable loc = new LocationSerializable(
							player.getLocation());
					if (warpname.equalsIgnoreCase("lobby"))
					{
						arena.lobbyWarp = loc;
						save(arena);
						MessageM.sendFMessage(player,
								ConfigC.normal_setwarpWarpSet, "warp-"
										+ warpname);
					}
					else if (warpname.equalsIgnoreCase("hiders"))
					{
						arena.hidersWarp = loc;
						save(arena);
						MessageM.sendFMessage(player,
								ConfigC.normal_setwarpWarpSet, "warp-"
										+ warpname);
					}
					else if (warpname.equalsIgnoreCase("seekers"))
					{
						arena.seekersWarp = loc;
						save(arena);
						MessageM.sendFMessage(player,
								ConfigC.normal_setwarpWarpSet, "warp-"
										+ warpname);
					}
					else if (warpname.equalsIgnoreCase("spawn"))
					{
						arena.spawnWarp = loc;
						save(arena);
						MessageM.sendFMessage(player,
								ConfigC.normal_setwarpWarpSet, "warp-"
										+ warpname);
					}
					else if (warpname.equalsIgnoreCase("singular"))
					{
						if(singularParam.isEmpty())
						{
							LocationSerializable sinPos = W.singularPos.get(player);
							for (LocationSerializable pos : arena.singularPoints)
							{
								LocationSerializable posTmp = (LocationSerializable) pos.clone();
								posTmp.subtract( sinPos );
								if(posTmp.length() < 1.5)
								{
									MessageM.sendFMessage(player, ConfigC.error_singularPointTooClose);
									return true;
								}
							}

							// test if in the same arene
							boolean xPass = false;
							boolean zPass = false;

							// test x
							if(arena.pos1.getX() >= arena.pos2.getX())
							{
								if( sinPos.getX() <= arena.pos1.getX()
										&& sinPos.getX() >= arena.pos2.getX())
								{
									xPass = true;
								}
							}
							else
							{
								if( sinPos.getX() >= arena.pos1.getX()
										&& sinPos.getX() <= arena.pos2.getX())
								{
									xPass = true;
								}
							}

							// test z
							if(arena.pos1.getZ() >= arena.pos2.getZ())
							{
								if( sinPos.getZ() <= arena.pos1.getZ()
										&& sinPos.getZ() >= arena.pos2.getZ())
								{
									zPass = true;
								}
							}
							else
							{
								if( sinPos.getZ() >= arena.pos1.getZ()
										&& sinPos.getZ() <= arena.pos2.getZ())
								{
									zPass = true;
								}
							}
							
							if(!xPass || !zPass)
							{
								MessageM.sendFMessage(player,
										ConfigC.normal_setSingularWarpSetFailed, "warp-"
												+ warpname, "arena-" + arena.arenaName);
							}
							else
							{
								if (sinPos != null
										&& sinPos
												.getWorld()
												.getName()
												.equals(arena.pos1.getWorld().getName()))
								{
									arena.singularPoints.add(sinPos);
									save(arena);
									MessageM.sendFMessage(player,
											ConfigC.normal_setwarpWarpSet, "warp-"
													+ warpname);
								}
								else
								{
									MessageM.sendFMessage(player, ConfigC.error_singularPointDiffWorld);
								}	
							}
						}
						else if(singularParam.equals("del"))
						{
							for (LocationSerializable pos : arena.singularPoints)
							{
								LocationSerializable posTmp = (LocationSerializable) pos.clone();
								LocationSerializable sinPos = W.singularPos.get(player);
								posTmp.subtract(sinPos);
								if(posTmp.length() < 1.5)
								{
									arena.singularPoints.remove(pos);
									save(arena);
									MessageM.sendFMessage(player,
											ConfigC.normal_delwarpWarpSet, "warp-"
													+ warpname);
									break;
								}
							}
						}
						else if(singularParam.equals("delall"))
						{
							arena.singularPoints.clear();
							save(arena);
							MessageM.sendFMessage(player,
									ConfigC.normal_delAllSingularWarpSet, "arena-"
											+ arena.arenaName);
						}
					}
					else
					{
						MessageM.sendFMessage(player,
								ConfigC.error_setwarpWarpNotFound, "warp-"
										+ warpname);
					}
				}
				else
				{
					MessageM.sendFMessage(player, ConfigC.error_noArena,
							"name-" + arenaname);
				}
			}
		}
		else
		{
			MessageM.sendFMessage(player, ConfigC.error_onlyIngame);
		}
		return true;
	}

	public void save(Arena arena)
	{
		W.arenas.getFile().set(arena.arenaName, arena);
		W.arenas.save();
		ArenaHandler.loadArenas();
	}
}
