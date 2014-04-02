package nl.Steffion.BlockHunt;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Common
{
	public static Material SeekerWeapon = Material.DIAMOND_SWORD;
	public static float SWORD_ONEHIT_COST_EXP = 0.1f;
	public static float SWORD_HITHIDER_COST_EXP = 0.05f;
	public static float SWORD_COOLDOWN_PER_SEC = 0.03f;
	public static double WRONG_ATTACK_DAMAGE = 4.0;
	public static double WRONG_ATTACK_ANIMAL_DAMAGE = 4.0;

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

		num = (Double) W.config.get(ConfigC.seekerWrongAttackAnimalDamage);
		WRONG_ATTACK_ANIMAL_DAMAGE = num;
	}

	static public double getDamageReduced(Player player)
	{
		org.bukkit.inventory.PlayerInventory inv = player.getInventory();
		ItemStack boots = inv.getBoots();
		ItemStack helmet = inv.getHelmet();
		ItemStack chest = inv.getChestplate();
		ItemStack pants = inv.getLeggings();
		double red = 0.0;
		if(helmet != null)
		{
			if (helmet.getType() == Material.LEATHER_HELMET)
				red = red + 0.04;
			else if (helmet.getType() == Material.GOLD_HELMET)
				red = red + 0.08;
			else if (helmet.getType() == Material.CHAINMAIL_HELMET)
				red = red + 0.08;
			else if (helmet.getType() == Material.IRON_HELMET)
				red = red + 0.08;
			else if (helmet.getType() == Material.DIAMOND_HELMET)
				red = red + 0.12;
		}

		if(boots != null)
		{
			if (boots.getType() == Material.LEATHER_BOOTS)
				red = red + 0.04;
			else if (boots.getType() == Material.GOLD_BOOTS)
				red = red + 0.04;
			else if (boots.getType() == Material.CHAINMAIL_BOOTS)
				red = red + 0.04;
			else if (boots.getType() == Material.IRON_BOOTS)
				red = red + 0.08;
			else if (boots.getType() == Material.DIAMOND_BOOTS)
				red = red + 0.12;
		}

		if(pants != null)
		{
			if (pants.getType() == Material.LEATHER_LEGGINGS)
				red = red + 0.08;
			else if (pants.getType() == Material.GOLD_LEGGINGS)
				red = red + 0.12;
			else if (pants.getType() == Material.CHAINMAIL_LEGGINGS)
				red = red + 0.16;
			else if (pants.getType() == Material.IRON_LEGGINGS)
				red = red + 0.20;
			else if (pants.getType() == Material.DIAMOND_LEGGINGS)
				red = red + 0.24;
		}

		if(chest != null)
		{
			if (chest.getType() == Material.LEATHER_CHESTPLATE)
				red = red + 0.12;
			else if (chest.getType() == Material.GOLD_CHESTPLATE)
				red = red + 0.20;
			else if (chest.getType() == Material.CHAINMAIL_CHESTPLATE)
				red = red + 0.20;
			else if (chest.getType() == Material.IRON_CHESTPLATE)
				red = red + 0.24;
			else if (chest.getType() == Material.DIAMOND_CHESTPLATE)
				red = red + 0.32;
		}

		return red;
	}
}
