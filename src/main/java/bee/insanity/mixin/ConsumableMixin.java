package bee.insanity.mixin;

import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Consumable.class)
public abstract class ConsumableMixin {

    @Inject(method = "onConsume", at = @At("HEAD"))
    private void explodeWhenDemonite(Level level, LivingEntity user, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.is(ModItems.DEMONITE_SHARD) || stack.getOrDefault(ModComponents.BOOL, false)) {
            level.explode(null, user.getX(), user.getY(), user.getZ(), 1, false, Level.ExplosionInteraction.MOB);
            stack.shrink(1);
        }
    }

}
