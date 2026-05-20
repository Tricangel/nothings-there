package bee.insanity.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.component.ResolvableProfile;

public class TheWatcherRenderstate extends LivingEntityRenderState {
    public ResolvableProfile profile = ResolvableProfile.createUnresolved("steve");
}
