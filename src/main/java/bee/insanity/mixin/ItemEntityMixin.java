package bee.insanity.mixin;

import bee.insanity.entity.ParticleBullet;
import bee.insanity.item.DemoniteCage;
import bee.insanity.item.DemoniteShard;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
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

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {


    @Inject(method = "playerTouch", at = @At("HEAD"), cancellable = true)
    public void makeExplode(CallbackInfo ci) {
        if ((Entity) (Object) this instanceof ItemEntity entity) {
            if (entity.getItem().getItem() instanceof DemoniteCage) {
                ci.cancel();
            }

        }
    }

    @Inject(method = "hurtServer", at = @At("HEAD"), cancellable = true)
    public void makeExpwlode(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof ItemEntity entity) {
            if (entity.getItem().getItem() instanceof DemoniteCage) {
                level.explode(entity, entity.getX(), entity.getY(), entity.getZ(), 1, Level.ExplosionInteraction.MOB);
                cir.setReturnValue(true);
            }

        }
    }





}
