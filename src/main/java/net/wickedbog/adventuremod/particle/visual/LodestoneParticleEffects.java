package net.wickedbog.adventuremod.particle.visual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.wickedbog.adventuremod.particle.ModParticles;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleTypes;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.*;
import java.util.Random;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class LodestoneParticleEffects {

    // Tick Event

    @SubscribeEvent
    public static void clientTick(ClientTickEvent.Pre event) {
        final LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {

        }
    }

    // Particles

    public static void spawnParticlesTemplate(Level level, int x, int y, int z) {
        Color startingColor = new Color(100, 0, 100);
        Color endingColor = new Color(0, 100, 200);
        Easing colorEasingType = Easing.BOUNCE_IN_OUT;
        float colorCoefficient = 1.4f;

        float startingScale = 0.5f;
        float endingScale = 0f;

        float startingTransparency = 0.75f;
        float endingTransparency = 0.25f;

        float startingSpin = 0.2f;
        float endingSpin = 0.4f;
        float spinOffsetMulti = 0.2f;
        Easing spinEasingType = Easing.QUARTIC_IN;

        int lifetime = 40;

        float motionX = 0f;
        float motionY = 0.01f;
        float motionZ = 0f;

        WorldParticleBuilder.create(LodestoneParticleTypes.TWINKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(startingScale, endingScale).build())
                .setTransparencyData(GenericParticleData.create(startingTransparency, endingTransparency).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(colorCoefficient).setEasing(colorEasingType).build())
                .setSpinData(SpinParticleData.create(startingSpin, endingSpin).setSpinOffset((level.getGameTime() * spinOffsetMulti) % 6.28f).setEasing(spinEasingType).build())
                .setLifetime(lifetime)
                .addMotion(motionX, motionY, motionZ)
                .enableNoClip()
                .spawn(level, x, y, z);
    }

    public static void luminblossomsParticles(Level level, double x, double y, double z) {
        Random rand = new Random();

        // Set a wider random position offset for spawning particles further away
        double offsetX = rand.nextDouble(-0.5, 0.5); // Increase horizontal spread
        double offsetY = rand.nextDouble(0.2, 0.6);  // Vertical offset for higher initial position
        double offsetZ = rand.nextDouble(-0.5, 0.5); // Increase horizontal spread

        WorldParticleBuilder.create(ModParticles.SPIRIT_PARTICLE)
                .spawn(level, x + offsetX, y + offsetY, z + offsetZ);
    }

    public static void fireflyParticles(Level level, double x, double y, double z) {
        Random rand = new Random();

        double offsetX = rand.nextDouble(-0.5, 0.5);;
        double offsetZ = rand.nextDouble(-0.5, 0.5);

        WorldParticleBuilder.create(ModParticles.FIREFLY_PARTICLE)
                .spawn(level, x + offsetX, y , z + offsetZ);
    }
}
