package net.wickedbog.adventuremod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.wickedbog.adventuremod.particle.visual.LodestoneParticleEffects;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PottedStarpetalBlock extends FlowerPotBlock {
    public PottedStarpetalBlock(@Nullable Supplier<FlowerPotBlock> emptyPot, Supplier<? extends Block> flowerBlock, Properties properties) {
        super(emptyPot, flowerBlock, properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        VoxelShape voxelShape = this.getShape(blockState, level, pos, CollisionContext.empty());
        Vec3 vec3 = voxelShape.bounds().getCenter();
        double d0 = (double) pos.getX() + vec3.x;
        double d1 = (double) pos.getY() + vec3.y;
        double d2 = (double) pos.getZ() + vec3.z;

        for (int i = 0; i < 3; ++i) {
            if(random.nextBoolean()) {
                LodestoneParticleEffects.fireflyParticles(level, d0 + random.nextDouble() / 5.0, d1 + random.nextDouble() / 5.0, d2 + random.nextDouble() / 5.0);
            }
        }
    }
}
