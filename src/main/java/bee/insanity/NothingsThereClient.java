package bee.insanity;

import bee.insanity.entity.client.TheWatcherModel;
import bee.insanity.entity.client.TheWatcherRenderer;
import bee.insanity.registry.InsanityBlocks;
import bee.insanity.registry.InsanityMobs;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.BlockRenderLayer;

public class NothingsThereClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(TheWatcherModel.THE_WATCHER, TheWatcherModel::getTexturedModelData);
        EntityRendererRegistry.register(InsanityMobs.THE_WATCHER, TheWatcherRenderer::new);

        BlockRenderLayerMap.putBlock(InsanityBlocks.DEMONITE_PLATING, BlockRenderLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(InsanityBlocks.BLEED_AMETHYST_CLUSTER, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(InsanityBlocks.SMALL_BLEED_AMETHYST_BUD, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(InsanityBlocks.MEDIUM_BLEED_AMETHYST_BUD, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(InsanityBlocks.LARGE_BLEED_AMETHYST_BUD, BlockRenderLayer.CUTOUT);


    }
}
