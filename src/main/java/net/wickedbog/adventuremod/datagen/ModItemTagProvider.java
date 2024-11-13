package net.wickedbog.adventuremod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.ModBlocks;
import net.wickedbog.adventuremod.item.ModItems;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, AdventureMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Trees

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.HEARTWOOD_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_HEARTWOOD_LOG.get().asItem())
                .add(ModBlocks.HEARTWOOD_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_HEARTWOOD_WOOD.get().asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.HEARTWOOD_PLANKS.get().asItem());

        // Foods

        tag(Tags.Items.FOODS_BERRY)
                .add(ModItems.SKY_BERRIES.get());

        // Dyes

        tag(Tags.Items.DYES_PURPLE)
                .add(ModItems.LUMINBLOSSOM_DYE.get());
    }
}
