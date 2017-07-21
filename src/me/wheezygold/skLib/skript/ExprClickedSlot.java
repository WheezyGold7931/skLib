package me.wheezygold.skLib.skript;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprClickedSlot extends SimpleExpression<Number> {
	
	static {
		Skript.registerExpression(
				ExprClickedSlot.class, Number.class, ExpressionType.SIMPLE, 
				"[sk[-]Lib][ ]clicked slot");
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		if (!ScriptLoader.isCurrentEvent(InventoryClickEvent.class)) {
			Skript.error("[SkLib] Hey idiot! You cannot use the clicked row expression in this event.");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return "clicked slot";
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		return new Number[]{((InventoryClickEvent)e).getSlot()};
	}

}
