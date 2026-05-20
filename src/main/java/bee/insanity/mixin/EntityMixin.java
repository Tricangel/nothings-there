package bee.insanity.mixin;

import bee.insanity.item.DemoniteShard;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

}
