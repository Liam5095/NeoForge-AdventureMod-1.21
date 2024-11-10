package net.wickedbog.adventuremod.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AdventureMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Misc.

        blockWithItem(ModBlocks.TEST_BLOCK);

        // Logs

        logBlock(((RotatedPillarBlock) ModBlocks.HEARTWOOD_LOG.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_HEARTWOOD_LOG.get()));

        blockItem(ModBlocks.HEARTWOOD_LOG);
        blockItem(ModBlocks.STRIPPED_HEARTWOOD_LOG);

        // Woods

        axisBlock(((RotatedPillarBlock) ModBlocks.HEARTWOOD_WOOD.get()), blockTexture(ModBlocks.HEARTWOOD_LOG.get()), blockTexture(ModBlocks.HEARTWOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_HEARTWOOD_WOOD.get()), blockTexture(ModBlocks.STRIPPED_HEARTWOOD_LOG.get()), blockTexture(ModBlocks.STRIPPED_HEARTWOOD_LOG.get()));

        blockItem(ModBlocks.HEARTWOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_HEARTWOOD_WOOD);

        // Planks

        blockWithItem(ModBlocks.HEARTWOOD_PLANKS);

        // Leaves

        leavesBlock(ModBlocks.HEARTWOOD_LEAVES);

        // Saplings

        saplingBlock(ModBlocks.HEARTWOOD_SAPLING);
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));

    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("adventuremod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("adventuremod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
