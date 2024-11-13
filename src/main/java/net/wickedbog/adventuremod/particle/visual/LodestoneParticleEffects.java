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

        double randomMotionSpeed = 0.5d;

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
        Color startingColor = new Color(66, 135, 245);
        Color endingColor = new Color(0, 100, 200);
        Easing colorEasingType = Easing.BOUNCE_IN_OUT;
        float colorCoefficient = 1.4f;

        float startingScale = 0.5f;
        float endingScale = 0.35f;

        float startingTransparency = 0.75f;
        float endingTransparency = 0.25f;

        float startingSpin = 0.2f;
        float endingSpin = 0.4f;
        float spinOffsetMulti = 0.2f;
        Easing spinEasingType = Easing.QUARTIC_IN;

        int lifetime = 5;

        double randomMotionSpeed = 0.1d;

        WorldParticleBuilder.create(ModParticles.SPIRIT_PARTICLE)
                .setScaleData(GenericParticleData.create(startingScale, endingScale).build())
                .setTransparencyData(GenericParticleData.create(startingTransparency, endingTransparency).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(colorCoefficient).setEasing(colorEasingType).build())
                .setSpinData(SpinParticleData.create(startingSpin, endingSpin).setSpinOffset((level.getGameTime() * spinOffsetMulti) % 6.28f).setEasing(spinEasingType).build())
                .setLifetime(lifetime)
                .setRandomMotion(randomMotionSpeed)
                .enableNoClip()
                .spawn(level, x, y, z);
    }
}
