package bee.insanity.mixin;

import bee.insanity.registry.InsanityComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MaceItem.class)
public class MaceItemMixin {

    @Inject(at = @At("HEAD"), method = "postHit", cancellable = true)
    private static void eatingitrightnow(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfo ci) {
        if (stack.getComponents().contains(InsanityComponents.DEMONITE)) {
            World world = target.getEntityWorld();
            world.createExplosion(null, target.getX(), target.getY(), target.getZ(), .5f, World.ExplosionSourceType.MOB);
            stack.remove(InsanityComponents.DEMONITE);
        }
    }

}
