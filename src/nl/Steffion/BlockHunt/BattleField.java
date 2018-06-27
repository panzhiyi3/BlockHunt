package nl.Steffion.BlockHunt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nl.Steffion.BlockHunt.Serializables.LocationSerializable;
import nl.Steffion.BlockHunt.Serializables.M;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("BlockHuntBattleField")
public class BattleField implements ConfigurationSerializable
{
	public ArrayList<LocationSerializable> spawnPoints;
	
	public void clear()
	{
		spawnPoints.clear();
	}
	
	@Override
	public Map<String, Object> serialize()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("spawnPoints", spawnPoints);
		return map;
	}

	public BattleField (ArrayList<LocationSerializable> spawnPoints)
	{
		this.spawnPoints = spawnPoints;
	}

	@SuppressWarnings("unchecked")
	public static BattleField deserialize(Map<String, Object> map)
	{
		return new BattleField(
				(ArrayList<LocationSerializable>) M.g(map, "spawnPoints", new ArrayList<LocationSerializable>())
				);
	}
}
