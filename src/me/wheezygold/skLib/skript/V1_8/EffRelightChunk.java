package me.wheezygold.skLib.skript.V1_8;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.Chunk;

public class EffRelightChunk extends Effect {
	
	static {
		Skript.registerEffect(EffRelightChunk.class, 
			"relight [chunk] %chunk%");
	}
	
	private Expression<Chunk> cnk;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		cnk = (Expression<Chunk>) arg0[0];
		return false;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "relight [chunk] %chunk%";
	}

	@Override
	protected void execute(Event arg0) {
		((Chunk) cnk).initLighting();
		
	}

}
