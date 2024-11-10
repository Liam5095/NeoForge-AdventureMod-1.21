package net.wickedbog.adventuremod.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.ModBlocks;

import java.util.Optional;
import java.util.function.Consumer;

public class ModAdvancementProvider implements AdvancementProvider.AdvancementGenerator {
    Advancement.Builder builder = Advancement.Builder.advancement();

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        Advancement rootAdvancement = builder
                .display(new DisplayInfo(new ItemStack(ModBlocks.HEARTWOOD_LOG.get()),
                        Component.literal("Adventure Mod"), Component.literal("This a weird tree..."),
                        Optional.of(ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "textures/block/heartwood_log.png")), AdvancementType.TASK,
                        true, true, false))
                .addCriterion("has_heartwood_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.HEARTWOOD_LOG.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "adventuremod"), existingFileHelper).value();
    }
}
