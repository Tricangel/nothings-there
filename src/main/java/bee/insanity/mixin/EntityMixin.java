package bee.insanity.mixin;

import bee.insanity.item.DemoniteCage;
import bee.insanity.item.DemoniteShard;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

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

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci) {
        if ((Entity) (Object) this instanceof ItemEntity entity) {
            if (entity.getItem().getItem() instanceof DemoniteCage) {
                
            }

        }
    }



}
