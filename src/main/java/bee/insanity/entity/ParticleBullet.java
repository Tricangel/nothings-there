package bee.insanity.entity;

import bee.insanity.NothingsThere;
import bee.insanity.registry.ModEntityTypes;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ParticleBullet extends Entity {
    private static final ParticleEmitterInfo PARTICLE = new ParticleEmitterInfo(NothingsThere.id("shatter"));
    boolean isFlare = false;

    ParticleOptions type;
    public ParticleBullet(EntityType<?> type, Level level) {
        super(type, level);
        this.type = ParticleTypes.PORTAL;
    }

    public ParticleBullet(Level level, ParticleOptions particleType, boolean isFlare) {
        super(ModEntityTypes.PARTICLE_BULLET, level);
        this.type = particleType;
        this.isFlare = isFlare;
    }



    @Override
    public void tick() {

        super.tick();
        if (!level().getBlockState(this.getOnPos()).isAir()) {
            if (isFlare) level().explode(this, getX(), getY(), getZ(), 1, Level.ExplosionInteraction.BLOCK);
            this.remove(RemovalReason.DISCARDED);
        }
        Vec3 vec = this.getDeltaMovement();
        this.setDeltaMovement(vec.x(), vec.y -0.04, vec.z());
        this.level().addParticle(type, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        this.move(MoverType.SELF, this.getDeltaMovement().scale(new Random().nextFloat(.05f, .1f)));
    }



    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
    }

}
