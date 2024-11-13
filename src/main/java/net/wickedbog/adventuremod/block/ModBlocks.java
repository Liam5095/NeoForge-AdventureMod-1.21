package net.wickedbog.adventuremod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.block.custom.CrystalClusterBlock;
import net.wickedbog.adventuremod.block.custom.LuminblossomBlock;
import net.wickedbog.adventuremod.block.custom.ModFlammableRotatedPillarBlock;
import net.wickedbog.adventuremod.item.ModItems;
import net.wickedbog.adventuremod.worldgen.trees.ModTreeGrowers;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AdventureMod.MOD_ID);

    // Misc.

    public static final DeferredBlock<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    // Logs

    public static final DeferredBlock<Block> HEARTWOOD_LOG = registerBlock("heartwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<Block> STRIPPED_HEARTWOOD_LOG = registerBlock("stripped_heartwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));


    // Woods

    public static final DeferredBlock<Block> HEARTWOOD_WOOD = registerBlock("heartwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<Block> STRIPPED_HEARTWOOD_WOOD = registerBlock("stripped_heartwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    // Planks

    public static final DeferredBlock<Block> HEARTWOOD_PLANKS = registerBlock("heartwood_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    // Leaves

    public static final DeferredBlock<Block> HEARTWOOD_LEAVES = registerBlock("heartwood_leaves",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    // Saplings

    public static final DeferredBlock<Block> HEARTWOOD_SAPLING = registerBlock("heartwood_sapling",
            () -> new SaplingBlock(ModTreeGrowers.HEARTWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    // Flowers ect.

    public static final DeferredBlock<Block> CELESTIAL_GRASS = registerBlock("celestial_grass",
            () -> new TallGrassBlock(BlockBehaviour.Properties.of()
                    .replaceable().noCollission().instabreak()
                    .sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .ignitedByLava().pushReaction(PushReaction.DESTROY).lightLevel(s -> 3)));

    public static final DeferredBlock<Block> LUMINBLOSSOM = registerBlock("luminblossom",
            () -> new LuminblossomBlock(MobEffects.ABSORPTION, 2f, BlockBehaviour.Properties.of()
                    .replaceable().noCollission().instabreak()
                    .sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)
                    .ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> POTTED_LUMINBLOSSOM = registerBlock("potted_luminblossom",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), LUMINBLOSSOM, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    // Magical

    public static final DeferredBlock<Block> CRYSTAL_CLUSTER_BLOCK = registerBlock("crystal_cluster_block",
            () -> new CrystalClusterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).sound(SoundType.STONE)));

    // Rest

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}