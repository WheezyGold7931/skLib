package me.wheezygold.skLib.skript;

import java.io.IOException;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.common.IpTools;
import me.wheezygold.skLib.common.JsonReader;

public class CondVpnCheck extends Condition {

	static {
		Skript.registerCondition(CondVpnCheck.class, 
				"%string% is [a] (proxy|vpn)");
	}
	
	private Expression<String> ip;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		ip = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return ip.getSingle(arg0) + " is a vpn";
	}

	@Override
	public boolean check(Event arg0) {
		@SuppressWarnings("unused")
		Boolean goodip = IpTools.validIP(ip.getSingle(arg0));
		if (!goodip) {
			Skript.error("\"" + ip + "\" is not a valid IPv4 address!");
			return false;
		}
		JSONObject jsonparsed = null;
		try {
			jsonparsed = JsonReader.readJsonFromUrl("http://proxycheck.io/v1/" + ip.getSingle(arg0) + "&vpn=1&asn=0&node=1&time=1&tag=skLib");
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		String jsonvalue = (String) jsonparsed.get("proxy");
		if (jsonvalue.equalsIgnoreCase("yes")) {
			return true;
		}
		return false;
	}

}
