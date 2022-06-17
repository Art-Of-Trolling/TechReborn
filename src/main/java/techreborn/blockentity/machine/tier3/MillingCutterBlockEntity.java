/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2020 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package techreborn.blockentity.machine.tier3;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import reborncore.api.IListInfoProvider;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.client.screen.builder.BuiltScreenHandler;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.util.ItemUtils;
import reborncore.common.util.RebornInventory;
import techreborn.blockentity.machine.GenericMachineBlockEntity;
import techreborn.config.TechRebornConfig;
import techreborn.init.ModRecipes;
import techreborn.init.TRBlockEntities;
import techreborn.init.TRContent;
import techreborn.items.DynamicCellItem;

import java.util.List;

public class MillingCutterBlockEntity extends GenericMachineBlockEntity implements BuiltScreenHandlerProvider, IListInfoProvider {

	public MillingCutterBlockEntity() {
		super(TRBlockEntities.MILLING_CUTTER, "MillingCutter", TechRebornConfig.millingCutterMaxInput, TechRebornConfig.millingCutterMaxEnergy, TRContent.Machine.MILLING_CUTTER.block, 13);
		final int[] inputs = new int[]{0, 1, 2, 3};
		final int[] outputs = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12};
		this.inventory = new RebornInventory<>(14, "MillingCutterBlockEntity", 64, this);
		this.crafter = new RecipeCrafter(ModRecipes.MILLING_CUTTER, this, 4, 9, this.inventory, inputs, outputs);
	}

	// IContainerProvider
	@Override
	public BuiltScreenHandler createScreenHandler(int syncID, final PlayerEntity player) {
		return new ScreenHandlerBuilder("millingcutter").player(player.inventory).inventory().hotbar()
				.addInventory().blockEntity(this)
				.filterSlot(3, 47, 36, stack -> !ItemUtils.isItemEqual(stack, DynamicCellItem.getEmptyCell(1), true, true))
				.filterSlot(2, 47, 54, stack -> ItemUtils.isItemEqual(stack, DynamicCellItem.getEmptyCell(1), true, true))
				.filterSlot(1, 29, 36, stack -> ItemUtils.isItemEqual(stack, DynamicCellItem.getEmptyCell(1), true, true))
				.filterSlot(0, 29, 54, stack -> !ItemUtils.isItemEqual(stack, DynamicCellItem.getEmptyCell(1), true, true))
				.outputSlot(4, 91, 28).outputSlot(5, 91, 46).outputSlot(6, 91, 64)
				.outputSlot(7, 109, 28).outputSlot(8, 109, 46).outputSlot(9, 109, 64)
				.outputSlot(10, 127, 28).outputSlot(11,127, 46).outputSlot(12,127, 64)
				.energySlot(13, 8, 72).syncEnergyValue()
				.syncCrafterValue().addInventory().create(this, syncID);
	}

	// IListInfoProvider
	@Override
	public void addInfo(final List<Text> info, final boolean isReal, boolean hasData) {
		super.addInfo(info, isReal, hasData);
		if (Screen.hasControlDown()) {
			info.add(new LiteralText("Big THINGS"));
		}
	}
}