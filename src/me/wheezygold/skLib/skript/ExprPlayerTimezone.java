package me.wheezygold.skLib.skript;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.wheezygold.skLib.common.JsonReader;
import org.bukkit.entity.Player;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;

public class ExprPlayerTimezone extends SimplePropertyExpression<Player, String> {
	static {
		PropertyExpression.register(ExprPlayerTimezone.class, String.class, "timezone", "players");
	}
	@Override
	protected String getPropertyName() {
		return "timezone";
	}

	@Override
	public String convert(Player o) {
		InetAddress rawIp = o.getAddress().getAddress();
		String ip = rawIp.toString().replaceAll("/", "");
		JSONObject jsonParsed = null;
		try {
			jsonParsed = JsonReader.readJsonFromUrl("http://freegeoip.net/json/" + ip);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		String jsonValue = (String) jsonParsed.get("time_zone");
		return jsonValue;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}