package net.wickedbog.adventuremod.worldgen.trees;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower HEARTWOOD = new TreeGrower(AdventureMod.MOD_ID + ":heartwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.HEARTWOOD_KEY), Optional.empty());
}
