package nl.Steffion.BlockHunt;

import nl.Steffion.BlockHunt.Serializables.LocationSerializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class WorldPerformer
{
	public void randomTheArena(World world, Arena arena)
	{
		if(arena.singularPoints.isEmpty())
			return;

		String targetName = world.getName();
		String bakName = targetName + "_bak";
		World worldBak = Bukkit.getWorld(bakName);
		if(worldBak == null)
		{
			return;
		}
	}

	public void recoverTheArena(World world, Arena arena) throws Exception
	{
		if(arena.singularPoints.isEmpty())
			return;

		String targetName = world.getName();
		String bakName = targetName + "_bak";
		World worldBak = Bukkit.getWorld(bakName);
		if(worldBak == null)
		{
			throw new Exception("Bak world: " + bakName + " is not exist!");
		}

		for (LocationSerializable point : arena.singularPoints)
		{
			Location pos1 = point.clone();
			//Location pos2 = point.clone();
			pos1.subtract(1, 0, 1);
			//pos2.add(1, 0, 1);
			double orgX = pos1.getX();

			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					Block blockBak = worldBak.getBlockAt(pos1);
					Block block = world.getBlockAt(pos1);

					if(block != null && blockBak != null)
					{
						block.setType( blockBak.getType() );
					}

					pos1.add(1, 0, 0);
				}
				pos1.setX(orgX);
				pos1.add(0, 0, 1);
			}
		}
	}
}
