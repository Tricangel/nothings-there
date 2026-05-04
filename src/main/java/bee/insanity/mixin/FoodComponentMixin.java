package bee.insanity.mixin;

import bee.insanity.registry.InsanityComponents;
import bee.insanity.registry.InsanitySounds;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodComponent.class)
public class FoodComponentMixin {

    @Inject(at = @At("HEAD"), method = "onConsume", cancellable = true)
    private static void eatingitrightnow(World world, LivingEntity user, ItemStack stack, ConsumableComponent consumable, CallbackInfo ci) {
        if (stack.getComponents().contains(InsanityComponents.DEMONITE)) {
            if (world.isClient()) {
                user.playSound(InsanitySounds.BOMB_EXPLODE, 0.5f, 0.7f);
            }
            if (world instanceof ServerWorld serverWorld) {
                Vec3d rotation = user.getRotationVector();
                float deltaX = (float) (-rotation.getX() * 0.1f);
                float deltaZ = (float) (-rotation.getZ() * 0.1f);
                user.addVelocity(deltaX, 0.4f, deltaZ);
                user.velocityDirty = true;
                serverWorld.spawnParticles(ParticleTypes.EXPLOSION, user.getX(), user.getY(), user.getZ(), 2, 0.05f, 0.05f, 0.05f, 0f);
                user.damage(serverWorld, user.getDamageSources().explosion(user, user), stack.get(DataComponentTypes.FOOD).nutrition());
            }
        }
    }

}
