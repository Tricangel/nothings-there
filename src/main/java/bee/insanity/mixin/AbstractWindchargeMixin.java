package bee.insanity.mixin;

import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModEntityComponents;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractWindCharge.class)
public abstract class AbstractWindchargeMixin {


    @Inject(method = {"onHit"}, at = @At("HEAD"))
    public void makeExplode(CallbackInfo ci) {
       if ((AbstractWindCharge) (Object) this instanceof WindCharge entity) {
           if (ModEntityComponents.BOOL.get(entity) != null && ModEntityComponents.BOOL.get(entity).getBool()) {
               entity.level().explode(entity, entity.getX(), entity.getY() - .5f, entity.getZ(), 1f, false, Level.ExplosionInteraction.BLOCK);
           }
       }

    }

}
