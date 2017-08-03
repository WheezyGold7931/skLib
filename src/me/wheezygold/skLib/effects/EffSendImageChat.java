package me.wheezygold.skLib.skript;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.bobacadodl.imgmessage.ImageChar;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSendImageChat extends Effect {
	
	static {
		Skript.registerEffect(EffSendImageChat.class, 
			"send image [from url][ ]%string% to [the player][ ]%player%");
	}
	
	private Expression<String> url;
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		url = (Expression<String>) arg0[0];
		player = (Expression<Player>) arg0[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "send image [from url][ ]%string% to [the player][ ]%player%";
	}

	@Override
	protected void execute(Event arg0) {
		try {
			BufferedImage imageToSend = ImageIO.read(new URL(url.getSingle(arg0)));
			new com.bobacadodl.imgmessage.ImageMessage(imageToSend, 8, ImageChar.DARK_SHADE.getChar()).sendToPlayer(player.getSingle(arg0).getPlayer());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
