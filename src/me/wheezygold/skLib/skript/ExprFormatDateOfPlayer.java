package me.wheezygold.skLib.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import me.wheezygold.skLib.common.JsonReader;
import org.bukkit.entity.Player;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ExprFormatDateOfPlayer extends SimplePropertyExpression<Object, String>{
	static {
		PropertyExpression.register(ExprFormatDateOfPlayer.class, String.class, "formatted date", "player");
	}
	@Override
	protected String getPropertyName() {
		return "formatted date";
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
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			df.setTimeZone(TimeZone.getTimeZone(jsonvalue));
			return df.format(date);
		}
		Skript.error("Incorrect provided argument, expected %player%");
		return null;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
