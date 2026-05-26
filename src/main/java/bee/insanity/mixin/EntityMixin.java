package bee.insanity.mixin;

import bee.insanity.data.DemonPlayers;
import bee.insanity.entity.ParticleBullet;
import bee.insanity.item.DemoniteCage;
import bee.insanity.item.DemoniteShard;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import java.util.stream.Stream;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public abstract void addDeltaMovement(Vec3 momentum);

    @Shadow
    public abstract void setDeltaMovement(Vec3 deltaMovement);

    @Shadow
    public abstract Vec3 getDeltaMovement();

    @Shadow
    protected abstract double getDefaultGravity();

    @Shadow
    public int tickCount;

    @Inject(method = "resetFallDistance", at = @At("HEAD"))
    public void makeExplode(CallbackInfo ci) {
        if ((Entity) (Object) this instanceof ItemEntity entity) {
            if (entity.getItem().getItem() instanceof DemoniteShard && entity.fallDistance > 5) {
                entity.level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 1, false, Level.ExplosionInteraction.MOB);
            }

        }
    }

    @Inject(method = "isNoGravity", at = @At("HEAD"), cancellable = true)
    public void makeFloat(CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof ItemEntity entity) {
            if (entity.getItem().getItem() instanceof DemoniteCage) {
                cir.setReturnValue(true);
            }

        }
    }

    @Inject(method = "getGravity", at = @At("HEAD"), cancellable = true)
    public void lowGrav(CallbackInfoReturnable<Double> cir) {
        if ((Entity) (Object) this instanceof Entity entity) {
            if (entity.position().y > 256) {
                //cir.setReturnValue(getDefaultGravity() / entity.position().scale(.1).y);
            }
        }
    }


    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci) {
        if ((Entity) (Object) this instanceof ItemEntity entity) {

            if (entity.getItem().getItem() instanceof DemoniteCage) {
                Vec3 movement = entity.getDeltaMovement().scale(.1);
                entity.addDeltaMovement(new Vec3(-movement.x, -movement.y, -movement.z));
            }


            if (entity.getItem().getItem() instanceof DemoniteCage && false) {
                BlockPos pos = entity.blockPosition();
                Level level = entity.level();
                AABB aabb = new AABB(entity.position(), entity.position().subtract(0, 2, 0));
                Stream<BlockState> states = level.getBlockStates(aabb);
                boolean hasBlockBelow = false;
                for (BlockState state : states.toList()) {
                    if (!state.isAir()) {
                        hasBlockBelow = true;
                        break;
                    }
                }

                if (this.tickCount % 3 == 0) {
                    ParticleBullet bullet = new ParticleBullet(level, ParticleTypes.PORTAL, false);
                    Random random = new Random();
                    Vec3 vec3 = new Vec3(random.nextFloat(-1f, 1f), -.5, random.nextFloat(-1f, 1f));
                    bullet.copyPosition(entity);
                    bullet.setDeltaMovement(vec3);
                    level.addFreshEntity(bullet);
                }

                if (hasBlockBelow) {
                    Vec3 movement = this.getDeltaMovement();
                    movement = new Vec3(movement.x, 0.1, movement.z);
                    this.setDeltaMovement(movement);
                }

            }

        }
    }



}
