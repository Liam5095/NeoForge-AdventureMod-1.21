package net.wickedbog.adventuremod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.wickedbog.adventuremod.particle.visual.LodestoneParticleEffects;

public class LuminblossomBlock extends FlowerBlock {
    public LuminblossomBlock(Holder<MobEffect> effect, float seconds, Properties properties) {
        super(effect, seconds, properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        VoxelShape voxelShape = this.getShape(blockState, level, pos, CollisionContext.empty());
        Vec3 vec3 = voxelShape.bounds().getCenter();
        double d0 = (double) pos.getX() + vec3.x;
        double d1 = (double) pos.getZ() + vec3.z;

        for (int i = 0; i < 3; ++i) {
            if(random.nextBoolean()) {
                LodestoneParticleEffects.luminblossomsParticles(level, d0 + random.nextDouble() / 5.0, (double) pos.getY() + (0.5 - random.nextDouble()), d1 + random.nextDouble() / 5.0);
            }
        }
    }
}
