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

public class EffectMessage
extends Effect {
	
	static {
		Skript.registerEffect(EffectMessage.class, (String[])new String[]{
				"send redis message %string% over channel %string%"});
	}
	
    private Expression<String> message;
    private Expression<String> channel;

    @SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] ex, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        this.message = (Expression<String>) ex[0];
        this.channel = (Expression<String>) ex[1];
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "send redis message %string% over channel %string%";
    }

    protected void execute(Event e) {
        Main.sendMessage((String)this.channel.getSingle(e), (String)this.message.getSingle(e));
    }
}

