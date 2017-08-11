package me.wheezygold.skLib.WIP;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.scoreboard.SbManager;

@SuppressWarnings("unused")
public class EffSetScoreSB extends Effect {
	
	static {
		Skript.registerEffect(EffSetScoreSB.class, 
				"[sk[-]Lib] set [the] score at %number% of %player%[']s s[core][ ]b[oard] to %string%");
	}
	
	private Expression<Integer> slot;
	private Expression<Player> player;
	private Expression<String> value;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		slot = (Expression<Integer>) arg0[0];
		player = (Expression<Player>) arg0[1];
		value = (Expression<String>) arg0[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return "set score at " + slot.getSingle(arg0) + " of " + player.getSingle(arg0) + " score board to " + value.getSingle(arg0);
	}

	@Override
	protected void execute(Event evt) {
		if((slot.getSingle(evt) == null) || (player.getSingle(evt) == null) || (value.getSingle(evt) == null)){
			 			Skript.error("Must provide all values, refer to syntax.");
			 			
			 		}else{
			 			int slotnum = Integer.valueOf(slot.toString());
			 			SbManager.setscoresb(player.getSingle(evt), slotnum, value.getSingle(evt));
			 		}
		
	}

}
