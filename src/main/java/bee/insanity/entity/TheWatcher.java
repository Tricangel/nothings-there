package bee.insanity.entity;

import bee.insanity.registry.ModEntityTypes;
import bee.insanity.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Containers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class TheWatcher extends Mob {
    private int angerTime;
    public ResolvableProfile profile;
    public TheWatcher(EntityType<? extends Mob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 500, 100));
    }

    @Override
    public HumanoidArm getMainArm() {
        return Minecraft.getInstance().player != null ? Minecraft.getInstance().player.getMainArm() : HumanoidArm.RIGHT;
    }

    @Override
    public void tick() {
        super.tick();
        level().getEntities(this, this.getBoundingBox().inflate(100)).forEach(entity -> {
            if (entity.is(ModEntityTypes.THE_WATCHER)) entity.remove(RemovalReason.DISCARDED);
        });

        if (level().getNearestPlayer(this, 500) != null) {
            this.profile = level().getNearestPlayer(this, 500).getProfile();
        } else this.remove(RemovalReason.DISCARDED);
        for (Player player : level().players()) {

            if (this.isLookingAtMe(player, .4, true, true,  new double[]{this.getEyeY()})) {
                if (angerTime % 3 == 1) player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.4f ,2.9f);
                angerTime++;
                if (angerTime > 60) {
                    this.remove(this.level(), this, player);
                }
            }

        }

    }

    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);
        this.remove(this.level(), this, player);

    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        scatterItem(level(), new ItemStack(ModItems.DEMONITE_SHARD), position());
        this.remove(level, this, (Player) source.getEntity());
        return false;
    }

    private void remove(Level level, Entity entity, @Nullable Player player) {
        if (level.isClientSide()) {
            if (player != null) {
                player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL);
            }
        } else {
            ((ServerLevel)level).sendParticles(ParticleTypes.SMOKE, entity.getX(), entity.getY(), entity.getZ(), 100, .3, 1, 1, .01f);
            ((ServerLevel)level).sendParticles(ParticleTypes.SMOKE, entity.getX(), entity.getY(), entity.getZ(), 300, .3, 1, 1, .5f);

            entity.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason spawnReason) {
        return level.canSeeSky(this.blockPosition());
    }

    public static void scatterItem(Level level, ItemStack stack, Vec3 pos) {
        Containers.dropItemStack(level, pos.x(), pos.y(), pos.z(), stack);
    }


}
