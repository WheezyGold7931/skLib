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

public class ExprCreateQrCode extends SimpleExpression<String> {
	
	static {
		Skript.registerExpression(
				ExprCreateQrCode.class, String.class, ExpressionType.SIMPLE, 
				"[the] QR( |-)Code [url] (for|of) the (two|2)[( |-)]factor [secret] code %string% with [account] name %string%");
	}
	
	 private Expression<String> code;
	 private Expression<String> name;
	
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
		code = (Expression<String>) arg0[0];
		name = (Expression<String>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "the QR-Code of the two-factor secret code %string% with account name %string%";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		return new String[] {TimeBasedOneTimePasswordUtil.qrImageUrl(name.getSingle(arg0), code.getSingle(arg0))};
	}

}
