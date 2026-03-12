package bee.insanity.mixin;

import bee.insanity.registry.InsanityItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class ItemEntityMixin {
	@Shadow
	public double fallDistance;

	@Inject(at = @At("HEAD"), method = "onLanding")
	private void init(CallbackInfo ci) {
		if ((Entity) (Object) this instanceof ItemEntity itemEntity) {
			World world = itemEntity.getEntityWorld();
			if (itemEntity.getStack().getItem() == InsanityItems.DEMONITE_SHARD && fallDistance > 1.5f) {
				world.createExplosion(itemEntity, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 1, World.ExplosionSourceType.MOB);
			}
		}
	}
}