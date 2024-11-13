package net.wickedbog.adventuremod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class CrystalClusterBlock extends Block {
    public CrystalClusterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            world.scheduleTick(pos, this, 20); // Schedule the first tick after 20 ticks (1 second)
        }
    }

    @Override
    protected void tick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource random) {
        applyRegenerationEffect(level, pos);
        level.scheduleTick(pos, this, 20); // Reschedule the next tick
    }

    private void applyRegenerationEffect(Level world, BlockPos pos) {
        if (world.isClientSide) return; // Only execute on the server

        // Define a 20x20x20 range (10 blocks in each direction)
        AABB range = new AABB(
                pos.getX() - 10, pos.getY() - 10, pos.getZ() - 10,
                pos.getX() + 10, pos.getY() + 10, pos.getZ() + 10
        );

        // Find all players in the range
        List<Player> playersInRange = world.getEntitiesOfClass(Player.class, range);

        // Apply the regeneration effect to each player
        for (Player player : playersInRange) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 1, true, false, false));
        }
    }
}
