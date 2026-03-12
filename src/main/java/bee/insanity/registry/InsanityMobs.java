package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.entity.TheWatcher;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class InsanityMobs {
    public static final RegistryKey<EntityType<?>> THE_WATCHER_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(NothingsThere.MOD_ID, "the_watcher"));


    public static final EntityType<TheWatcher> THE_WATCHER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(NothingsThere.MOD_ID, "the_watcher"),
            EntityType.Builder.create(TheWatcher::new, SpawnGroup.MONSTER)
                    .dimensions(0.6f, 2).build(THE_WATCHER_KEY));



    public static void init() {}


}
