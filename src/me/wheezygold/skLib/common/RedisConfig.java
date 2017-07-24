package me.wheezygold.skLib.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class RedisConfig {

    private static YamlConfiguration redisConfig;
    private static File configFile;
    private static boolean loaded = false;

    public static YamlConfiguration getConfig() {
        if (!loaded) {
            loadConfig();
        }
        return redisConfig;
    }

    public static File getConfigFile() {
        return configFile;
    }
 
    public static void loadConfig() {
        configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("skLib").getDataFolder(), "redis.yml");
        if (configFile.exists()) {
        	redisConfig = new YamlConfiguration();
            try {
            	redisConfig.load(configFile);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
            	ex.printStackTrace();
            } catch (InvalidConfigurationException ex) {
            	ex.printStackTrace();
            }
            loaded = true;
        } else {
            try {
                InputStream jarURL = RedisConfig.class.getResourceAsStream("/redis.yml");
                copyFile(jarURL, configFile);
                redisConfig = new YamlConfiguration();
                redisConfig.load(configFile);
                loaded = true;
                Util.sendCMsg("The redis config has loaded!");
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    static private void copyFile(InputStream in, File out) throws Exception {
        InputStream fis = in;
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
 
    private RedisConfig() {
    }
}