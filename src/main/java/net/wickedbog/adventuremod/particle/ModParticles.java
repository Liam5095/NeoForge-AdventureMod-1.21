package net.wickedbog.adventuremod.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.wickedbog.adventuremod.AdventureMod;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

public class ModParticles {
    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, AdventureMod.MOD_ID);

    // Vanilla Particle

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TEST_PARTICLE =
            PARTICLE_TYPES.register("test_particle", () -> new SimpleParticleType(false));

    // Lodestone Particle

    public static final DeferredHolder<ParticleType<?>, LodestoneWorldParticleType> SPIRIT_PARTICLE =
            PARTICLE_TYPES.register("spirit_particle", LodestoneWorldParticleType::new);

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
