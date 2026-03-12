package bee.insanity.entity;


import bee.insanity.registry.InsanityMobs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.List;

public class TheWatcher extends MobEntity {
    private static final TrackedData<Integer> ANGERTIME = DataTracker.registerData(TheWatcher.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> HEADY = DataTracker.registerData(TheWatcher.class, TrackedDataHandlerRegistry.INTEGER);

    public TheWatcher(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean canSpawn(WorldView world) {
        return world.isSkyVisibleAllowingSea(this.getBlockPos()) && this.getEntityWorld().getClosestPlayer(this, 75) == null;
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        boolean bl = true;
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, this.getBoundingBox().expand(100), livingEntity -> livingEntity != this);
        for (LivingEntity livingEntity : entities) {
            if (livingEntity.getType() == InsanityMobs.THE_WATCHER) {
                bl = false;
                break;
            }
        }

        return world.isSkyVisibleAllowingSea(this.getBlockPos()) && this.getEntityWorld().getClosestPlayer(this, 75) == null && bl;
    }

    @Override
    public void tick() {
        super.tick();
        World world = this.getEntityWorld();

        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, this.getBoundingBox().expand(100), livingEntity -> livingEntity != this);
        entities.forEach(livingEntity -> {
                    if (livingEntity.getType() == InsanityMobs.THE_WATCHER) {
                        this.remove(RemovalReason.DISCARDED);
                    }
                });



        PlayerEntity player = world.getClosestPlayer(this.getX(), this.getY(), this.getZ(), 150, true);

        if (player != null) {
            this.getEntityWorld().getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox());
            boolean playerLookingAtMe = false;
            List<? extends PlayerEntity> players = world.getPlayers();
            for (PlayerEntity playerEntity : players) {
                if (this.isEntityLookingAtMe(playerEntity, .4, true, false, this.getEyeY())) {
                    playerLookingAtMe = true;
                    break;
                }

            }

            if (player.distanceTo(this) <= 30 || playerLookingAtMe) {

                setAngertime(getAngerTime() + 1);

                if (getAngerTime() >= 80) {
                    for (int i = 0; i < 5; i++) {
                        if (!world.isClient()) {
                            ((ServerWorld) world).spawnParticles(ParticleTypes.SMOKE, true, true, this.getX(), this.getY() + 1, this.getZ(),
                                    10, .3, .5, .3, 0);
                        }
                        if (random.nextBetween(0, 4) == 0) {
                            ItemScatterer.spawn(world, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), Items.ACACIA_BOAT.getDefaultStack());
                        }
                    }
                    world.playSound(this, this.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.HOSTILE, 1, 1);
                    this.remove(RemovalReason.KILLED);
                }
            }
        } //else this.remove(RemovalReason.DISCARDED);

    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {

        if (random.nextBetween(0, 4) == 0) {
            ItemScatterer.spawn(world, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), Items.ACACIA_BOAT.getDefaultStack());
        }

        world.spawnParticles(ParticleTypes.SMOKE, true, true, this.getX(), this.getY() + 1, this.getZ(),
                10, .3, .5, .3, 0);
        this.remove(RemovalReason.KILLED);
        return super.damage(world, source, amount);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(HEADY, 0);
        builder.add(ANGERTIME, 0);
    }

    protected void setHeadY(int headY) {
        this.dataTracker.set(HEADY, headY);
    }

    protected void setAngertime(int angertime) {
        this.dataTracker.set(ANGERTIME, angertime);
    }

    protected int getHeadY() {
        return this.dataTracker.get(HEADY);
    }

    protected int getAngerTime() {
        return this.dataTracker.get(ANGERTIME);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new LookAtEntityGoal(this, PlayerEntity.class, 150, 100));
        super.initGoals();
    }
}
