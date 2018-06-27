package nl.Steffion.BlockHunt;

import java.util.ArrayList;
import java.util.List;

import net.milkbowl.vault.economy.Economy;
import nl.Steffion.BlockHunt.Arena.ArenaState;
import nl.Steffion.BlockHunt.PermissionsC.Permissions;
import nl.Steffion.BlockHunt.Commands.CMDbattlemode;
import nl.Steffion.BlockHunt.Commands.CMDcreate;
import nl.Steffion.BlockHunt.Commands.CMDforcejoin;
import nl.Steffion.BlockHunt.Commands.CMDhelp;
import nl.Steffion.BlockHunt.Commands.CMDinfo;
import nl.Steffion.BlockHunt.Commands.CMDjoin;
import nl.Steffion.BlockHunt.Commands.CMDleave;
import nl.Steffion.BlockHunt.Commands.CMDlist;
import nl.Steffion.BlockHunt.Commands.CMDnotfound;
import nl.Steffion.BlockHunt.Commands.CMDreload;
import nl.Steffion.BlockHunt.Commands.CMDremove;
import nl.Steffion.BlockHunt.Commands.CMDset;
import nl.Steffion.BlockHunt.Commands.CMDsetwarp;
import nl.Steffion.BlockHunt.Commands.CMDshop;
import nl.Steffion.BlockHunt.Commands.CMDstart;
import nl.Steffion.BlockHunt.Commands.CMDtokens;
import nl.Steffion.BlockHunt.Commands.CMDtp;
import nl.Steffion.BlockHunt.Commands.CMDuaw;
import nl.Steffion.BlockHunt.Commands.CMDwand;
import nl.Steffion.BlockHunt.Commands.CMDstandmode;
import nl.Steffion.BlockHunt.Listeners.OnAsyncPlayerChatEvent;
import nl.Steffion.BlockHunt.Listeners.OnBlockBreakEvent;
import nl.Steffion.BlockHunt.Listeners.OnBlockPlaceEvent;
import nl.Steffion.BlockHunt.Listeners.OnEntityDamageByEntityEvent;
import nl.Steffion.BlockHunt.Listeners.OnEntityDamageEvent;
import nl.Steffion.BlockHunt.Listeners.OnEntityDeathEvent;
import nl.Steffion.BlockHunt.Listeners.OnFoodLevelChangeEvent;
import nl.Steffion.BlockHunt.Listeners.OnInventoryClickEvent;
import nl.Steffion.BlockHunt.Listeners.OnInventoryCloseEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerCommandPreprocessEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerDropItemEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerInteractEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerJoinEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerMoveEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerQuitEvent;
import nl.Steffion.BlockHunt.Listeners.OnPlayerRespawnEvent;
import nl.Steffion.BlockHunt.Listeners.OnSignChangeEvent;
import nl.Steffion.BlockHunt.Managers.CommandM;
import nl.Steffion.BlockHunt.Managers.ConfigM;
import nl.Steffion.BlockHunt.Managers.MessageM;
import nl.Steffion.BlockHunt.Managers.PermissionsM;
import nl.Steffion.BlockHunt.Serializables.LocationSerializable;
import nl.Steffion.BlockHunt.mcstats.Metrics;
import nl.Steffion.BlockHunt.mcstats.Metrics.Graph;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockHunt extends JavaPlugin implements Listener {
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

	public static PluginDescriptionFile pdfFile;
	public static BlockHunt plugin;

	public static Economy econ = null;

	@SuppressWarnings("serial")
	public static List<String> BlockHuntCMD = new ArrayList<String>() {
		{
			add("info");
			add("help");
			add("reload");
			add("join");
			add("leave");
			add("list");
			add("shop");
			add("start");
			add("wand");
			add("create");
			add("set");
			add("setwarp");
			add("remove");
			add("tokens");
			add("forcejoin");
			add("uaw");
			add("tp");
			add("battlemode");
			add("standmode");
		}
	};

	public static CommandM CMD;
	public static CommandM CMDinfo;
	public static CommandM CMDhelp;
	public static CommandM CMDreload;
	public static CommandM CMDjoin;
	public static CommandM CMDleave;
	public static CommandM CMDlist;
	public static CommandM CMDshop;
	public static CommandM CMDstart;
	public static CommandM CMDwand;
	public static CommandM CMDcreate;
	public static CommandM CMDset;
	public static CommandM CMDsetwarp;
	public static CommandM CMDremove;
	public static CommandM CMDtokens;
	public static CommandM CMDforcejoin;
	public static CommandM CMDuaw;
	public static CommandM CMDtp;
	public static CommandM CMDbattlemode;
	public static CommandM CMDstandmode;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

		getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(),
				this);
		getServer().getPluginManager().registerEvents(new OnBlockPlaceEvent(),
				this);
		getServer().getPluginManager().registerEvents(
				new OnEntityDamageByEntityEvent(), this);
		getServer().getPluginManager().registerEvents(
				new OnEntityDamageEvent(), this);
		getServer().getPluginManager().registerEvents(
				new OnFoodLevelChangeEvent(), this);
		getServer().getPluginManager().registerEvents(
				new OnInventoryClickEvent(), this);
		getServer().getPluginManager().registerEvents(
				new OnInventoryCloseEvent(), this);
		getServer().getPluginManager().registerEvents(
				new OnPlayerCommandPreprocessEvent(), this);
		getServer().getPluginManager().registerEvents(
				new OnPlayerDropItemEvent(), this);
		getServer().getPluginManager().registerEvents(
				new OnPlayerInteractEvent(), this);
		getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(),
				this);
		getServer().getPluginManager().registerEvents(new OnPlayerQuitEvent(),
				this);
		getServer().getPluginManager().registerEvents(new OnSignChangeEvent(),
				this);
		
		getServer().getPluginManager().registerEvents(new OnPlayerJoinEvent(),
				this);
		//getServer().getPluginManager().registerEvents(new OnEntityShootBowEvent(),
		//		this);
		getServer().getPluginManager().registerEvents(new OnEntityDeathEvent(),
				this);
		getServer().getPluginManager().registerEvents(new OnAsyncPlayerChatEvent(),
				this);
		getServer().getPluginManager().registerEvents(new OnPlayerRespawnEvent(),
				this);

		ConfigurationSerialization.registerClass(LocationSerializable.class,
				"BlockHuntLocation");
		ConfigurationSerialization.registerClass(Arena.class, "BlockHuntArena");
		ConfigurationSerialization.registerClass(BattleField.class, "BlockHuntBattleField");

		pdfFile = getDescription();
		plugin = this;

		ConfigM.newFiles();

		CMD = new CommandM("BlockHunt", "BlockHunt", null, null,
				Permissions.info, ConfigC.help_info,
				(Boolean) W.config.get(ConfigC.commandEnabled_info),
				BlockHuntCMD, new CMDinfo(), null);
		CMDinfo = new CommandM("BlockHunt INFO", "BlockHunt", "info", "i",
				Permissions.info, ConfigC.help_info,
				(Boolean) W.config.get(ConfigC.commandEnabled_info),
				BlockHuntCMD, new CMDinfo(), "/BlockHunt [info|i]");
		CMDhelp = new CommandM("BlockHunt HELP", "BlockHunt", "help", "h",
				Permissions.help, ConfigC.help_help,
				(Boolean) W.config.get(ConfigC.commandEnabled_help),
				BlockHuntCMD, new CMDhelp(),
				"/BlockHunt <help|h> [page number]");
		CMDreload = new CommandM("BlockHunt RELOAD", "BlockHunt", "reload",
				"r", Permissions.reload, ConfigC.help_reload,
				(Boolean) W.config.get(ConfigC.commandEnabled_reload),
				BlockHuntCMD, new CMDreload(), "/BlockHunt <reload|r>");
		CMDjoin = new CommandM("BlockHunt JOIN", "BlockHunt", "join", "j",
				Permissions.join, ConfigC.help_join,
				(Boolean) W.config.get(ConfigC.commandEnabled_join),
				BlockHuntCMD, new CMDjoin(), "/BlockHunt <join|j> <arenaname>");
		CMDleave = new CommandM("BlockHunt LEAVE", "BlockHunt", "leave", "l",
				Permissions.leave, ConfigC.help_leave,
				(Boolean) W.config.get(ConfigC.commandEnabled_leave),
				BlockHuntCMD, new CMDleave(), "/BlockHunt <leave|l>");
		CMDlist = new CommandM("BlockHunt LIST", "BlockHunt", "list", "li",
				Permissions.list, ConfigC.help_list,
				(Boolean) W.config.get(ConfigC.commandEnabled_list),
				BlockHuntCMD, new CMDlist(), "/BlockHunt <list|li>");
		CMDshop = new CommandM("BlockHunt SHOP", "BlockHunt", "shop", "sh",
				Permissions.shop, ConfigC.help_shop,
				(Boolean) W.config.get(ConfigC.commandEnabled_shop),
				BlockHuntCMD, new CMDshop(), "/BlockHunt <shop|sh>");
		CMDstart = new CommandM("BlockHunt START", "BlockHunt", "start", "go",
				Permissions.start, ConfigC.help_start,
				(Boolean) W.config.get(ConfigC.commandEnabled_start),
				BlockHuntCMD, new CMDstart(),
				"/BlockHunt <start|go> <arenaname>");
		CMDwand = new CommandM("BlockHunt WAND", "BlockHunt", "wand", "w",
				Permissions.create, ConfigC.help_wand,
				(Boolean) W.config.get(ConfigC.commandEnabled_wand),
				BlockHuntCMD, new CMDwand(), "/BlockHunt <wand|w>");
		CMDcreate = new CommandM("BlockHunt CREATE", "BlockHunt", "create",
				"c", Permissions.create, ConfigC.help_create,
				(Boolean) W.config.get(ConfigC.commandEnabled_create),
				BlockHuntCMD, new CMDcreate(),
				"/BlockHunt <create|c> <arenaname>");
		CMDset = new CommandM("BlockHunt SET", "BlockHunt", "set", "s",
				Permissions.set, ConfigC.help_set,
				(Boolean) W.config.get(ConfigC.commandEnabled_set),
				BlockHuntCMD, new CMDset(), "/BlockHunt <set|s> <arenaname>");
		CMDsetwarp = new CommandM("BlockHunt SETWARP", "BlockHunt", "setwarp",
				"sw", Permissions.setwarp, ConfigC.help_setwarp,
				(Boolean) W.config.get(ConfigC.commandEnabled_setwarp),
				BlockHuntCMD, new CMDsetwarp(),
				"/BlockHunt <setwarp|sw> <lobby|hiders|seekers|spawn|singular> <arenaname> <del|delall>(for singular only)");
		CMDremove = new CommandM("BlockHunt REMOVE", "BlockHunt", "remove",
				"delete", Permissions.remove, ConfigC.help_remove,
				(Boolean) W.config.get(ConfigC.commandEnabled_remove),
				BlockHuntCMD, new CMDremove(),
				"/BlockHunt <remove|delete> <arenaname>");
		CMDtokens = new CommandM("BlockHunt TOKENS", "BlockHunt", "tokens",
				"t", Permissions.tokens, ConfigC.help_tokens,
				(Boolean) W.config.get(ConfigC.commandEnabled_tokens),
				BlockHuntCMD, new CMDtokens(),
				"/BlockHunt <tokens|t> <set|add|take> <playername> <amount>");
		CMDforcejoin = new CommandM("BlockHunt FORCEJOIN", "BlockHunt", "forcejoin",
				"fjoin", Permissions.allcommands, ConfigC.help_forcejoin,
				true,
				BlockHuntCMD, new CMDforcejoin(),
				"/BlockHunt <forcejoin|fjoin> <random> | <arena:name>");
		CMDuaw = new CommandM("BlockHunt UAW", "BlockHunt", "arrowworks",
				"uaw", Permissions.allcommands, ConfigC.help_uaw,
				true,
				BlockHuntCMD, new CMDuaw(),
				"/BlockHunt <arrowworks|uaw>");
		CMDtp = new CommandM("BlockHunt TP", "BlockHunt", "tp",
				"tp", Permissions.allcommands, ConfigC.help_tp,
				true,
				BlockHuntCMD, new CMDtp(),
				"/BlockHunt tp ArenaName");
		CMDbattlemode = new CommandM("BlockHunt BattleMode", "BlockHunt", "battlemode",
				"bm", Permissions.allcommands, ConfigC.help_battlemode,
				true,
				BlockHuntCMD, new CMDbattlemode(),
				"/BlockHunt <battlemode|bm> <on|off|setspawn|clearspawn>");
		CMDstandmode = new CommandM("BlockHunt StandMode", "BlockHunt", "standmode",
				"sm", Permissions.allcommands, ConfigC.help_standmode,
				true,
				BlockHuntCMD, new CMDstandmode(),
				"/BlockHunt <standmode|sm> <on|off>");

		DisguiseDelegate.GetSingleton().SetupDisguiseCraft(getServer());

		if (!getServer().getPluginManager().isPluginEnabled("ProtocolLib")) {
			MessageM.broadcastFMessage(ConfigC.error_protocolLibNotInstalled);
		}
		
		if(W.config.getFile().getBoolean("vaultSupport") == true) {
			if(!getServer().getPluginManager().isPluginEnabled("Vault")) {
				MessageM.broadcastFMessage(ConfigC.error_trueVaultNull);
				return;
			}else{
				MessageM.broadcastFMessage(ConfigC.warning_usingVault);
			}
		}else{
			MessageM.broadcastFMessage(ConfigC.warning_noVault);
		}

		setupEconomy();

		ArenaHandler.loadArenas();
		W.battleField.clear();
		for(String name : W.battlefieldCfg.getFile().getKeys(false))
		{
			W.battleField = (BattleField) W.battlefieldCfg.getFile().get(name);
		}
		
		Common.Init();

		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

			@Override
			public void run() {
				try {
					Metrics metrics = new Metrics(plugin);
					Graph playersPlayingBlockHunt = metrics
							.createGraph("Players playing BlockHunt");

					playersPlayingBlockHunt.addPlotter(new Metrics.Plotter(
							"Playing") {

						@Override
						public int getValue() {
							int playersPlaying = 0;
							for (Arena arena : W.arenaList) {
								playersPlaying = playersPlaying
										+ arena.playersInArena.size();
							}
							return playersPlaying;
						}

					});

					playersPlayingBlockHunt.addPlotter(new Metrics.Plotter(
							"Not playing") {

						@Override
						public int getValue() {
							int playersPlaying = 0;
							for (Arena arena : W.arenaList) {
								playersPlaying = playersPlaying
										+ arena.playersInArena.size();
							}
							return Bukkit.getOnlinePlayers().length
									- playersPlaying;
						}

					});
				} catch (Exception e) {
					MessageM.sendMessage(null,
							"%TAG%EUnable to send %AMCStats %Eto the server. Something went wrong ;(!");
				}
			}
		}, 0, 6000);

		Metrics metrics;
		try {
			metrics = new Metrics(plugin);
			metrics.start();
			FileConfiguration metrics_fc = new YamlConfiguration();
			metrics_fc.load(metrics.getConfigFile());
			if (!metrics_fc.getBoolean("opt-out", false)) {
				MessageM.sendMessage(null,
						"%TAG%NSending %AMCStats%N to the server...");
			} else {
				MessageM.sendMessage(null,
						"%TAG%EUnable to send %AMCStats %Eto the server. %AMCStats%E is disabled?");
			}
		} catch (Exception e) {
			MessageM.sendMessage(null,
					"%TAG%EUnable to send %AMCStats %Eto the server. Something went wrong ;(!");
		}

		if ((Boolean) W.config.get(ConfigC.autoUpdateCheck)) {
			if ((Boolean) W.config.get(ConfigC.autoDownloadUpdate)) {
				new Updater(this, pdfFile.getName(), this.getFile(),
						Updater.UpdateType.DEFAULT, true);
			} else {
				new Updater(this, pdfFile.getName(), this.getFile(),
						Updater.UpdateType.NO_DOWNLOAD, true);
			}
		}

		MessageM.sendFMessage(null, ConfigC.log_enabledPlugin, "name-"
				+ BlockHunt.pdfFile.getName(),
				"version-" + BlockHunt.pdfFile.getVersion(), "autors-"
						+ BlockHunt.pdfFile.getAuthors().get(0));

		// 20 ticks equal 1 second
		getServer().getScheduler().runTaskTimer(this, new Runnable()
		{
			@Override
			public void run()
			{
				mainLoop();
			}
		}, 0, 20);

		// 500ms执行一次The World的定时器
		getServer().getScheduler().runTaskTimer(this, new Runnable()
		{
			@Override
			public void run()
			{
				theWorldLoop();
			}
		}, 0, 10);
	}

	public void onDisable() {
		for (Arena arena : W.arenaList) {
			ArenaHandler.stopArena(arena);
		}

		MessageM.sendFMessage(null, ConfigC.log_disabledPlugin, "name-"
				+ BlockHunt.pdfFile.getName(),
				"version-" + BlockHunt.pdfFile.getVersion(), "autors-"
						+ BlockHunt.pdfFile.getAuthors().get(0));
	}

	/**
	 * Args to String. Makes 1 string.
	 * 
	 * @param input
	 *            String list which should be converted to a string.
	 * @param startArg
	 *            Start on this length.
	 * 
	 * @return The converted string.
	 */
	public static String stringBuilder(String[] input, int startArg) {
		if (input.length - startArg <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder(input[startArg]);
		for (int i = ++startArg; i < input.length; i++) {
			sb.append(' ').append(input[i]);
		}
		return sb.toString();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}

		for (CommandM command : W.commands) {
			String[] argsSplit = null;
			String[] argsSplitAlias = null;

			if (command.args != null && command.argsalias != null) {
				argsSplit = command.args.split("/");
				argsSplitAlias = command.argsalias.split("/");
			}

			if (cmd.getName().equalsIgnoreCase(command.label)) {
				boolean equals = true;

				if (argsSplit == null) {
					if (args.length == 0) {
						equals = true;
					} else {
						equals = false;
					}
				} else {
					if (args.length >= argsSplit.length) {
						for (int i2 = argsSplit.length - 1; i2 >= 0; i2 = i2 - 1) {
							int loc = argsSplit.length - i2 - 1;
							if (!argsSplit[loc].equalsIgnoreCase(args[loc])
									&& !argsSplitAlias[loc]
											.equalsIgnoreCase(args[loc])) {
								equals = false;
							}
						}
					} else {
						equals = false;
					}
				}

				if (equals) {
					if (PermissionsM.hasPerm(player, command.permission, true)) {
						if (command.enabled) {
							command.CMD.exectue(player, cmd, label, args);
						} else {
							MessageM.sendFMessage(player,
									ConfigC.error_commandNotEnabled);
						}
					}

					return true;
				}
			}
		}

		CMDnotfound.exectue(player, cmd, label, args);
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd,
			String label, String[] args) {

		for (CommandM command : W.commands) {
			if (cmd.getName().equalsIgnoreCase(command.label)) {
				if (args.length == 1) {
					return command.mainTABlist;
				}
			}
		}

		return null;
	}

	/**
	 * Short a String for like the Scoreboard title.
	 * 
	 * @param string
	 *            String to be shorten.
	 * @param maxLenght
	 *            Max lenght of the characters.
	 * @return Shorten string, else normal string.
	 */
	public static String cutString(String string, int maxLenght) {
		if (string.length() > maxLenght) {
			string = string.substring(0, maxLenght);
		}
		return string;
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}

		econ = rsp.getProvider();
		return econ != null;
	}

	/*
	 * The main loop
	 */
	private void mainLoop()
	{
		if(W.BattleMode)
		{
			for(Player pl : W.playerTobeRespawn)
			{
				// Respawn in random point
				if(!W.battleField.spawnPoints.isEmpty())
				{
					LocationSerializable loc = W.battleField.spawnPoints.get( W.random.nextInt( W.battleField.spawnPoints.size() ) );
					pl.teleport(loc);
				}
			}
			W.playerTobeRespawn.clear();

			for (Player pl : Bukkit
					.getOnlinePlayers())
			{
				boolean canHide = true;
				for (Arena arena : W.arenaList)
				{
					if(arena.playersInArena.contains(pl))
					{
						canHide = false;
						break;
					}
				}
				if(canHide)
				{
					if(!DisguiseDelegate.GetSingleton().IsDisguised(pl))
					{
						DisguiseDelegate.GetSingleton().DisguiseAsPlayer(pl);
					}
				}
			}
		}

		for (Arena arena : W.arenaList)
		{
			perArenaLoop(arena);
		}

		SignsHandler.updateSigns();
	}

	private void perArenaLoop(Arena arena)
	{
		if (arena.gameState == ArenaState.WAITING)
		{
			if (arena.playersInArena.size() >= arena.minPlayers)
			{
				arena.gameState = ArenaState.STARTING;
				arena.timer = arena.timeInLobbyUntilStart;
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_lobbyArenaIsStarting, "1-"
								+ arena.timeInLobbyUntilStart);
			}
		}
		else if (arena.gameState == ArenaState.STARTING)
		{
			arena.timer = arena.timer - 1;
			if (arena.timer > 0)
			{
				processArenaStartingCountdown(arena);
			}
			else // InGame
			{
				processArenaStarting(arena);
			}
		}

		processArenaSeekers(arena);

		if (arena.gameState == ArenaState.INGAME)
		{
			processArenaInGame(arena);
		}

		for (Player pl : arena.playersInArena)
		{
			pl.setLevel(arena.timer);
			pl.setGameMode(GameMode.SURVIVAL);
		}

		ScoreboardHandler.updateScoreboard(arena);
	}

	private void processArenaStartingCountdown(Arena arena)
	{
		if (arena.timer == 60)
		{
			ArenaHandler.sendFMessage(arena,
					ConfigC.normal_lobbyArenaIsStarting,
					"1-60");
		}
		else if (arena.timer == 30)
		{
			ArenaHandler.sendFMessage(arena,
					ConfigC.normal_lobbyArenaIsStarting,
					"1-30");
		}
		else if (arena.timer == 10)
		{
			ArenaHandler.sendFMessage(arena,
					ConfigC.normal_lobbyArenaIsStarting,
					"1-10");
		}
		else if (arena.timer == 5)
		{
			for (Player pl : arena.playersInArena)
			{
				pl.playSound(pl.getLocation(),
						Sound.ORB_PICKUP, 1, 0);
			}
			ArenaHandler.sendFMessage(arena,
					ConfigC.normal_lobbyArenaIsStarting,
					"1-5");
		}
		else if (arena.timer == 4)
		{
			for (Player pl : arena.playersInArena)
			{
				pl.playSound(pl.getLocation(),
						Sound.ORB_PICKUP, 1, 0);
			}
			ArenaHandler.sendFMessage(arena,
					ConfigC.normal_lobbyArenaIsStarting,
					"1-4");
		}
		else if (arena.timer == 3)
		{
			for (Player pl : arena.playersInArena)
			{
				pl.playSound(pl.getLocation(),
						Sound.ORB_PICKUP, 1, 1);
			}
			ArenaHandler.sendFMessage(arena,
					ConfigC.normal_lobbyArenaIsStarting,
					"1-3");
		}
		else if (arena.timer == 2)
		{
			for (Player pl : arena.playersInArena)
			{
				pl.playSound(pl.getLocation(),
						Sound.ORB_PICKUP, 1, 1);
			}
			ArenaHandler.sendFMessage(arena,
					ConfigC.normal_lobbyArenaIsStarting,
					"1-2");
		}
		else if (arena.timer == 1)
		{
			if(arena.killScore != null)
			{
				arena.killScore.clear();
			}

			for (Player pl : arena.playersInArena)
			{
				pl.playSound(pl.getLocation(),
						Sound.ORB_PICKUP, 1, 2);

				// Init the Nyan cooldown
				if (arena.nyanCooldown != null)
				{
					arena.nyanCooldown.put(
							pl,
							(Integer) W.config
									.get(ConfigC.nyanCooldown));
				}

				// Init the player's The World cooldown
				if (arena.theWorldCooldown != null)
				{
					arena.theWorldCooldown.put(
							pl, 0);
				}
				
				if(arena.killScore != null)
				{
					arena.killScore.put(pl, 0);
				}

				arena.theWorldUser.put(pl, false);
				arena.theWorldTime.put(pl, 0.0f);
			}

			try
			{
				WorldPerformer.randomTheArena(arena.pos1.getWorld(), arena);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			ArenaHandler.sendFMessage(arena,
					ConfigC.normal_lobbyArenaIsStarting,
					"1-1");
		}
	}

	@SuppressWarnings("deprecation")
	private void processArenaStarting(Arena arena)
	{
		arena.gameState = ArenaState.INGAME;
		arena.timer = arena.gameTime;
		ArenaHandler.sendFMessage(arena,
				ConfigC.normal_lobbyArenaStarted, "secs-"
						+ arena.waitingTimeSeeker);

		for (int i = arena.amountSeekersOnStart; i > 0; i = i - 1)
		{
			boolean loop = true;
			//Player seeker = arena.playersInArena
			//		.get(W.random
			//				.nextInt(arena.playersInArena
			//						.size()));
			Player seeker = arena.getRandomPlayer();

			for (Player playerCheck : arena.playersInArena)
			{
				if (W.choosenSeeker.get(playerCheck) != null)
				{
					if (W.choosenSeeker.get(playerCheck) == true)
					{
						seeker = playerCheck;
						W.choosenSeeker.remove(playerCheck);
					}
					else
					{
						if (seeker.equals(playerCheck))
						{
							i = i + 1;
							loop = false;
						}
					}
				}
			}

			if (loop)
			{
				if (!arena.seekers.contains(seeker))
				{
					ArenaHandler
							.sendFMessage(
									arena,
									ConfigC.normal_ingameSeekerChoosen,
									"seeker-"
											+ seeker.getName());
					arena.seekers.add(seeker);
					seeker.teleport(arena.seekersWarp);
					seeker.getInventory().clear();
					seeker.updateInventory();
					W.seekertime.put(seeker,
							arena.waitingTimeSeeker);
				}
				else
				{
					i = i + 1;
				}
			}
		}

		for (Player arenaPlayer : arena.playersInArena)
		{
			if (!arena.seekers.contains(arenaPlayer))
			{
				arenaPlayer.getInventory().clear();
				arenaPlayer.updateInventory();
				ItemStack block = arena.disguiseBlocks.get(W.random
						.nextInt(arena.disguiseBlocks
								.size()));

				if (W.choosenBlock.get(arenaPlayer) != null)
				{
					block = W.choosenBlock.get(arenaPlayer);
					W.choosenBlock.remove(arenaPlayer);
				}

				if (DisguiseDelegate.GetSingleton().IsMobDisguise(block))
				{
//					MobDisguise mobDisguise = new MobDisguise(
//							Common.GetDisguiseType(block),
//							true,
//							true);
//					DisguiseAPI.disguiseToAll(arenaPlayer,
//							mobDisguise);
					DisguiseDelegate.GetSingleton().Disguise(arenaPlayer,
							DisguiseDelegate.DISGUISE_TYPE.TYPE_MOB,
							block);
				}
				else
				{
//					MiscDisguise disguise = new MiscDisguise(
//							DisguiseType.FALLING_BLOCK,
//							block.getTypeId(), block
//									.getDurability());
//					DisguiseAPI.disguiseToAll(arenaPlayer,
//							disguise);
					DisguiseDelegate.GetSingleton().Disguise(arenaPlayer,
							DisguiseDelegate.DISGUISE_TYPE.TYPE_FALLING_BLOCK,
							block);
				}

				arenaPlayer.teleport(arena.hidersWarp);

				ItemStack blockCount = new ItemStack(block
						.getType(), 5);
				blockCount.setDurability(block
						.getDurability());
				arenaPlayer.getInventory().setItem(8,
						blockCount);
				arenaPlayer.getInventory().setHelmet(
						new ItemStack(block));
				W.pBlock.put(arenaPlayer, block);

				if (block.getDurability() != 0)
				{
					MessageM.sendFMessage(
							arenaPlayer,
							ConfigC.normal_ingameBlock,
							"block-"
									+ block.getType()
											.name()
											.replaceAll(
													"_", "")
											.replaceAll(
													"BLOCK",
													"")
											.toLowerCase()
									+ ":"
									+ block.getDurability());
				}
				else
				{
					MessageM.sendFMessage(
							arenaPlayer,
							ConfigC.normal_ingameBlock,
							"block-"
									+ block.getType()
											.name()
											.replaceAll(
													"_", "")
											.replaceAll(
													"BLOCK",
													"")
											.toLowerCase());
				}
			}
		}
	}

	private void processArenaSeekers(Arena arena)
	{
		for (Player player : arena.seekers)
		{
			if (player.getInventory().getItem(0) == null
					|| player.getInventory().getItem(0).getType() != Common.SeekerWeapon)
			{
				player.getInventory().setItem(0,
						new ItemStack(Common.SeekerWeapon, 1));
				player.getInventory().setItem(1,
						new ItemStack(Material.BOW, 1));
				player.getInventory().setItem(2,
						new ItemStack(Material.ARROW,
								(int) W.config.get(ConfigC.seekerArrowNumber)));

				if(W.TheStandMode)
				{
					player.getInventory().setItem(3,
							new ItemStack(Material.WATCH, 1));
				}

				player.getInventory().setHelmet(
						new ItemStack(Material.IRON_HELMET, 1));
				player.getInventory().setChestplate(
						new ItemStack(Material.DIAMOND_CHESTPLATE,
								1));
				player.getInventory().setLeggings(
						new ItemStack(Material.IRON_LEGGINGS, 1));
				player.getInventory().setBoots(
						new ItemStack(Material.IRON_BOOTS, 1));
				player.playSound(player.getLocation(),
						Sound.ANVIL_USE, 1, 1);
			}

			if (W.seekertime.get(player) != null)
			{
				W.seekertime.put(player,
						W.seekertime.get(player) - 1);
				if (W.seekertime.get(player) <= 0)
				{
					player.teleport(arena.hidersWarp);
					W.seekertime.remove(player);
					ArenaHandler.sendFMessage(arena,
							ConfigC.normal_ingameSeekerSpawned,
							"playername-" + player.getName());
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void processArenaInGame(Arena arena)
	{
		arena.timer = arena.timer - 1;
		if (arena.timer > 0)
		{
			if (arena.timer == arena.gameTime
					- arena.timeUntilHidersSword)
			{
				ItemStack sword = new ItemStack(
						Material.WOOD_SWORD, 1);
				sword.addUnsafeEnchantment(
						Enchantment.KNOCKBACK, 1);
				ItemStack bow = new ItemStack(Material.BOW, 1);
				bow.addUnsafeEnchantment(
						Enchantment.ARROW_DAMAGE,
						(int) W.config
								.get(ConfigC.arrowDamageLevel));
				ItemStack arrow = new ItemStack(Material.ARROW,
						(int) W.config.get(ConfigC.arrowNumber));
				ItemStack sugar = new ItemStack(Material.SUGAR,
						1);
				ItemStack firework = new ItemStack(
						Material.FIREWORK, 5);
				FireworkMeta fm = (FireworkMeta) firework
						.getItemMeta();
				fm.addEffect(FireworkEffect.builder()
						.flicker(true).trail(true)
						.with(FireworkEffect.Type.BALL_LARGE)
						.withColor(Color.YELLOW).build());
				fm.setPower(3);
				firework.setItemMeta(fm);
				for (Player arenaPlayer : arena.playersInArena)
				{
					if (!arena.seekers.contains(arenaPlayer))
					{
						arenaPlayer.getInventory().addItem(
								sword);
						arenaPlayer.getInventory().addItem(bow);
						arenaPlayer.getInventory().addItem(
								arrow);
						arenaPlayer.getInventory().addItem(
								sugar);
						arenaPlayer.getInventory().addItem(
								firework);

						if(W.TheStandMode)
						{
							arenaPlayer.getInventory().addItem( new ItemStack(Material.WATCH, 1) );
						}

						arenaPlayer.setFoodLevel(10);
						MessageM.sendFMessage(arenaPlayer,
								ConfigC.normal_ingameGivenSword);
					}
				}
			}
			if (arena.timer == 190)
			{
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-190");
			}
			else if (arena.timer == 60)
			{
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-60");
			}
			else if (arena.timer == 30)
			{
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-30");
			}
			else if (arena.timer == 10)
			{
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-10");
			}
			else if (arena.timer == 5)
			{
				arena.lobbyWarp.getWorld()
						.playSound(arena.lobbyWarp,
								Sound.ORB_PICKUP, 1, 0);
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-5");
			}
			else if (arena.timer == 4)
			{
				arena.lobbyWarp.getWorld()
						.playSound(arena.lobbyWarp,
								Sound.ORB_PICKUP, 1, 0);
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-4");
			}
			else if (arena.timer == 3)
			{
				arena.lobbyWarp.getWorld()
						.playSound(arena.lobbyWarp,
								Sound.ORB_PICKUP, 1, 1);
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-3");
			}
			else if (arena.timer == 2)
			{
				arena.lobbyWarp.getWorld()
						.playSound(arena.lobbyWarp,
								Sound.ORB_PICKUP, 1, 1);
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-2");
			}
			else if (arena.timer == 1)
			{
				arena.lobbyWarp.getWorld()
						.playSound(arena.lobbyWarp,
								Sound.ORB_PICKUP, 1, 2);
				ArenaHandler.sendFMessage(arena,
						ConfigC.normal_ingameArenaEnd, "1-1");
			}
		}
		else
		{
			ArenaHandler.hidersWin(arena);
			return;
		}

		for (Player player : arena.playersInArena)
		{
			// cooldown the Nyan
			if (arena.nyanCooldown != null)
			{
				if (arena.nyanCooldown.containsKey(player))
				{
					int cd = arena.nyanCooldown.get(player);
					cd--;
					if (cd < 0)
					{
						cd = 0;
					}
					arena.nyanCooldown.put(player, cd);
				}
			}
			
			// cooldown The World
			if(arena.theWorldCooldown != null)
			{
				if(arena.theWorldCooldown.containsKey(player))
				{
					int cd = arena.theWorldCooldown.get(player);
					cd--;
					if (cd < 0)
					{
						cd = 0;
					}
					arena.theWorldCooldown.put(player, cd);
				}
			}

			// cooldown seeker's sword
			if (arena.seekers.contains(player))
			{
				float exp = player.getExp();
				exp += Common.SWORD_COOLDOWN_PER_SEC;
				if (exp >= 1.0f)
				{
					exp = 0.9f;
				}
				player.setExp(exp);
			}

			if (!arena.seekers.contains(player))
			{
				Location pLoc = player.getLocation();
				Location moveLoc = W.moveLoc.get(player);
				ItemStack block = player.getInventory()
						.getItem(8);

				if (block == null)
				{
					if (W.pBlock.get(player) != null)
					{
						block = W.pBlock.get(player);
						player.getInventory().setItem(8, block);
						player.updateInventory();
					}
					else
					{
						MessageM.broadcastMessage("Init block error,please contact the authour");
					}
				}

				if (moveLoc != null
						&& !DisguiseDelegate.GetSingleton().IsMobDisguise(block))
				{
					if (moveLoc.getX() == pLoc.getX()
							&& moveLoc.getY() == pLoc.getY()
							&& moveLoc.getZ() == pLoc.getZ())
					{
						if (block.getAmount() > 1)
						{
							block.setAmount(block.getAmount() - 1);
						}
						else
						{
							Block pBlock = player.getLocation()
									.getBlock();
							if (pBlock.getType().equals(
									Material.AIR)
									|| pBlock.getType().equals(
											Material.WATER)
									|| pBlock
											.getType()
											.equals(Material.STATIONARY_WATER))
							{
								if (pBlock.getType().equals(
										Material.WATER)
										|| pBlock
												.getType()
												.equals(Material.STATIONARY_WATER))
								{
									W.hiddenLocWater.put(
											player, true);
								}
								else
								{
									W.hiddenLocWater.put(
											player, false);
								}

								//if (DisguiseAPI.isDisguised(player))
								if (DisguiseDelegate.GetSingleton().IsDisguised(player))
								{
									//DisguiseAPI.undisguiseToAll(player);
									DisguiseDelegate.GetSingleton().UnDisguise(player);
									for (Player pl : Bukkit
											.getOnlinePlayers())
									{
										if (!pl.equals(player))
										{
											pl.hidePlayer(player);
											pl.sendBlockChange(
													pBlock.getLocation(),
													block.getType(),
													(byte) block
															.getDurability());
										}
									}

									block.addUnsafeEnchantment(
											Enchantment.DURABILITY,
											10);
									player.playSound(pLoc,
											Sound.ORB_PICKUP,
											1, 1);
									W.hiddenLoc.put(player,
											moveLoc);
									if (block.getDurability() != 0)
									{
										MessageM.sendFMessage(
												player,
												ConfigC.normal_ingameNowSolid,
												"block-"
														+ block.getType()
																.name()
																.replaceAll(
																		"_",
																		"")
																.replaceAll(
																		"BLOCK",
																		"")
																.toLowerCase()
														+ ":"
														+ block.getDurability());
									}
									else
									{
										MessageM.sendFMessage(
												player,
												ConfigC.normal_ingameNowSolid,
												"block-"
														+ block.getType()
																.name()
																.replaceAll(
																		"_",
																		"")
																.replaceAll(
																		"BLOCK",
																		"")
																.toLowerCase());
									}
								}
								for (Player pl : Bukkit
										.getOnlinePlayers())
								{
									if (!pl.equals(player))
									{
										pl.hidePlayer(player);
										pl.sendBlockChange(
												pBlock.getLocation(),
												block.getType(),
												(byte) block
														.getDurability());
									}
								}
							}
							else
							{
								MessageM.sendFMessage(
										player,
										ConfigC.warning_ingameNoSolidPlace);
							}
						}
					}
					else
					{
						if (block != null)
						{
							block.setAmount(5);
						}
						//if (!DisguiseAPI.isDisguised(player))
						if (!DisguiseDelegate.GetSingleton().IsDisguised(player))
						{
							SolidBlockHandler
									.makePlayerUnsolid(player);
						}
					}
				}
			}
		}
	}

	private void theWorldLoop()
	{
		if(W.TheStandMode)
		{
			for (Arena arena : W.arenaList)
			{
				if (arena.gameState == ArenaState.INGAME)
				{
					for (Player player : arena.playersInArena)
					{
						// cooldown The World time
						if (arena.theWorldTime != null)
						{
							if (arena.theWorldTime.containsKey(player))
							{
								float time = arena.theWorldTime.get(player);
								time -= 0.5f;
								if (time < 0.0f)
								{
									time = 0.0f;
								}
								arena.theWorldTime.put(player, time);
							}
						}
					}
				}
			}
		}
	}
}
