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
package me.wheezygold.skLib.skriptredis;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.Main;
import javax.annotation.Nullable;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;
import redis.clients.jedis.Jedis;

public class EffectDelVariable
extends Effect {
	
	static {
		Skript.registerEffect(EffectDelVariable.class, (String[])new String[]{
				"delete redis variable %string%"});
	}
	
    private Expression<String> key;

    @SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.key = (Expression<String>) arg0[0];
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "delete redis variable %string%";
    }

    protected void execute(final Event arg0) {
        final Jedis jedis = Main.pool.getResource();
        new BukkitRunnable(){

            public void run() {
                try {
                    jedis.del((String)EffectDelVariable.this.key.getSingle(arg0));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                jedis.close();
            }
        }.runTaskAsynchronously(Main.plugin);
    }

}

