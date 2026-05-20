package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.entity.client.TheWatcherModel;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModEntityModelLayers {
    public static final ModelLayerLocation THE_WATCHER = createMain("the_watcher");

    private static ModelLayerLocation createMain(String name) {
        return new ModelLayerLocation(NothingsThere.id(name), "main");
    }

    public static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(ModEntityModelLayers.THE_WATCHER, TheWatcherModel::createMesh);
    }
}
