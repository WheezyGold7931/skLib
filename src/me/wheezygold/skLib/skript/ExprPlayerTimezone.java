package me.wheezygold.skLib.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.wheezygold.skLib.common.JsonReader;
import org.bukkit.entity.Player;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;

public class ExprPlayerTimezone extends SimplePropertyExpression<Object, String> {
	static {
		PropertyExpression.register(ExprPlayerTimezone.class, String.class, "timezone", "player");
	}
	@Override
	protected String getPropertyName() {
		return "timezone";
	}

	@Override
	public String convert(Object o) {
		if(o instanceof Player){
			InetAddress rawip = ((Player) o).getAddress().getAddress();
			String ip = rawip.toString().replaceAll("/", "");
			JSONObject jsonparsed = null;
			try {
				jsonparsed = JsonReader.readJsonFromUrl("http://freegeoip.net/json/" + ip);
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
			String jsonvalue = (String) jsonparsed.get("time_zone");
			return jsonvalue;
		}
		Skript.error("Incorrect provided argument, expected %player%");
		return null;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
