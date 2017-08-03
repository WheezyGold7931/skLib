package me.wheezygold.skLib.skript;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprClickedItem extends SimpleExpression<ItemType>{
	
	static {
		Skript.registerExpression(
				ExprClickedItem.class, ItemType.class, ExpressionType.SIMPLE, 
				"[sk[-]Lib][ ]clicked item");
	}

	@Override
	public Class<? extends ItemType> getReturnType() {
		return ItemType.class;
	}

	@Override
	public boolean isSingle() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		if (!ScriptLoader.isCurrentEvent(InventoryClickEvent.class)) {
			Skript.error("[SkLib] Hey idiot! You cannot use the clicked row expression in this event.");
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return "clicked item";
	}

	@Override
	@Nullable
	protected ItemType[] get(Event e) {
		if (((InventoryClickEvent)e).getCurrentItem() != null) {
			return new ItemType[]{new ItemType(((InventoryClickEvent)e).getCurrentItem())};
		}
		return null;
	}
	
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (mode == ChangeMode.SET) {
			ItemType item = (ItemType)delta[0];
			ItemStack m = item.getRandom();
			((InventoryClickEvent)e).setCurrentItem(m);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == ChangeMode.SET) {
			return CollectionUtils.array(ItemType.class);
		}
		return null;
	}

}
