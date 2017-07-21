package me.wheezygold.skLib.skript;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.common.TwoFactor;

public class ExprCurrentCode extends SimpleExpression<String>{
	
	static {
		Skript.registerExpression(
				ExprCurrentCode.class, String.class, ExpressionType.SIMPLE, 
				"the [current] (two|2)[( |-)]factor code of [secret code] %string%");
	}
	
	private Expression<String> secret;

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
		secret = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "the current two-factor code of secret code %string%";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		return new String[] {TwoFactor.getCode(secret.getSingle(arg0))};
	}

}
