package nl.Steffion.BlockHunt;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Common
{
	public static Material SeekerWeapon = Material.DIAMOND_SWORD;
	public static float SWORD_ONEHIT_COST_EXP = 0.1f;
	public static float SWORD_HITHIDER_COST_EXP = 0.05f;
	public static float SWORD_COOLDOWN_PER_SEC = 0.03f;
	public static double WRONG_ATTACK_DAMAGE = 4.0;
	
	public static void Init()
	{
		Double num = (Double) W.config.get(ConfigC.seekerWeaponOneHitCost);
		SWORD_ONEHIT_COST_EXP = num.floatValue();

		num = (Double) W.config.get(ConfigC.seekerWeaponHitHiderCost);
		SWORD_HITHIDER_COST_EXP = num.floatValue();

		num = (Double) W.config.get(ConfigC.seekerWeaponCooldownPerSec);
		SWORD_COOLDOWN_PER_SEC = num.floatValue();

		num = (Double) W.config.get(ConfigC.seekerWrongAttackDamage);
		WRONG_ATTACK_DAMAGE = num;
	}
	
	public static boolean IsBlockOrModCanDisguise(ItemStack item)
	{
		if(item != null)
		{
			if(item.getType().isBlock())
			{
				return true;
			}
			else if(IsMobDisguise(item))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean IsMobDisguise(ItemStack item)
	{
		if (item != null)
		{
			Material mat = item.getType();
			if (mat == Material.COOKED_CHICKEN)
			{
				return true;
			}
			else if (mat == Material.COOKED_BEEF)
			{
				return true;
			}
			else if (mat == Material.GRILLED_PORK)
			{
				return true;
			}
			else if (mat == Material.RAW_CHICKEN)
			{
				return true;
			}
			else if (mat == Material.RAW_BEEF)
			{
				return true;
			}
			else if (mat == Material.PORK)
			{
				return true;
			}
			else if(mat == Material.COOKED_FISH)
			{
				return true;
			}
			else if (mat == Material.RAW_FISH)
			{
				return true;
			}
		}

		return false;
	}
	
	public static DisguiseType GetDisguiseType(ItemStack item)
	{
		if(item != null)
		{
			Material mat = item.getType();
			if (mat == Material.COOKED_CHICKEN
					|| mat == Material.RAW_CHICKEN)
			{
				return DisguiseType.CHICKEN;
			}
			else if (mat == Material.COOKED_BEEF
					|| mat == Material.RAW_BEEF)
			{
				return DisguiseType.COW;
			}
			else if (mat == Material.PORK
					|| mat == Material.GRILLED_PORK)
			{
				return DisguiseType.PIG;
			}
			else if(mat == Material.COOKED_FISH
					|| mat == Material.RAW_FISH)
			{
				return DisguiseType.OCELOT;
			}
		}

		return DisguiseType.GHAST;
	}
}
