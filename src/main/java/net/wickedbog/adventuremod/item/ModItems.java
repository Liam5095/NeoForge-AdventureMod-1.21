package net.wickedbog.adventuremod.item;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wickedbog.adventuremod.AdventureMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AdventureMod.MOD_ID);

    // Misc.

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.registerSimpleItem("test_item");

    // Dyes

    public static final DeferredItem<Item> LUMINBLOSSOM_DYE = ITEMS.register("luminblossom_dye",
            () -> new DyeItem(DyeColor.PURPLE, new Item.Properties()));

    // Foods

    public static final DeferredItem<Item> SKY_BERRIES = ITEMS.register("sky_berries",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SKY_BERRIES)));

    // Shards

    public static final DeferredItem<Item> CELESTIAL_SHARD = ITEMS.register("celestial_shard",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
