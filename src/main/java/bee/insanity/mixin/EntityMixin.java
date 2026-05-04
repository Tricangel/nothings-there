package bee.insanity.mixin;

import bee.insanity.registry.InsanityBlocks;
import bee.insanity.registry.InsanityItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	public double fallDistance;

	@Shadow
	public abstract void remove(Entity.RemovalReason reason);

	@Inject(at = @At("HEAD"), method = "onLanding")
	private void init(CallbackInfo ci) {
		if ((Entity) (Object) this instanceof ItemEntity itemEntity) {
			World world = itemEntity.getEntityWorld();
			if (itemEntity.getStack().getItem() == InsanityItems.DEMONITE_SHARD && fallDistance > 5f) {
				world.createExplosion(itemEntity, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 1, World.ExplosionSourceType.MOB);
				this.remove(Entity.RemovalReason.KILLED);
			}

			if (itemEntity.getStack().getItem() == InsanityItems.DEMONITE && fallDistance > 15f) {
				world.createExplosion(itemEntity, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 2, World.ExplosionSourceType.MOB);
				this.remove(Entity.RemovalReason.KILLED);
			}

			if (itemEntity.getStack().getItem() == InsanityBlocks.DEMONITE_BLOCK.asItem() && fallDistance > 50f) {
				world.createExplosion(itemEntity, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 5, World.ExplosionSourceType.MOB);
				this.remove(Entity.RemovalReason.KILLED);
			}
		}
	}



}