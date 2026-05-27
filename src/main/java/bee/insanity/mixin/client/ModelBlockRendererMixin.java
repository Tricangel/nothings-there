package bee.insanity.mixin.client;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModelBlockRenderer.class)
public class ModelBlockRendererMixin {

    @Shadow
    @Final
    private static Direction[] DIRECTIONS;

    @Inject(at = @At("HEAD"), method = "shouldRenderFace", cancellable = true)
    private void init(BlockAndTintGetter level, BlockState state, Direction direction, BlockPos neighborPos, CallbackInfoReturnable<Boolean> cir) {
        boolean panelNearby = false;
        for (Direction direction1 : DIRECTIONS) {
            neighborPos = neighborPos.relative(direction1);
            if (level.getBlockState(neighborPos).is(Blocks.GLASS)) {
                panelNearby = true;
            }
        }

        cir.setReturnValue(true);


    }
}