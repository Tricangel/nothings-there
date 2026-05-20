package bee.insanity.entity.client;

import bee.insanity.entity.TheWatcher;
import bee.insanity.registry.ModEntityModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

public class TheWatcherRenderer extends LivingEntityRenderer<TheWatcher, TheWatcherRenderstate, TheWatcherModel> {
    public TheWatcherRenderer(EntityRendererProvider.Context context) {
        super(context, new TheWatcherModel(context.bakeLayer(ModEntityModelLayers.THE_WATCHER)), .3f);
    }

    @Override
    public void extractRenderState(TheWatcher entity, TheWatcherRenderstate state, float partialTicks) {
        state.profile = entity.profile;
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public Identifier getTextureLocation(TheWatcherRenderstate state) {
        return state.profile != null ? Minecraft.getInstance().playerSkinRenderCache().getOrDefault(state.profile).playerSkin().body().texturePath() : Identifier.fromNamespaceAndPath("null", "null");
    }


    @Override
    protected @Nullable Component getNameTag(TheWatcher entity) {
        if (entity.profile != null && entity.profile.name().isPresent()) return Component.literal(entity.profile.name().get());
        return null;
    }

    @Override
    public TheWatcherRenderstate createRenderState() {
        return new TheWatcherRenderstate();
    }
}
