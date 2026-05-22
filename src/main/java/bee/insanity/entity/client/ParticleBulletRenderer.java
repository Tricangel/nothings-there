package bee.insanity.entity.client;

import bee.insanity.entity.ParticleBullet;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class ParticleBulletRenderer extends EntityRenderer<ParticleBullet, EntityRenderState> {
    public ParticleBulletRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}
