package net.wickedbog.adventuremod.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import team.lodestar.lodestone.systems.particle.world.options.WorldParticleOptions;

public class SpiritParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;
    private final double targetX;
    private final double targetY;
    private final double targetZ;

    protected SpiritParticle(ClientLevel level, double x, double y, double z,
                             SpriteSet spriteSet, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        this.spriteSet = spriteSet;
        this.gravity = 0;

        // Define the target location slightly above the starting position
        this.targetX = x;
        this.targetY = y + 0.5; // Adjust as needed for "floating" effect
        this.targetZ = z;

        // Set an initial small velocity towards the target
        this.xd = xSpeed * 0.2;
        this.yd = ySpeed * 0.2;
        this.zd = zSpeed * 0.2;

        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public void tick() {
        super.tick();

        double dx = targetX - this.x;
        double dy = targetY - this.y;
        double dz = targetZ - this.z;

        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        // Pull particle towards the target if not close enough
        if (distance > 0.05) {
            double pullStrength = 0.02; // Controls pull speed; adjust as needed
            this.xd += dx / distance * pullStrength;
            this.yd += dy / distance * pullStrength;
            this.zd += dz / distance * pullStrength;
        }

        this.move(this.xd, this.yd, this.zd);

        // Slow down the particle's movement over time to create a fading effect
        this.xd *= 0.94;
        this.yd *= 0.94;
        this.zd *= 0.94;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<WorldParticleOptions> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(WorldParticleOptions particleOptions, ClientLevel level,
                                       double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SpiritParticle(level, x, y, z, this.spriteSet, xSpeed, ySpeed, zSpeed);
        }
    }
}
