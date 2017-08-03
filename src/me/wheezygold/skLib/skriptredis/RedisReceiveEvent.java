/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Event
 *  org.bukkit.event.HandlerList
 */
package me.wheezygold.skLib.skriptredis;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class RedisReceiveEvent
extends Event {
	
	static {
		Skript.registerEvent((String)"Redis Receive Event", SimpleEvent.class, RedisReceiveEvent.class, (String[])new String[]{"[on][ ]redis msg"});
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
	}
	
    private static final HandlerList handlers = new HandlerList();
    private String channel;
    private String msg;

    public RedisReceiveEvent(String ch, String ms) {
        this.channel = ch;
        this.msg = ms;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getMsg() {
        return this.msg;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

