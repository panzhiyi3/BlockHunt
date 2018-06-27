package nl.Steffion.BlockHunt;

import nl.Steffion.BlockHunt.Managers.ConfigM;

public enum ConfigC {
	/**
	 * Steffion's Engine - Made by Steffion.
	 * 
	 * You're allowed to use this engine for own usage, you're not allowed to
	 * republish the engine. Using this for your own plugin is allowed when a
	 * credit is placed somewhere in the plugin.
	 * 
	 * Thanks for your cooperate!
	 * 
	 * @author Steffion
	 */

	chat_tag ("[" + BlockHunt.pdfFile.getName() + "] ", W.config),
	chat_normal ("&b", W.config),
	chat_warning ("&c", W.config),
	chat_error ("&c", W.config),
	chat_arg ("&e", W.config),
	chat_header ("&9", W.config),
	chat_headerhigh ("%H_______.[ %A%header%%H ]._______", W.config),

	commandEnabled_info (true, W.config),
	commandEnabled_help (true, W.config),
	commandEnabled_reload (true, W.config),
	commandEnabled_join (true, W.config),
	commandEnabled_leave (true, W.config),
	commandEnabled_list (true, W.config),
	commandEnabled_shop (true, W.config),
	commandEnabled_start (true, W.config),
	commandEnabled_wand (true, W.config),
	commandEnabled_create (true, W.config),
	commandEnabled_set (true, W.config),
	commandEnabled_setwarp (true, W.config),
	commandEnabled_remove (true, W.config),
	commandEnabled_tokens (true, W.config),

	autoUpdateCheck (true, W.config),
	autoDownloadUpdate (false, W.config),

	vaultSupport (false, W.config),
	blockChooserPrice (3000, W.config),
	seekerHiderPrice (150, W.config),

	wandIDname ("STICK", W.config),
	wandName ("%A&l" + BlockHunt.pdfFile.getName() + "%N's selection wand",
			W.config),
	wandDescription (new String[] {
			"%NUse this item to select an arena for your arena.",
			"%ALeft-Click%N to select point #1.",
			"%ARight-Click%N to select point #2.",
			"%NUse the create command to define your arena.",
			"%A/" + BlockHunt.pdfFile.getName() + " <help|h>" }, W.config),

	singularWandIDname ("STICK", W.config),
	singularWandName ("%A&l" + BlockHunt.pdfFile.getName() + "%N's singularity point selection wand",
			W.config),
	singularWandDescription (new String[] {
			"%NUse this item to select a singularity point for your arena.",
			"%ALeft-Click%N to add point.",
			"%ARight-Click%N to delete point.",
			"%A/" + BlockHunt.pdfFile.getName() + " <help|h>" }, W.config),

	shop_title ("%H&lBlockHunt %NShop", W.config),
	shop_price ("%NPrice: %A%amount% %Ntokens.", W.config),
	shop_vaultPrice ("%NPrice: %A%amount%", W.config),

	shop_blockChooserv1Enabled (true, W.config),
	shop_blockChooserv1IDname ("BOOK", W.config),
	shop_blockChooserv1Price (3000, W.config),
	shop_blockChooserv1Name ("%H&lBlock Chooser", W.config),
	shop_blockChooserv1Description (new String[] {
			"%N在躲猫猫开始前可以拿着这个物品",
			"%N点%A右键%N来选择",
			"%N你想变身的方块!", "&6无限次数" }, W.config),

	shop_BlockHuntPassv2Enabled (true, W.config),
	shop_BlockHuntPassv2IDName ("NAME_TAG", W.config),
	shop_BlockHuntPassv2Price (150, W.config),
	shop_BlockHuntPassv2Name ("%H&lBlockHunt Pass", W.config),
	shop_BlockHuntPassv2Description (new String[] {
			"%N在躲猫猫开始前可以拿着这个物品",
			"%N点%A右键%N来选择",
			"%N你是Hider还是Seeker!", "&6一次性使用", },
			W.config),

	sign_LEAVE (new String[] { "%H[" + BlockHunt.pdfFile.getName() + "%H]",
			"&4LEAVE", "&8Right-Click", "&8To leave." }, W.config),
	sign_SHOP (new String[] { "%H[" + BlockHunt.pdfFile.getName() + "%H]",
			"&4SHOP", "&8Right-Click", "&8To shop." }, W.config),
	sign_WAITING (new String[] { "%H[" + BlockHunt.pdfFile.getName() + "%H]",
			"%A%arenaname%", "%A%players%%N/%A%maxplayers%", "&8Waiting..." },
			W.config),
	sign_STARTING (new String[] { "%H[" + BlockHunt.pdfFile.getName() + "%H]",
			"%A%arenaname%", "%A%players%%N/%A%maxplayers%",
			"&2Start: %A%timeleft%" }, W.config),
	sign_INGAME (new String[] { "%H[" + BlockHunt.pdfFile.getName() + "%H]",
			"%A%arenaname%", "%A%players%%N/%A%maxplayers%",
			"%EIngame: %A%timeleft%" }, W.config),

	scoreboard_enabled (true, W.config),
	scoreboard_title ("%H[" + BlockHunt.pdfFile.getName() + "]", W.config),
	scoreboard_timeleft ("%ATime left:", W.config),
	scoreboard_seekers ("%NSeekers:", W.config),
	scoreboard_hiders ("%NHiders:", W.config),
	scoreboard_vaultBank ("%NBank:", W.config),
	scoreboard_tokenAmount ("%NTokens:", W.config),

	requireInventoryClearOnJoin (false, W.config),

	nyanCooldown (6, W.config),
	seekerWeaponOneHitCost (0.1, W.config),
	seekerWeaponHitHiderCost (0.5, W.config),
	seekerWeaponCooldownPerSec (0.03, W.config),
	seekerWrongAttackDamage(4.0, W.config),
	seekerWrongAttackAnimalDamage(4.0, W.config),
	arrowDamageLevel (1, W.config),
	arrowNumber (24, W.config),
	seekerArrowNumber (8, W.config),

	theWorldCooldownSeeker (60, W.config),
	theWorldCooldownHider (999, W.config),
	theWorldTimeSeeker (1.0f, W.config),
	theWorldTimeHider (0.5f, W.config),
	theWorldRange (5, W.config),

	log_enabledPlugin ("%TAG%N%name%&a&k + %N%version% is now Enabled. Made by %A%autors%%N.",
			W.messages),
	log_disabledPlugin ("%TAG%N%name%&c&k - %N%version% is now Disabled. Made by %A%autors%%N.",
			W.messages),

	help_info ("%NDisplays the plugin's info.", W.messages),
	help_help ("%NShows a list of commands.", W.messages),
	help_reload ("%NReloads all configs.", W.messages),
	help_join ("%NJoins a " + BlockHunt.pdfFile.getName() + " game.",
			W.messages),
	help_leave ("%NLeave a " + BlockHunt.pdfFile.getName() + " game.",
			W.messages),
	help_list ("%NShows a list of available arenas.", W.messages),
	help_shop ("%NOpens the " + BlockHunt.pdfFile.getName() + " shop.",
			W.messages),
	help_start ("%NForces an arena to start.", W.messages),
	help_wand ("%NGives you the wand selection tool.", W.messages),
	help_create ("%NCreates an arena from your selection.", W.messages),
	help_set ("%NOpens a panel to set settings.", W.messages),
	help_setwarp ("%NSets warps for your arena.", W.messages),
	help_remove ("%NDeletes an Arena.", W.messages),
	help_tokens ("%NChange someones tokens.", W.messages),
	help_forcejoin ("%NForce all players join an arena.", W.messages),
	help_uaw ("%NAdd Unlimited Arrow Works to your inventory.", W.messages),
	help_tp ("%NTeleport you to arena lobby.", W.messages),
	help_name ("%NHide of Show player name.", W.messages),
	help_battlemode ("%NSwitch on/off Battle Mode or set spawn.", W.messages),
	help_standmode ("%NSwitch on/off Stand Mode or set spawn.", W.messages),

	button_add ("%NAdd %A%1%%N to %A%2%%N", W.messages),
	button_add2 ("Add", W.messages),
	button_setting ("%NSetting %A%1%%N is now: %A%2%%N.", W.messages),
	button_remove ("%NRemove %A%1%%N from %A%2%%N", W.messages),
	button_remove2 ("Remove", W.messages),

	normal_reloadedConfigs ("%TAG&aReloaded all configs!", W.messages),
	normal_joinJoinedArena ("%TAG%A%playername%%N 加入了本张躲猫猫地图 (%A%1%%N/%A%2%%N)",
			W.messages),
	normal_leaveYouLeft ("%TAG%N你离开了这张地图,感谢游玩!)!",
			W.messages),
	normal_leaveLeftArena ("%TAG%A%playername%%N 离开了这张地图. (%A%1%%N/%A%2%%N)",
			W.messages),
	normal_startForced ("%TAG%NYou forced to start arena '%A%arenaname%%N'!",
			W.messages),
	normal_wandGaveWand ("%TAG%NHere you go ;)! &o(Use the %A&o%type%%N&o!)",
			W.messages),
	normal_wandSetPosition ("%TAG%NSet position %A#%number%%N to location: (%A%x%%N, %A%y%%N, %A%z%%N).",
			W.messages),
	normal_singularWandSetPosition ("%TAG%NSet singular position to location: (%A%x%%N, %A%y%%N, %A%z%%N).",
			W.messages),
	normal_createCreatedArena ("%TAG%NCreated an arena with the name '%A%name%%N'.",
			W.messages),
	normal_lobbyArenaIsStarting ("%TAG%N本场躲猫猫将在%A%1%%N秒内开始!",
			W.messages),
	normal_lobbyArenaStarted ("%TAG%N躲猫猫开始啦! 再过%A%secs%%N秒Seeker们就出来抓你了!",
			W.messages),
	normal_ingameSeekerChoosen ("%TAG%N玩家 %A%seeker%%N 成为了天选之Seeker!",
			W.messages),
	normal_ingameBlock ("%TAG%N你将会变成一个 '%A%block%%N' 方块",
			W.messages),
	normal_ingameArenaEnd ("%TAG%N本局躲猫猫将在  %A%1%%N 秒内结束!",
			W.messages),
	normal_ingameSeekerSpawned ("%TAG%A%playername%%N 重生成为一名Seeker!",
			W.messages),
	normal_ingameGivenSword ("%TAG%N拿上你的武器!", W.messages),
	normal_ingameHiderDied ("%TAG%NHider %A%playername%%N 被 %A%killername%%N 杀了! 还剩%A%left%%N 个Hider...",
			W.messages),
	normal_ingameSeekerDied ("%TAG%NSeeker %A%playername%%N 被 %A%killername%%N 杀了! 他将在 %A%secs%%N 秒内接受洗礼完成重生!",
			W.messages),
	normal_winSeekers ("%TAG %ASEEKERS%N 赢得了比赛!", W.messages),
	normal_winHiders ("%TAG %AHIDERS%N 赢得了比赛!", W.messages),
	normal_setwarpWarpSet ("%TAG%NSet warp '%A%warp%%N' to your location!",
			W.messages),
	normal_setSingularWarpSetFailed ("%TAG%NSet warp '%A%warp%%N' failed! The point you chose is not in the arena '%A%arena%",
			W.messages),
	normal_delwarpWarpSet ("%TAG%NDel warp '%A%warp%%N' to your location!",
			W.messages),
	normal_delAllSingularWarpSet ("%TAG%NDel all singular points at arena '%A%arena%'!",
			W.messages),
	normal_addedToken ("%TAG%A%amount%%N 大洋跑到了你的荷包中",
			W.messages),
	normal_addedVaultBalance ("%TAG%A%amount%%N was added to your bank!",
			W.messages),
	normal_addedVaultBalanceKill ("%TAG%N杀死一名Hider的奖励:%A%amount%",
			W.messages),
	normal_removeRemovedArena ("%TAG%NRemoved arena '%A%name%%N'!", W.messages),
	normal_tokensChanged ("%TAG%N%option% %A%amount%%N tokens %option2% %A%playername%%N.",
			W.messages),
	normal_tokensChangedPerson ("%TAG%NPlayer %A%playername%%N %N%option% %A%amount%%N %option2% your tokens.",
			W.messages),
	normal_ingameNowSolid ("%TAG%N啊啊啊!你一觉醒来,发现自己变成了一个 '%A%block%%N' 方块!",
			W.messages),
	normal_ingameNoMoreSolid ("%TAG%N啊啊啊!你又一觉醒来,发现自己不再是一个方块!",
			W.messages),
	normal_shopBoughtItem ("%TAG%NYou've bought the '%A%itemname%%N' item!",
			W.messages),
	normal_shopChoosenBlock ("%TAG%N你自愿选择成为一个 '%A%block%%N' 方块!",
			W.messages),
	normal_shopChoosenSeeker ("%TAG%N你已成为天选之 %Aseeker%N!",
			W.messages),
	normal_shopChoosenHiders ("%TAG%N你已成为天选之 %Ahider%N!",
			W.messages),

	warning_lobbyNeedAtleast ("%TAG%WYou need atleast %A%1%%W player(s) to start the game!",
			W.messages),
	warning_ingameNEWSeekerChoosen ("%TAG%WThe last seeker left and a new seeker has been choosen!",
			W.messages),
	warning_unableToCommand ("%TAG%WSorry but that command is disabled in the arena.",
			W.messages),
	warning_ingameNoSolidPlace ("%TAG%W这个破地方无法让你变身成为一名魔法少(方)女(块)!",
			W.messages),
	warning_arenaStopped ("%TAG%WThe arena has been forced to stop!",
			W.messages),
	warning_noVault ("%TAG%WUsing BlockHunts token system!", W.messages),
	warning_usingVault ("%TAG%WUsing Vault support", W.messages),

	error_noPermission ("%TAG%EYou don't have the permissions to do that!",
			W.messages),
	error_notANumber ("%TAG%E'%A%1%%E' is not a number!", W.messages),
	error_commandNotEnabled ("%TAG%EThis command has been disabled!",
			W.messages),
	error_commandNotFound ("%TAG%ECouldn't find the command. Try %A/"
			+ BlockHunt.pdfFile.getName() + " help %Efor more info.",
			W.messages),
	error_notEnoughArguments ("%TAG%EYou're missing arguments, correct syntax: %A%syntax%",
			W.messages),
	error_libsDisguisesNotInstalled ("%TAG%EThe plugin '%ALib's Disguises%E' is required to run this plugin! Intall it or it won't work!",
			W.messages),
	error_disguisesPluginNotInstalled ("%TAG%EThe plugin '%ADisguisesCraft%E' or '%ALib's Disguise%E' is required to run this plugin! Intall it or it won't work!",
			W.messages),
	error_protocolLibNotInstalled ("%TAG%EThe plugin '%AProtocolLib%E' is required to run this plugin! Intall it or it won't work!",
			W.messages),
	error_noArena ("%TAG%ENo arena found with the name '%A%name%%E'.",
			W.messages),
	error_onlyIngame ("%TAG%EThis is an only in-game command!", W.messages),
	error_joinAlreadyJoined ("%TAG%E你已经加入过了躲猫猫地图!",
			W.messages),
	error_joinNoBlocksSet ("%TAG%EThere are none blocks set for this arena. Notify the administrator.",
			W.messages),
	error_joinWarpsNotSet ("%TAG%EThere are no warps set for this arena. Notify the administrator.",
			W.messages),
	error_joinArenaIngame ("%TAG%E游戏已经开始啦.", W.messages),
	error_joinFull ("%TAG%E房间已满无法加入!", W.messages),
	error_joinInventoryNotEmpty ("%TAG%EYour inventory should be empty before joining!",
			W.messages),
	error_leaveNotInArena ("%TAG%E你不在一个躲猫猫地图中!", W.messages),
	error_createSelectionFirst ("%TAG%EMake a selection first. Use the wand command: %A/"
			+ BlockHunt.pdfFile.getName() + " <wand|w>%E.",
			W.messages),
	error_createNotSameWorld ("%TAG%EMake your selection points in the same world!",
			W.messages),
	error_setTooHighNumber ("%TAG%EThat amount is too high! Max amount is: %A%max%%E.",
			W.messages),
	error_setTooLowNumber ("%TAG%EThat amount is too low! Minimal amount is: %A%min%%E.",
			W.messages),
	error_setNotABlock ("%TAG%EThat is not a block!", W.messages),
	error_setwarpWarpNotFound ("%TAG%EWarp '%A%warp%%E' is not valid!",
			W.messages),
	error_tokensPlayerNotOnline ("%TAG%ENo player found with the name '%A%playername%%E'!",
			W.messages),
	error_tokensUnknownsetting ("%TAG%E'%A%option%%E' is not a known option!",
			W.messages),
	error_shopNeedMoreTokens ("%TAG%EYou need more tokens before you can buy this item.",
			W.messages),
	error_shopNeedMoreMoney ("%TAG%EYou don't have enough money to do this",
			W.messages),
	error_shopMaxSeekersReached ("%TAG%ESorry, the maximum amount of seekers has been reached!",
			W.messages),
	error_shopMaxHidersReached ("%TAG%ESorry, the maximum amount of hiders has been reached!",
			W.messages),
	error_trueVaultNull ("%TAG%EVault has been enabled in the config.yml but cannot find the 'Vault' plugin! The plugin will not run",
			W.messages),
	error_singularPointTooClose ("%TAG%EThe singular point you choose is too close to another one in the arena.",
			W.messages),
	error_singularPointDiffWorld ("%TAG%EThe singular point you choose is not the same world as the arene.",
			W.messages);

	public Object value;
	public ConfigM config;
	public String location;

	/**
	 * Makes an object from the list above.
	 * 
	 * @param value
	 *            Setting in the config file.
	 * @param config
	 *            The config file.
	 */
	private ConfigC (Object value, ConfigM config) {
		this.value = value;
		this.config = config;
		this.location = this.name().replaceAll("_", ".");
	}
}
