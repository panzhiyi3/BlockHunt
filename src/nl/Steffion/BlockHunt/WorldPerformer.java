package nl.Steffion.BlockHunt;

import java.util.ArrayList;

import nl.Steffion.BlockHunt.Serializables.LocationSerializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;

public class WorldPerformer
{
	@SuppressWarnings("unchecked")
	static public ItemStack getRandomBlock(ArrayList<ItemStack> disguiseBlocks)
	{
		if(disguiseBlocks == null || !(disguiseBlocks instanceof ArrayList<?>))
		{
			return null;
		}

		long start = System.currentTimeMillis();
		ArrayList<ItemStack> tmpList = new ArrayList<ItemStack>();
		ArrayList<ItemStack> workList = (ArrayList<ItemStack>) disguiseBlocks.clone();
		while(workList.size() > 1)
		{
			if(System.currentTimeMillis() - start >= 500) //if costs more than 500ms, get random directly
			{
				return disguiseBlocks
						.get(W.random
								.nextInt(disguiseBlocks
										.size()));
			}

			tmpList.clear();
			for(ItemStack item : disguiseBlocks)
			{
				if(W.random.nextFloat() > 0.5)
				{
					tmpList.add(item);
				}
			}
			if(tmpList.isEmpty())
			{
				tmpList.add( workList.get( W.random.nextInt( workList.size() ) ) );
			}
			workList = (ArrayList<ItemStack>) tmpList.clone();
		}
		if(workList.size() == 1)
		{
			return workList.get(0);
		}
		if(tmpList.isEmpty())
		{
			return disguiseBlocks
					.get(W.random
							.nextInt(disguiseBlocks
									.size()));
		}
		return null;
	}

	static public void randomTheArena(World world, Arena arena) throws Exception
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
			pos1.subtract(1, 0, 1);
			double orgX = pos1.getX();

			for(int z = 0; z < 2; z++)
			{
				for(int i = 0; i < 3; i++)
				{
					for(int j = 0; j < 3; j++)
					{
						if(W.random.nextFloat() > 0.5)
						{
							ItemStack item = getRandomBlock(arena.disguiseBlocks);

							if(item != null)
							{
								// It may drop down
								if(item.getType() == Material.SAND)
								{
									continue;
								}

								Block block = world.getBlockAt(pos1);

								if(block != null)
								{
									block.setType( item.getType() );
									BlockState bs = block.getState();
									bs.setData(item.getData());
								}
							}
						}

						pos1.add(1, 0, 0);
					}
					pos1.setX(orgX);
					pos1.add(0, 0, 1);
				}
				pos1.add(0, 1, 0);
			}
		}
	}

	static public void recoverTheArena(World world, Arena arena) throws Exception
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
			pos1.subtract(1, 0, 1);
			double orgX = pos1.getX();

			for(int z = 0; z < 2; z++)
			{
				for(int i = 0; i < 3; i++)
				{
					for(int j = 0; j < 3; j++)
					{
						Block blockBak = worldBak.getBlockAt(pos1);
						Block block = world.getBlockAt(pos1);

						if(block != null && blockBak != null)
						{
							block.setType( blockBak.getType() );
							BlockState bs = block.getState();
							BlockState bsBak = blockBak.getState();
							bs.setData(bsBak.getData());
							block.getLocation().setDirection( blockBak.getLocation().getDirection() );
							bs.getLocation().setDirection( bsBak.getLocation().getDirection() );
							bs.update();
						}

						pos1.add(1, 0, 0);
					}
					pos1.setX(orgX);
					pos1.add(0, 0, 1);
				}
				pos1.add(0, 1, 0);
			}
		}
	}
}
