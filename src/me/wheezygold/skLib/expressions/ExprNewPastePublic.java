package me.wheezygold.skLib.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.nrubin29.pastebinapi.PastebinException;
import me.wheezygold.skLib.common.publicPaster;
import java.io.IOException;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class ExprNewPastePublic extends SimpleExpression<String> {

	static {
		Skript.registerExpression(
				ExprNewPastePublic.class, 
				String.class, 
				ExpressionType.PROPERTY, 
				"[a] [new] public paste[ ][bin] [with] title[d] %string% [with] (text|body|contents) %string%");
	}

  private Expression<String> title;
  private Expression<String> body;

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
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    title = (Expression<String>) exp[0];
    body = (Expression<String>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  @Nullable
  protected String[] get(Event evt) {
    try {
        return new String[] {publicPaster.createPaste(title.getSingle(evt), body.getSingle(evt))};
    } catch (PastebinException | IOException e) {
        e.printStackTrace();
        return null;
    }
  }
}