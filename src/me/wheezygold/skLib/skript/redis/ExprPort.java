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
package me.wheezygold.skLib.skript.redis;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.Main;

import javax.annotation.Nullable;
import org.bukkit.event.Event;

public class ExprPort
extends SimpleExpression<Integer> {
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "redis port";
    }

    @Nullable
    protected Integer[] get(Event e) {
        return new Integer[]{Main.port};
    }
}

