/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Event
 *  org.bukkit.event.HandlerList
 */
package me.wheezygold.skLib.skript.redis;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RedisReceiveEvent
extends Event {
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

