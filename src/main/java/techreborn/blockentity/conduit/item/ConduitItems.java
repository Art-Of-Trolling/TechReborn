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
package techreborn.blockentity.conduit.item;

import net.minecraft.item.Item;
import reborncore.api.systems.conduit.functionalfaces.ConduitFunction;
import reborncore.api.systems.conduit.functionalfaces.IConduitItemProvider;
import techreborn.init.TRContent;

import java.util.HashMap;
import java.util.Map;

public class ConduitItems implements IConduitItemProvider {


	Map<ConduitFunction, Item> itemFunctionMap = new HashMap<ConduitFunction, Item>() {{
		put(ConduitFunction.EXPORT, TRContent.Conduit.EXPORT_CONDUIT_ITEM.asItem());
		put(ConduitFunction.IMPORT, TRContent.Conduit.IMPORT_CONDUIT_ITEM.asItem());
		put(ConduitFunction.BLOCK, TRContent.Conduit.BLOCK_CONDUIT_ITEM.asItem());
		put(ConduitFunction.ONE_WAY, TRContent.Conduit.ONE_WAY_CONDUIT_ITEM.asItem());
	}};

	@Override
	public Item getItem(ConduitFunction function) {
		return itemFunctionMap.get(function);
	}

	@Override
	public ConduitFunction fromConduitFunction(Item item) {
		for (Map.Entry<ConduitFunction, Item> entry : itemFunctionMap.entrySet()) {
			if (entry.getValue() == item) {
				return entry.getKey();
			}
		}

		return null;
	}
}
