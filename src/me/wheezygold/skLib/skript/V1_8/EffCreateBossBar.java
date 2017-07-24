package me.wheezygold.skLib.skript.V1_8;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.common.Bar;

public class EffCreateBossBar extends Effect {
	
	static {
		Skript.registerEffect(EffCreateBossBar.class, 
			"[sk[-]Lib] (show|display) bossbar to %player% [with] text %string%");
	}
	
	private Expression<Player> player;
	private Expression<String> message;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		player = (Expression<Player>) arg0[0];
		message = (Expression<String>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[sk[-]Lib] (show|display) bossbar to %player% [with] text %string%";
	}

	@Override
	protected void execute(Event arg0) {
		Bar.newBar(player.getSingle(arg0), message.getSingle(arg0));
		
	}

}
