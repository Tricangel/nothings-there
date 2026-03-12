package bee.insanity.entity;

import bee.insanity.registry.InsanityMobs;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class InsanityEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER, InsanityMobs.THE_WATCHER, 10, 1, 1);

        SpawnRestriction.register(InsanityMobs.THE_WATCHER, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, TheWatcher::canMobSpawn);

    }
}
