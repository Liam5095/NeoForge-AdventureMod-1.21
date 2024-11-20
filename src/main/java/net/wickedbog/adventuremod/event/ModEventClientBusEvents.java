package net.wickedbog.adventuremod.event;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.wickedbog.adventuremod.AdventureMod;
import net.wickedbog.adventuremod.particle.ModParticles;
import net.wickedbog.adventuremod.particle.custom.FireflyParticle;
import net.wickedbog.adventuremod.particle.custom.SpiritParticle;
import net.wickedbog.adventuremod.particle.custom.TestParticle;

@EventBusSubscriber(modid = AdventureMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.TEST_PARTICLE.get(), TestParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(
                ModParticles.SPIRIT_PARTICLE.get(),
                (SpiritParticle.Provider::new)
        );

        Minecraft.getInstance().particleEngine.register(
                ModParticles.FIREFLY_PARTICLE.get(),
                (FireflyParticle.Provider::new)
        );
    }
}
