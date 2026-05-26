package bee.insanity.item;

import bee.insanity.entity.ParticleBullet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FlareGun extends Item {
    public FlareGun(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        ParticleBullet flare = new ParticleBullet(level, ParticleTypes.ASH, true);
        flare.setDeltaMovement(player.getViewVector(1).scale(30));
        flare.snapTo(player.getX(), player.getEyeY(), player.getZ());
        level.addFreshEntity(flare);


        return super.use(level, player, hand);
    }
}
