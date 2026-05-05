package bee.insanity.mixin.client;

import bee.insanity.entity.client.FourthDimRenderState;
import bee.insanity.registry.ModEntityComponents;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(at = @At("HEAD"), method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", cancellable = true)
    private void init(LivingEntity entity, LivingEntityRenderState state, float partialTicks, CallbackInfo ci) {
        if (state instanceof AvatarRenderState) {
            FourthDimRenderState fourthDimRenderState = new FourthDimRenderState();
            fourthDimRenderState.inFourthDim = ModEntityComponents.IN_FOURTH_DIM.get(entity).getBool();
            state.setData(FourthDimRenderState.KEY, fourthDimRenderState);
        }

    }

    @Inject(at = @At("HEAD"), method = "submit(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V", cancellable = true)
    private void wawa(LivingEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera, CallbackInfo ci) {
        if (state instanceof AvatarRenderState) {
            if (state.getData(FourthDimRenderState.KEY) != null && state.getData(FourthDimRenderState.KEY).inFourthDim) {
                ci.cancel();
            }
        }

    }

}
