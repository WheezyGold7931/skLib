package me.wheezygold.skLib.skript;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprMD5Hash extends SimpleExpression<String> {
	
	static {
		Skript.registerExpression(
				ExprMD5Hash.class, String.class, ExpressionType.SIMPLE, 
				"[the] md5 hash of [string] %string%");
	}
	
	private Expression<String> string;

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
		string = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "the md5 hash of string %string%";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String s = string.getSingle(arg0);
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(),0,s.length());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new String[] {new BigInteger(1,m.digest()).toString(16)};
	}

}
