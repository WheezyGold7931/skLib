/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  ch.njol.skript.lang.Effect
 *  ch.njol.skript.lang.Expression
 *  ch.njol.skript.lang.SkriptParser
 *  ch.njol.skript.lang.SkriptParser$ParseResult
 *  ch.njol.util.Kleenean
 *  javax.annotation.Nullable
 *  org.bukkit.event.Event
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package me.wheezygold.skLib.skript.redis;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.Main;

import javax.annotation.Nullable;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;
import redis.clients.jedis.Jedis;

public class EffectFlush
extends Effect {
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "flush all redis variables";
    }

    protected void execute(Event arg0) {
        final Jedis jedis = Main.pool.getResource();
        new BukkitRunnable(){

            public void run() {
                try {
                    jedis.flushAll();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                jedis.close();
            }
        }.runTaskAsynchronously(Main.plugin);
    }

}

