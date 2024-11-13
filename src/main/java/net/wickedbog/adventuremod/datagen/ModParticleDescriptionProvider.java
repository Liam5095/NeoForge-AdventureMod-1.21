package net.wickedbog.adventuremod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.particle.ModParticles;

public class ModParticleDescriptionProvider extends ParticleDescriptionProvider {
    protected ModParticleDescriptionProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    protected void addDescriptions() {
        sprite(ModParticles.SPIRIT_PARTICLE.get(), ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "spirit_particle"));
        sprite(ModParticles.TEST_PARTICLE.get(), ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "test_particle"));

        // Animated

        // spriteSet(ModParticleTypes.ANIMATED_PARTICLE.get(),
        //        ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "animated_particle_1"),
        //        ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "animated_particle_2"),
        //        ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "animated_particle_3"));

        // Alternative for the above, appends "_<index>" to the base name given, for the given amount of textures.

        // spriteSet(ModParticleTypes.ALT_ANIMATED_PARTICLE.get(),
                // The base name.
        //        ResourceLocation.fromNamespaceAndPath(AdventureMod.MOD_ID, "alt_animated_particle"),
                // The amount of textures.
        //        3,
                // Whether to reverse the list, i.e. start at the last element instead of the first.
        //        false
        // );
    }
}
