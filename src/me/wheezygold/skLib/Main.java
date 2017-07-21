package me.wheezygold.skLib;

import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.wheezygold.metrics.Metrics;
import me.wheezygold.skLib.WIP.EffCreateScrollSB;
import me.wheezygold.skLib.common.Util;

public class Main extends JavaPlugin implements Listener {
	
	public void loadConfiguration() {
		String _kickpath = "values.pastebin.apikey";
		getConfig().addDefault(_kickpath, "api-key-here");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private static Main instance;

	@Override
	public void onEnable() {
		Util.sendCMsg("Creating Instance...");
		instance = this;
		Util.sendCMsg("Instance has been Created!");
		Util.sendCMsg("Loading Configuration...");
		loadConfiguration();
		reloadConfig();
		Util.sendCMsg("Loaded Configuration...(most likely)");
		Util.sendCMsg("Registering Events...");
		Bukkit.getPluginManager().registerEvents(this, this);
		Util.sendCMsg("Registered Events!");
		Util.sendCMsg("Loading Metrics...");
		Metrics metrics = new Metrics(this);
		metrics.addCustomChart(new Metrics.SimplePie("skript_version") {
            @Override
            public String getValue() {
                return Bukkit.getServer().getPluginManager().getPlugin("Skript").getDescription().getVersion();
            }
        });
		metrics.addCustomChart(new Metrics.SimplePie("plugin_version") {
            @Override
            public String getValue() {
                return getInstance().getDescription().getVersion();
            }
        });
		Util.sendCMsg("Loaded Metrics!");
		if (getServer().getPluginManager().getPlugin("Skript")!=null) {
			Util.sendCMsg("Skript has been found!");
			Util.sendCMsg("Registing Addon...");
			SkriptAddon sk = Skript.registerAddon(this);
			Util.sendCMsg("Registered Addon!");
			if (Skript.isAcceptRegistrations()) {
				Util.sendCMsg("Looks like Skript is looking for syntax so lets throw some shit at it...");
				Skript.registerEffect(EffCreateScrollSB.class, "create a magic scoreboard with title %string% for %player%");
				Util.sendCMsg("Going to start to load the syntax...");
				try {
					sk.loadClasses("me.wheezygold.skLib", "skript");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (Bukkit.getBukkitVersion().contains("1.8")) {
					Util.sendCMsg("Looks like you are running MC 1.8...Loading the 1.8 Skript syntax...");
					try {
						sk.loadClasses("me.wheezygold.skLib.skript", "V1_8");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				Util.sendCMsg("Loaded all of the Skript syntax!");
			} else {
				Util.sendCMsg("Skript is not looking to accept syntax/registrations. Did you restart the server?");
			}
		} else {
			Util.sendCMsg("Skript has not been found, you idiot what do you think a skript addon is, so expect nothing to register.");
		}
		Util.sendCMsg("skLib has been loaded. Enjoy!");
	}
	
	@Override
	public void onDisable() {
		Util.sendCMsg("skLib has been disabled! o/ Cya next time.");
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	 String[] args = null;

	 @Override
	    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	        if (command.getName().equalsIgnoreCase("skLib")) {
	        	final Player p = (Player) sender;
	        	Util.sendMsg(p, "Reloaded the config!");
		        reloadConfig();
		        return true;
	        }
			return false;
	 }
}
