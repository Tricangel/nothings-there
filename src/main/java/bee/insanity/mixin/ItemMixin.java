package bee.insanity.mixin;

import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "hurtEnemy", at = @At("HEAD"))
    private void explodeWhenDemonite(ItemStack stack, LivingEntity mob, LivingEntity attacker, CallbackInfo ci) {
        if (stack.is(ModItems.DEMONITE_SHARD) || stack.getOrDefault(ModComponents.BOOL, false)) {
            attacker.level().explode(attacker, mob.getX(), mob.getY(), mob.getZ(), 1, false, Level.ExplosionInteraction.MOB);
            stack.shrink(1);
        }
    }

    @Inject(method = "mineBlock", at = @At("HEAD"))
    private void explodeWhenDemonite2(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity owner, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(ModItems.DEMONITE_SHARD) || stack.getOrDefault(ModComponents.BOOL, false)) {
            owner.level().explode(owner, pos.getX(), pos.getY(), pos.getZ(), 1, false, Level.ExplosionInteraction.MOB);
            stack.shrink(1);
        }
    }


}
