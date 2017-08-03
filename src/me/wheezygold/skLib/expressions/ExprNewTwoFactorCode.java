package me.wheezygold.skLib.skript;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprNewTwoFactorCode extends SimpleExpression<String> {
	
	static {
		Skript.registerExpression(
				ExprNewTwoFactorCode.class, String.class, ExpressionType.SIMPLE, 
				"[a] new (two|2)[( |-)]factor [base[( |-)]32] secret code");
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "a new two-factor base-32 code";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		return new String[] {TimeBasedOneTimePasswordUtil.generateBase32Secret()};
	}

}
