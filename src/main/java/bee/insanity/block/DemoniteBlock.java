package bee.insanity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class DemoniteBlock extends Block {
    public DemoniteBlock(Settings settings) {
        super(settings);
    }




    @Override
    protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!projectile.collidedSoftly) {
            world.createExplosion(projectile.getEntity(), hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ(), 3,  World.ExplosionSourceType.BLOCK);
        }
        super.onProjectileHit(world, state, hit, projectile);
    }

    @Override
    public void onDestroyedByExplosion(ServerWorld world, BlockPos pos, Explosion explosion) {
        super.onDestroyedByExplosion(world, pos, explosion);
        world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3,  World.ExplosionSourceType.BLOCK);
    }

    @Override
    public boolean shouldDropItemsOnExplosion(Explosion explosion) {
        return false;
    }

}
