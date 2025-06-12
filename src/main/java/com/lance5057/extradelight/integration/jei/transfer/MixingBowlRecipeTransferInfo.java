package com.lance5057.extradelight.integration.jei.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lance5057.extradelight.ExtraDelightContainers;
import com.lance5057.extradelight.workstations.mixingbowl.MixingBowlMenu;
import com.lance5057.extradelight.workstations.mixingbowl.recipes.MixingBowlRecipe;
import com.lance5057.extradelight.integration.jei.categories.MixingBowlRecipeCategory;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;

public class MixingBowlRecipeTransferInfo implements IRecipeTransferInfo<MixingBowlMenu, MixingBowlRecipe> {

	@Override
	public Class<? extends MixingBowlMenu> getContainerClass() {
		return MixingBowlMenu.class;
	}

	@Override
	public Optional<MenuType<MixingBowlMenu>> getMenuType() {
		return Optional.of(ExtraDelightContainers.MIXING_BOWL_MENU.get());
	}

	@Override
	public RecipeType<MixingBowlRecipe> getRecipeType() {
		return MixingBowlRecipeCategory.TYPE;
	}

	@Override
	public boolean canHandle(MixingBowlMenu container, MixingBowlRecipe recipe) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(MixingBowlMenu container, MixingBowlRecipe recipe) {
		List<Slot> res = new ArrayList<>();
		var inv = container.slots;
		
		// 0-8: 3x3 crafting slot
		// 9: container slot
		int sz = recipe.getIngredients().size();
		for(int i=0;i<sz;i++) {
			res.add(inv.get(i));
		}
		if(!recipe.getUsedItem().isEmpty())
			res.add(inv.get(9));
		return res;
	}

	@Override
	public List<Slot> getInventorySlots(MixingBowlMenu container, MixingBowlRecipe recipe) {
		List<Slot> res = new ArrayList<>();
		// dunno if this 's' would be bug-freeee
		// intention of this one's to fetch the player's inventory
		int s = container.slots.size() - 36;
		int e = s + 36;
		for (int i=s;i<e;i++) {
			res.add(container.getSlot(i));
		}
		return res;
	}
	
	@Override
	public boolean requireCompleteSets(MixingBowlMenu container, MixingBowlRecipe recipe) {
		boolean v = false;
		// TODO
		return v;
	}

}
