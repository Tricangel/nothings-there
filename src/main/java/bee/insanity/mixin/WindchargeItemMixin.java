package bee.insanity.mixin;

import bee.insanity.registry.ModComponents;
import bee.insanity.registry.ModEntityComponents;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WindChargeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WindChargeItem.class)
public abstract class WindchargeItemMixin {


    @Shadow
    public abstract Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction);

    @Inject(method = {"use"}, at = @At("HEAD"), cancellable = true)
    public void makeExplode(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (level instanceof ServerLevel serverLevel) {
            WindCharge windCharge = new WindCharge(player, serverLevel, player.getX(), player.getEyePosition().y(), player.getZ());
            if (stack.getOrDefault(ModComponents.BOOL, false)) ModEntityComponents.BOOL.get(windCharge).setBool(true);
            Projectile.spawnProjectileFromRotation(
                    (source, l, itemStack) -> windCharge,
                    serverLevel,
                    stack,
                    player,
                    0.0F,
                    1.5F,
                    1.0F
            );
        }

        level.playSound((Entity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.WIND_CHARGE_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.awardStat(Stats.ITEM_USED.get((WindChargeItem) (Object) this));
        stack.consume(1, player);
        cir.setReturnValue(InteractionResult.SUCCESS);

    }

}
