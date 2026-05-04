package bee.insanity.mixin;

import bee.insanity.registry.InsanityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class ProjectileEntityMixin {

	@Shadow
	public abstract ItemStack getItemStack();

	@Inject(at = @At("TAIL"), method = {"onEntityHit", "onBlockHit"})
	private void init(CallbackInfo ci) {
		ProjectileEntity entity = (ProjectileEntity) (Object) this;
		World world = entity.getEntityWorld();
		ItemStack stack = this.getItemStack();
		Vec3d pos = entity.getEntityPos();


		if (stack.get(InsanityComponents.DEMONITE) != null && stack.get(InsanityComponents.DEMONITE)) {
			world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1, World.ExplosionSourceType.MOB);
			stack.remove(InsanityComponents.DEMONITE);
			entity.setOnGround(false);
		}

	}



}