package me.wheezygold.skLib.skript;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprCommandPlugin extends SimpleExpression<String> {
	
	static {
		Skript.registerExpression(
				ExprCommandPlugin.class, String.class, ExpressionType.SIMPLE, 
				"[the] plugin (holding|of) [command] %string%");
	}
	
	private Expression<String> commandsk;

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		commandsk = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[the] name of [the] plugin (holding|of) [command] %string%";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String cmd = commandsk.getSingle(arg0);
		String commandStr = cmd.startsWith("/") ? cmd.substring(1) : cmd;
		PluginCommand command = Bukkit.getServer().getPluginCommand(commandStr);
		if (command != null) {
			return new String[] {command.getPlugin().getName()};
		}
		Skript.error("Command not found!");
		return null;
	}

}
