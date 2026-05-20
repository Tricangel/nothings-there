package bee.insanity.mixin;

import bee.insanity.item.DemoniteShard;
import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class ProjectileMixin {

    @Shadow
    protected abstract ItemStack getPickupItem();

    @Shadow
    protected abstract void setPickupItemStack(ItemStack itemStack);

    @Shadow
    protected abstract void setInGround(boolean inGround);

    @Inject(method = {"onHitEntity", "onHitBlock"}, at = @At("TAIL"))
    public void makeExplode(CallbackInfo ci) {
        AbstractArrow entity = (AbstractArrow) (Object) this;
        ItemStack stack = this.getPickupItem();
        if (stack.getOrDefault(ModComponents.BOOL, false)) {
            entity.level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 2, false, Level.ExplosionInteraction.BLOCK);
            stack.remove(ModComponents.BOOL);
            this.setPickupItemStack(stack);
            this.setInGround(false);
        }

    }

}
