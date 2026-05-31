package bee.insanity.mixin;

import bee.insanity.registry.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionContents.class)
public class PotionContentsMixin {

        @Inject(at = @At("HEAD"), method = "lambda$applyToLivingEntity$0", cancellable = true)
        private static void init(ServerLevel serverLevel, Player player, LivingEntity entity, MobEffectInstance effect, CallbackInfo ci) {
            if (effect.getEffect().value().isInstantenous()) {
                effect.getEffect().value().applyInstantenousEffect(serverLevel, player, player, entity, effect.getAmplifier(), 1.0);
            } else {
                if (player.getUseItem().is(ModItems.DEMONITE_POTION)) {
                    effect = new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), false);
                }
                entity.addEffect(effect);
            }
            ci.cancel();

        }
}