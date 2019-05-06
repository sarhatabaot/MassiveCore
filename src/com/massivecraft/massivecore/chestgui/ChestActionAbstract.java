package com.massivecraft.massivecore.chestgui;

import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestActionAbstract implements ChestAction
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean onClick(InventoryClickEvent event)
	{
		Player player = IdUtil.getAsPlayer(event.getWhoClicked());
		if (MUtil.isntPlayer(player)) return false;

		// Variables
		Inventory inventory = event.getInventory();
		ChestGui gui = ChestGui.get(inventory);
		int slot = event.getSlot();

		// Transform item
		ItemStack itemBefore = event.getCurrentItem();
		ItemStack itemAfter = transformItem(itemBefore);

		if (itemAfter != null)
		{
			inventory.setItem(slot, itemAfter);
		}

		// Transform action
		ChestAction actionAfter = transformAction();
		if (actionAfter != null)
		{
			gui.setAction(slot, actionAfter);
		}

		return onClick(event, player);
	}
	
	public boolean onClick(InventoryClickEvent event, Player player)
	{
		return false;
	}

	public ItemStack transformItem(ItemStack clickedItem)
	{
		return null;
	}

	public ChestAction transformAction()
	{
		return null;
	}
	
}
