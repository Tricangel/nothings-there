package bee.insanity.mixin;

import bee.insanity.item.DemoniteSplashPotion;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownSplashPotion.class)
public class SplashPotionMixin {

    @Inject(at = @At("HEAD"), method = "onHitAsPotion")
    private void potionHit(ServerLevel level, ItemStack potionItem, HitResult hitResult, CallbackInfo ci) {

        if (potionItem.getItem() instanceof DemoniteSplashPotion) {
            ThrownSplashPotion potion = (ThrownSplashPotion) (Object) this;

            level.explode(potion, potion.getX(), potion.getY(), potion.getZ(), 1, false, Level.ExplosionInteraction.BLOCK);
        }

    }

}
