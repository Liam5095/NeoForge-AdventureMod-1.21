package net.wickedbog.adventuremod.datagen;

import com.google.gson.JsonElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.ModBlocks;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    final Consumer<BlockStateGenerator> blockStateOutput;
    final BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput;

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper, Consumer<BlockStateGenerator> blockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        super(output, AdventureMod.MOD_ID, exFileHelper);
        this.blockStateOutput = blockStateOutput;
        this.modelOutput = modelOutput;
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

        // Flowers ect.

        simpleBlock(ModBlocks.CELESTIAL_GRASS.get(),
                models().cross(blockTexture(ModBlocks.CELESTIAL_GRASS.get()).getPath(), blockTexture(ModBlocks.CELESTIAL_GRASS.get())).renderType("cutout"));

        simpleBlock(ModBlocks.LUMINBLOSSOM.get(),
                models().cross(blockTexture(ModBlocks.LUMINBLOSSOM.get()).getPath(), blockTexture(ModBlocks.LUMINBLOSSOM.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_LUMINBLOSSOM.get(), models().singleTexture("potted_luminblossom", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.LUMINBLOSSOM.get())).renderType("cutout"));

        // Magical

        blockWithItem(ModBlocks.CRYSTAL_CLUSTER_BLOCK);

        // Magical Ether Moss

        carpetBlock(ModBlocks.ETHER_MOSS_CARPET, ModBlocks.ETHER_MOSS_BLOCK);
    }

    private void carpetBlock(DeferredBlock<Block> carpetBlock, DeferredBlock<Block> fullBlock) {
        simpleBlockWithItem(carpetBlock.get(),
                models().carpet(BuiltInRegistries.BLOCK.getKey(carpetBlock.get()).getPath(), blockTexture(fullBlock.get())));
        carpetBlockFull(fullBlock);
    }

    private void carpetBlockFull(DeferredBlock<Block> deferredBlock) {
        blockWithItem(deferredBlock);
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
