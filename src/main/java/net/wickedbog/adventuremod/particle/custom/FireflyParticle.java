package net.wickedbog.adventuremod.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import team.lodestar.lodestone.systems.particle.world.options.WorldParticleOptions;

public class FireflyParticle extends StarpetalParticleHelper {
    private final SpriteSet spriteSet;

    protected FireflyParticle(ClientLevel level, double x, double y, double z,
                             SpriteSet spriteSet, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        this.spriteSet = spriteSet;
        this.quadSize *= 5;
        this.lifetime = (int)(Math.random() * 2.0) + 40;
        this.gravity = -.2f;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public void tick() {
        super.tick();
        float progress = (float)this.age / (float)this.lifetime;
        this.x += this.xd * progress * 0.344;  // Adjust this factor to control speed
        this.y += this.yd * progress * 0.344;
        this.z += this.zd * progress * 0.344;
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
            FireflyParticle particle = new FireflyParticle(level, x, y, z, this.spriteSet, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
