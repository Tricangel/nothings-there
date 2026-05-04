package bee.insanity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class DemoniteEffect extends StatusEffect {
    public DemoniteEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        entity.setInvisible(true);
        if (entity instanceof PlayerEntity player) {
            if (!player.getEntityWorld().isClient()) {
                ServerWorld world = (ServerWorld) player.getEntityWorld();
                MinecraftServer server = world.getServer();
                MutableText quitmessage = Text.translatable("multiplayer.player.left", player.getDisplayName());
                server.getPlayerManager().broadcast(quitmessage.formatted(Formatting.YELLOW), false);
            }
        }
        super.onApplied(entity, amplifier);
    }

    @Override
    public void onEffectRemoved(StatusEffectInstance effectInstance, LivingEntity entity) {
        entity.setInvisible(true);
        if (entity instanceof PlayerEntity player) {
            if (!player.getEntityWorld().isClient()) {
                ServerWorld world = (ServerWorld) player.getEntityWorld();
                MinecraftServer server = world.getServer();
                MutableText joinmessage = Text.translatable("multiplayer.player.joined", player.getDisplayName());
                server.getPlayerManager().broadcast(joinmessage.formatted(Formatting.YELLOW), false);
            }
        }
        super.onEffectRemoved(effectInstance, entity);
    }

    @Override
    public void onEntityDamage(ServerWorld world, LivingEntity entity, int amplifier, DamageSource source, float amount) {
        //world.createExplosion(entity, entity.getX(), entity.getY(), entity.getZ(), amplifier + 1 * amount, World.ExplosionSourceType.MOB);
        super.onEntityDamage(world, entity, amplifier, source, amount);
    }
}
