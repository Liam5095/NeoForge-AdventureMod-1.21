package net.wickedbog.adventuremod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wickedbog.adventuremod.AdventureMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AdventureMod.MOD_ID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.registerSimpleItem("test_item");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
