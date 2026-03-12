package bee.insanity;

import bee.insanity.entity.client.TheWatcherModel;
import bee.insanity.entity.client.TheWatcherRenderer;
import bee.insanity.registry.InsanityMobs;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class NothingsThereClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(TheWatcherModel.THE_WATCHER, TheWatcherModel::getTexturedModelData);
        EntityRendererRegistry.register(InsanityMobs.THE_WATCHER, TheWatcherRenderer::new);


    }
}
