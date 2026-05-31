package bee.insanity.mixin;

import bee.insanity.item.DemonitePotion;
import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Consumable.class)
public abstract class ConsumableMixin {

    @Inject(method = "onConsume", at = @At("HEAD"))
    private void explodeWhenDemonite(Level level, LivingEntity user, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.is(ModItems.DEMONITE_SHARD) || stack.getItem() instanceof DemonitePotion || stack.getOrDefault(ModComponents.BOOL, false)) {
            level.explode(null, user.getX(), user.getEyeY(), user.getZ(), 1, false, Level.ExplosionInteraction.MOB);
        }
    }


}
