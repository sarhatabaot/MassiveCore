package com.massivecraft.massivecore.chestgui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ChestActionToggle extends ChestActionAbstract implements ChestButton
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //

	private final ItemStack enabledItem;
	public ItemStack getEnabledItem() { return this.enabledItem; }

	private final ItemStack disabledItem;
	public ItemStack getDisabledItem() { return this.disabledItem; }

	private final ChestAction enableAction;
	public ChestAction getEnableAction() { return this.enableAction; }

	private final ChestAction disableAction;
	public ChestAction getDisableAction() { return this.disableAction; }

	private boolean enabled;

	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //

	public ChestActionToggle(ItemStack enabledItem, ItemStack disabledItem, ChestAction enableAction, ChestAction disableAction, boolean enabled)
	{
		this.enabledItem = enabledItem;
		this.disabledItem = disabledItem;
		this.enableAction = enableAction;
		this.disableAction = disableAction;

		this.enabled = enabled;
	}

	// -------------------------------------------- //
	// OVERRIDE: CHEST ACTION
	// -------------------------------------------- //

	@Override
	public boolean onClick(InventoryClickEvent event, Player player)
	{
		// If it is enabled then use the disable action
		// If it is disabled use the enable action
		boolean ret;
		ChestAction inner = this.enabled ? this.getDisableAction() : this.getEnableAction();
		ret = inner.onClick(event);

		this.enabled = !this.enabled;

		return ret;
	}

	// This method is run /before/ onClick
	// so this.enabled has not yet changed value
	@Override
	public ItemStack transformItem(ItemStack clickedItem)
	{
		// Currently enabled means it will be disabled, so show the disable item and vice versa
		return this.enabled ? this.getDisabledItem() : this.getEnabledItem();
	}

	// -------------------------------------------- //
	// OVERRIDE: CHEST BUTTON
	// -------------------------------------------- //

	@Override
	public ChestAction getAction()
	{
		return this;
	}

	@Override
	public ItemStack getItem()
	{
		return this.enabled ? this.getEnabledItem() : this.getDisabledItem();
	}

}
