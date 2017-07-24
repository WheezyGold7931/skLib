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
public class EffCreateScrollSB extends Effect {
	
	static {
		Skript.registerEffect(EffCreateScrollSB.class, 
			"[sk[-]Lib] create a (magic|scroll(ing|able)) scoreboard [with] title[d] %string% for %player%");
	}
	
	private Expression<String> title;
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		title = (Expression<String>) arg0[0];
		player = (Expression<Player>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Player p = player.getSingle(evt);
		SbManager.createscrollsb(p, title.getSingle(evt));
	}

}
