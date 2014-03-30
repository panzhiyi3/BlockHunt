package nl.Steffion.BlockHunt;

import org.bukkit.Material;

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
}
