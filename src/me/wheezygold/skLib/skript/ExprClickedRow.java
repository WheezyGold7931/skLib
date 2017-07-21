package me.wheezygold.skLib.skript;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprClickedRow extends SimpleExpression<Number> {
	
	static {
		Skript.registerExpression(
				ExprClickedRow.class, Number.class, ExpressionType.SIMPLE, 
				"[sk[-]Lib][ ]clicked row");
	}

	@Override
	public Class<? extends Number> getReturnType() {
		// TODO Auto-generated method stub
		return Number.class;
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
		return "clicked row";
	}

	public static boolean rowType(Integer row, Integer slot, Inventory inventory) {
		if (slot != null && row != null) {
			Integer mod = 9;
			if (inventory != null) {
				if (inventory.getType() == InventoryType.DISPENSER ||inventory.getType() == InventoryType.WORKBENCH || inventory.getType() == InventoryType.DROPPER) {
					mod = 3;
				} else if (inventory.getType() == InventoryType.CHEST || inventory.getType() == InventoryType.ENDER_CHEST || inventory.getType() == InventoryType.PLAYER){
					mod = 9;
				} else {
					mod = inventory.getSize();
				}
			}
			Integer calculate = row * mod;
			return slot >= calculate - mod && slot < calculate;
		}
		return false;
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		InventoryType container = ((InventoryClickEvent)e).getClickedInventory().getType();
		if (container != InventoryType.CHEST || container != InventoryType.DISPENSER || container != InventoryType.DROPPER || container != InventoryType.WORKBENCH || container != InventoryType.ENDER_CHEST || container != InventoryType.PLAYER) {
			for (int i = 1; i < ((InventoryClickEvent)e).getClickedInventory().getSize() + 1; i++) {
				if (rowType(i, ((InventoryClickEvent)e).getSlot(), ((InventoryClickEvent)e).getInventory())) {
					return new Number[]{i};
				}
			}
		}
		return null;
	}
}
