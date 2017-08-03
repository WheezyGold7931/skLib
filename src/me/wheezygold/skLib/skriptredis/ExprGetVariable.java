/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  ch.njol.skript.lang.Expression
 *  ch.njol.skript.lang.SkriptParser
 *  ch.njol.skript.lang.SkriptParser$ParseResult
 *  ch.njol.skript.lang.util.SimpleExpression
 *  ch.njol.util.Kleenean
 *  javax.annotation.Nullable
 *  org.bukkit.event.Event
 */
package me.wheezygold.skLib.skriptredis;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.Main;

import javax.annotation.Nullable;
import org.bukkit.event.Event;
import redis.clients.jedis.Jedis;

public class ExprGetVariable
extends SimpleExpression<String> {
	
	static {
		Skript.registerExpression(ExprGetVariable.class, String.class, 
				(ExpressionType)ExpressionType.SIMPLE, (String[])new String[]{"redis variable %string%"});
	}
	
    private Expression<String> key;

    public Class<? extends String> getReturnType() {
        return String.class;
    }

    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.key = (Expression<String>) arg0[0];
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "redis variable %string%";
    }

    @Nullable
    protected String[] get(Event arg0) {
        Jedis jedis = Main.pool.getResource();
        String value = jedis.get((String)this.key.getSingle(arg0));
        jedis.close();
        return new String[]{value};
    }
}

