/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  ch.njol.skript.Skript
 *  ch.njol.skript.lang.ExpressionType
 *  ch.njol.skript.lang.util.SimpleEvent
 *  ch.njol.skript.registrations.EventValues
 *  ch.njol.skript.util.Getter
 *  org.bukkit.plugin.java.JavaPlugin
 */
package me.wheezygold.skLib.common.redis;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.wheezygold.skLib.skript.redis.EffectDelVariable;
import me.wheezygold.skLib.skript.redis.EffectDoEvent;
import me.wheezygold.skLib.skript.redis.EffectFlush;
import me.wheezygold.skLib.skript.redis.EffectMessage;
import me.wheezygold.skLib.skript.redis.EffectSetVariable;
import me.wheezygold.skLib.skript.redis.ExprChannel;
import me.wheezygold.skLib.skript.redis.ExprChannels;
import me.wheezygold.skLib.skript.redis.ExprConnected;
import me.wheezygold.skLib.skript.redis.ExprGetVariable;
import me.wheezygold.skLib.skript.redis.ExprIP;
import me.wheezygold.skLib.skript.redis.ExprMsg;
import me.wheezygold.skLib.skript.redis.ExprPort;

import org.bukkit.plugin.java.JavaPlugin;

public class RegisterSkript {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public RegisterSkript(JavaPlugin pl) {
        Skript.registerEffect(EffectMessage.class, (String[])new String[]{"send redis message %string% over channel %string%"});
        Skript.registerEffect(EffectDoEvent.class, (String[])new String[]{"do local redis event with message %string% over channel %string%"});
        Skript.registerEffect(EffectFlush.class, (String[])new String[]{"flush all redis variables"});
        Skript.registerEffect(EffectDelVariable.class, (String[])new String[]{"delete redis variable %string%"});
        Skript.registerEffect(EffectSetVariable.class, (String[])new String[]{"set redis variable %string% to %string%"});
        Skript.registerExpression(ExprConnected.class, Boolean.class, (ExpressionType)ExpressionType.SIMPLE, (String[])new String[]{"redis connected"});
        Skript.registerExpression(ExprIP.class, String.class, (ExpressionType)ExpressionType.SIMPLE, (String[])new String[]{"redis ip"});
        Skript.registerExpression(ExprPort.class, Integer.class, (ExpressionType)ExpressionType.SIMPLE, (String[])new String[]{"redis port"});
        Skript.registerExpression(ExprChannels.class, String.class, (ExpressionType)ExpressionType.SIMPLE, (String[])new String[]{"redis channels"});
        Skript.registerExpression(ExprGetVariable.class, String.class, (ExpressionType)ExpressionType.SIMPLE, (String[])new String[]{"redis variable %string%"});
        Skript.registerEvent((String)"Redis Receive Event", SimpleEvent.class, RedisReceiveEvent.class, (String[])new String[]{"redis msg"});
        EventValues.registerEventValue(RedisReceiveEvent.class, String.class, (Getter)new Getter<String, RedisReceiveEvent>(){

            public String get(RedisReceiveEvent e) {
                return e.getMsg();
            }
        }, (int)0);
        EventValues.registerEventValue(RedisReceiveEvent.class, String.class, (Getter)new Getter<String, RedisReceiveEvent>(){

            public String get(RedisReceiveEvent e) {
                return e.getChannel();
            }
        }, (int)0);
        Skript.registerExpression(ExprChannel.class, String.class, (ExpressionType)ExpressionType.SIMPLE, (String[])new String[]{"redis channel"});
        Skript.registerExpression(ExprMsg.class, String.class, (ExpressionType)ExpressionType.SIMPLE, (String[])new String[]{"redis message"});
    }

}

