package net.wickedbog.adventuremod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AdventureMod.MOD_ID);

    public static final Supplier<CreativeModeTab> ADVENTURE_MOD_TAB =
            CREATIVE_MODE_TABS.register("adventure_mod_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.adventure_mod.title"))
                    .icon(() -> new ItemStack(ModBlocks.TEST_BLOCK.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TEST_ITEM);
                        pOutput.accept(ModBlocks.TEST_BLOCK);
                        pOutput.accept(ModBlocks.HEARTWOOD_LOG);
                        pOutput.accept(ModBlocks.STRIPPED_HEARTWOOD_LOG);
                        pOutput.accept(ModBlocks.HEARTWOOD_WOOD);
                        pOutput.accept(ModBlocks.STRIPPED_HEARTWOOD_WOOD);
                        pOutput.accept(ModBlocks.HEARTWOOD_PLANKS);
                        pOutput.accept(ModBlocks.HEARTWOOD_LEAVES);
                        pOutput.accept(ModBlocks.HEARTWOOD_SAPLING);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
