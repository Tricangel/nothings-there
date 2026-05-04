package bee.insanity.mixin;

import bee.insanity.block.BleedingBlock;
import bee.insanity.registry.InsanityBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.block.WireOrientation;
import net.minecraft.world.tick.ScheduledTickView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {

	@Inject(at = @At("HEAD"), method = "neighborUpdate")
	private static void init(BlockState state, World world, BlockPos pos, Block sourceBlock, WireOrientation wireOrientation, boolean notify, CallbackInfo ci) {
		if (world.getBlockState(pos.down()).isOf(Blocks.POINTED_DRIPSTONE)) {
			if (state.isOf(Blocks.REDSTONE_BLOCK)) {
				world.setBlockState(pos, InsanityBlocks.BLEEDING_REDSTONE_BLOCK.getDefaultState().with(BleedingBlock.STAGE, 0));
			}
		}
	}
}