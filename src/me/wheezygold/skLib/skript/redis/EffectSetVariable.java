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

public class EffectSetVariable
extends Effect {
    private Expression<String> key;
    private Expression<String> value;

    @SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.key = (Expression<String>) arg0[0];
        this.value = (Expression<String>) arg0[1];
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "set redis variable %string% to %string%";
    }

    protected void execute(final Event arg0) {
        final Jedis jedis = Main.pool.getResource();
        new BukkitRunnable(){

            public void run() {
                try {
                    jedis.set((String)EffectSetVariable.this.key.getSingle(arg0), (String)EffectSetVariable.this.value.getSingle(arg0));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                jedis.close();
            }
        }.runTaskAsynchronously(Main.plugin);
    }

}

