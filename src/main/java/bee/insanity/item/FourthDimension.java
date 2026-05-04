package bee.insanity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Set;

public class FourthDimension extends Item {
    public FourthDimension(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        HitResult result = user.raycast(10, 10, false);

        user.sendMessage(Text.literal(String.valueOf(result.getType())), false);

        if (result.getType().equals(Type.ENTITY)) {
            Entity entity = ((EntityHitResult) result).getEntity();
            Vec3d entityPos = entity.getEntityPos();
            entity.teleport((ServerWorld) world, user.getX(), user.getY(), user.getZ(), Set.of(), 0, 0, false);
            user.teleport(entityPos.getX(), entityPos.getY(), entityPos.getZ(), false);
        } else {

            if (world.getBlockState(BlockPos.ofFloored(result.getPos())).isAir()) {
                user.teleport(result.getPos().getX(), result.getPos().getY(), result.getPos().getZ(), false);
            }
        }

        return super.use(world, user, hand);
    }
}
