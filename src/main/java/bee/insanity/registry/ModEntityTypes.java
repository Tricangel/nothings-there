package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.entity.ParticleBullet;
import bee.insanity.entity.TheWatcher;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {
    public static final EntityType<TheWatcher> THE_WATCHER = register(
            "the_watcher",
            EntityType.Builder.<TheWatcher>of(TheWatcher::new, MobCategory.AMBIENT)
                    .sized(0.75f, 1.75f)
    );

    public static final EntityType<ParticleBullet> PARTICLE_BULLET = register(
            "particle_bullet",
            EntityType.Builder.<ParticleBullet>of(ParticleBullet::new, MobCategory.AMBIENT)
                    .sized(0.25f, 0.25f)
    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, NothingsThere.id(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void init() {

    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(THE_WATCHER, TheWatcher.createMobAttributes());
    }
}
