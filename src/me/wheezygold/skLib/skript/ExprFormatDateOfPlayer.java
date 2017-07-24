package me.wheezygold.skLib.skript;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.wheezygold.skLib.common.JsonReader;

public class ExprFormatDateOfPlayer extends SimpleExpression<String> {
	
	static {
		Skript.registerExpression(
				ExprFormatDateOfPlayer.class, String.class, ExpressionType.SIMPLE, 
				"[the] formatted date (of|for) [player] %player%");
	}
	
	private Expression<Player> player;

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
		player = (Expression<Player>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "the formatted date for player %player%";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		InetAddress rawip = player.getSingle(arg0).getAddress().getAddress();
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
		return new String[] {df.format(date)};
	}

}
