package net.wickedbog.adventuremod.worldgen.biomes;

import net.minecraft.resources.ResourceLocation;
import net.wickedbog.adventuremod.AdventureMod;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "overworld"), 5));
    }
}
