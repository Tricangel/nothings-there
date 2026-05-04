package bee.insanity.entity.client;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class TheWatcherRenderState extends LivingEntityRenderState {
    Identifier skin;

    public Identifier getSkin() {
        return skin;
    }

    public void setSkin(Identifier skin) {
        this.skin = skin;
    }
}
