/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  ch.njol.skript.ScriptLoader
 *  ch.njol.skript.Skript
 *  ch.njol.skript.lang.Expression
 *  ch.njol.skript.lang.SkriptParser
 *  ch.njol.skript.lang.SkriptParser$ParseResult
 *  ch.njol.skript.lang.util.SimpleExpression
 *  ch.njol.util.Kleenean
 *  javax.annotation.Nullable
 *  org.bukkit.event.Event
 */
package me.wheezygold.skLib.skript.redis;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import javax.annotation.Nullable;
import org.bukkit.event.Event;

public class ExprMsg
extends SimpleExpression<String> {
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(RedisReceiveEvent.class)) {
            Skript.error((String)"Expression 'redis message' can only be used inside a 'redis msg' event!");
            return false;
        }
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "redis message";
    }

    @Nullable
    protected String[] get(Event e) {
        RedisReceiveEvent event = (RedisReceiveEvent)e;
        return new String[]{event.getMsg()};
    }
}

