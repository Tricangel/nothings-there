package bee.insanity.entity.client;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class TheWatcherRenderState extends LivingEntityRenderState {
    Identifier skin;
    int headY;

    public Identifier getSkin() {
        return skin;
    }

    public int getHeadY() {
        return headY;
    }

    public void setSkin(Identifier skin) {
        this.skin = skin;
    }

    public void setHeadY(int headY) {
        this.headY = headY;
    }
}
