package net.wickedbog.adventuremod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class StarlightGrassBlock extends DoublePlantBlock {
    public StarlightGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 100;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    private boolean canPlantTypeSurvive(BlockState blockState, LevelReader level, BlockPos pos) {
        return blockState.is(BlockTags.DIRT) || blockState.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos) {
        BlockPos posBelow = pos.below();
        BlockState stateBelow = level.getBlockState(posBelow);
        if (blockState.getValue(HALF) != DoubleBlockHalf.UPPER) {
            if (blockState.getBlock() == this)
                return this.canPlantTypeSurvive(stateBelow, level, posBelow);
            return this.mayPlaceOn(stateBelow, level, posBelow);
        } else {
            return stateBelow.is(this) && stateBelow.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }
}
