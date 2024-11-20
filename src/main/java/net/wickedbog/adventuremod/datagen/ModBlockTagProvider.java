package net.wickedbog.adventuremod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AdventureMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Mining

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TEST_BLOCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TEST_BLOCK.get());

        // Trees

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.HEARTWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_HEARTWOOD_LOG.get())
                .add(ModBlocks.HEARTWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_HEARTWOOD_WOOD.get())
                .add(ModBlocks.STARPETAL.get());

        // Flowers ect.

        this.tag(BlockTags.FLOWERS)
                .add(ModBlocks.ZEPHYR_LILLY.get())
                .add(ModBlocks.LUMINBLOSSOM.get());

        this.tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_LUMINBLOSSOM.get())
                .add(ModBlocks.POTTED_ZEPHYR_LILLY.get())
                .add(ModBlocks.POTTED_STARPETAL.get());

        // Magical - Ether Moss

        this.tag(BlockTags.WOOL_CARPETS)
                .add(ModBlocks.ETHER_MOSS_CARPET.get());

        this.tag(BlockTags.WOOL)
                .add(ModBlocks.ETHER_MOSS_BLOCK.get());
    }
}
