package bee.insanity.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;

public class FourthDimRenderState {
    public static final RenderStateDataKey<FourthDimRenderState> KEY = RenderStateDataKey.create(() -> "inFourthDim");
    public boolean inFourthDim;
}
