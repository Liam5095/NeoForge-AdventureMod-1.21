package net.wickedbog.adventuremod.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.ModBlocks;
import net.wickedbog.adventuremod.block.custom.StarlightGrassBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    static enum TintState {
        TINTED,
        NOT_TINTED;

        public ModelTemplate getCross() {
            return this == TINTED ? ModelTemplates.TINTED_CROSS : ModelTemplates.CROSS;
        }

        public ModelTemplate getCrossPot() {
            return this == TINTED ? ModelTemplates.TINTED_FLOWER_POT_CROSS : ModelTemplates.FLOWER_POT_CROSS;
        }
    }

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

        // Flowers ect.

        simpleBlock(ModBlocks.CELESTIAL_GRASS.get(),
                models().cross(blockTexture(ModBlocks.CELESTIAL_GRASS.get()).getPath(), blockTexture(ModBlocks.CELESTIAL_GRASS.get())).renderType("cutout"));

        doubleFlowerBlock(ModBlocks.STARLIGHT_GRASS, "starlight_grass");

        simpleBlock(ModBlocks.LUMINBLOSSOM.get(),
                models().cross(blockTexture(ModBlocks.LUMINBLOSSOM.get()).getPath(), blockTexture(ModBlocks.LUMINBLOSSOM.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_LUMINBLOSSOM.get(), models().singleTexture("potted_luminblossom", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.LUMINBLOSSOM.get())).renderType("cutout"));

        simpleBlock(ModBlocks.ZEPHYR_LILLY.get(),
                models().cross(blockTexture(ModBlocks.ZEPHYR_LILLY.get()).getPath(), blockTexture(ModBlocks.ZEPHYR_LILLY.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_ZEPHYR_LILLY.get(), models().singleTexture("potted_zephyr_lilly", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.ZEPHYR_LILLY.get())).renderType("cutout"));

        // Magical

        blockWithItem(ModBlocks.CRYSTAL_CLUSTER_BLOCK);

        // Magical Ether Moss

        carpetBlock(ModBlocks.ETHER_MOSS_CARPET, ModBlocks.ETHER_MOSS_BLOCK);
    }

    private void doubleFlowerBlock(DeferredBlock<Block> flowerBlock, String name) {
        getVariantBuilder(flowerBlock.get())
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models()
                        .withExistingParent(name + "_lower", mcLoc("block/cross"))
                        .texture("cross", modLoc("block/" + name + "_bottom"))
                        .texture("particle", modLoc("block/" + name + "_top")).renderType("cutout")).addModel()
                .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models()
                        .withExistingParent(name + "_upper", mcLoc("block/cross"))
                        .texture("cross", modLoc("block/" + name + "_top"))
                        .texture("particle", modLoc("block/" + name + "_top")).renderType("cutout")).addModel();
    }

    private void carpetBlock(DeferredBlock<Block> carpetBlock, DeferredBlock<Block> fullBlock) {
        simpleBlockWithItem(carpetBlock.get(),
                models().carpet(BuiltInRegistries.BLOCK.getKey(carpetBlock.get()).getPath(), blockTexture(fullBlock.get())));
        carpetBlockFull(fullBlock);
    }

    private void carpetBlockFull(DeferredBlock<Block> carpetFull) {
        blockWithItem(carpetFull);
    }

    private void leavesBlock(DeferredBlock<Block> leaves) {
        simpleBlockWithItem(leaves.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(leaves.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(leaves.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> sapling) {
        simpleBlock(sapling.get(), models().cross(BuiltInRegistries.BLOCK.getKey(sapling.get()).getPath(), blockTexture(sapling.get())).renderType("cutout"));

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
