package me.wheezygold.skLib.common;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.wheezygold.skLib.Main;
import net.minecraft.server.v1_8_R3.EntityEnderDragon;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;

public class Bar {
	
	public static void newBar(Player player, String text) {
		Location loc = player.getLocation();
		WorldServer world = ((CraftWorld) player.getLocation().getWorld()).getHandle();
		
		EntityEnderDragon dragon = new EntityEnderDragon(world);
		dragon.setLocation(loc.getX() - 30, loc.getY() - 100, loc.getZ(), 0, 0);
		dragon.setCustomName(text);
		NBTTagCompound tag = dragon.getNBTTag();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		dragon.c(tag);
		tag.setInt("NoAI", 1);
		dragon.f(tag);
		dragon.setInvisible(true);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			 
		    @Override
		    public void run() {
		    	Location loc = player.getLocation();
		    	dragon.setLocation(loc.getX() - 30, loc.getY() - 100, loc.getZ(), 0, 0);
		    	Packet<?> packet = new PacketPlayOutSpawnEntityLiving(dragon);
				((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		    }
		 
		}, 10);
		
		Packet<?> packet = new PacketPlayOutSpawnEntityLiving(dragon);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

}
