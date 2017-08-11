package me.wheezygold.skLib.skript;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
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

public class ExprFormatDateOfPlayer extends SimplePropertyExpression<Player, String>{
	static {
		PropertyExpression.register(ExprFormatDateOfPlayer.class, String.class, "formatted date", "players");
	}
	@Override
	protected String getPropertyName() {
		return "formatted date";
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
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.setTimeZone(TimeZone.getTimeZone(jsonValue));
		return df.format(date);
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
}