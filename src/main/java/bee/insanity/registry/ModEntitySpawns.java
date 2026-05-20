package bee.insanity.registry;

import bee.insanity.entity.TheWatcher;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.AMBIENT,
                ModEntityTypes.THE_WATCHER, 100, 1, 1);

        SpawnPlacements.register(ModEntityTypes.THE_WATCHER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG, TheWatcher::checkMobSpawnRules);




    }

}
