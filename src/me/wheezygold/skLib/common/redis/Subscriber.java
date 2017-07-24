/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Server
 *  org.bukkit.event.Event
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package me.wheezygold.skLib.common.redis;

import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

import me.wheezygold.skLib.Main;
import redis.clients.jedis.JedisPubSub;

public class Subscriber
extends JedisPubSub {
    @Override
    public void onMessage(final String channel, final String msg) {
        new BukkitRunnable(){

            public void run() {
                Main.plugin.getServer().getPluginManager().callEvent((Event)new RedisReceiveEvent(channel, msg));
            }
        }.runTask(Main.plugin);
    }

    @Override
    public void onPMessage(String s, String s2, String s3) {
    }

    @Override
    public void onSubscribe(String s, int i) {
    }

    @Override
    public void onUnsubscribe(String s, int i) {
    }

    @Override
    public void onPUnsubscribe(String s, int i) {
    }

    @Override
    public void onPSubscribe(String s, int i) {
    }

}

