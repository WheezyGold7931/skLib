package me.wheezygold.skLib.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class CondAlwaysFalse extends Condition {
	
	static {
		Skript.registerCondition(CondAlwaysFalse.class, 
				"false is true");
		Skript.registerCondition(CondAlwaysFalse.class, 
				"true is false");
		}
	
  public CondAlwaysFalse() {}
  
  public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult)
  {
    return true;
  }
  
  public boolean check(Event event)
  {
    return true;
  }

@Override
public String toString(@Nullable Event arg0, boolean arg1) {
	return null;
}

}
