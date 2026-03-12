package bee.insanity.entity.client;

import bee.insanity.entity.TheWatcher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.util.Identifier;

public class TheWatcherRenderer extends MobEntityRenderer<TheWatcher, TheWatcherRenderState, TheWatcherModel> implements ModelWithHead {
    public TheWatcherRenderer(EntityRendererFactory.Context context) {
        super(context, new TheWatcherModel(context.getPart(TheWatcherModel.THE_WATCHER)), 1);
    }


    @Override
    public TheWatcherRenderState createRenderState() {
        return new TheWatcherRenderState();
    }


    @Override
    public Identifier getTexture(TheWatcherRenderState state) {
        return MinecraftClient.getInstance().getSkinProvider().supplySkinTextures(MinecraftClient.getInstance().getGameProfile(), false).get().body().texturePath();
    }


    @Override
    public ModelPart getHead() {
        return this.getModel().head;
    }
}
