package bee.insanity.block;

import bee.insanity.registry.InsanityBlocks;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class BleedAmethystBlock extends Block {
    public static final IntProperty STAGE = IntProperty.of("stage", 0, 3);
    public BleedAmethystBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(STAGE);
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0 && state.get(STAGE) == 3) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            if (canGrowIn(blockState)) {
                block = InsanityBlocks.SMALL_BLEED_AMETHYST_BUD;
            } else if (blockState.isOf(InsanityBlocks.SMALL_BLEED_AMETHYST_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = InsanityBlocks.MEDIUM_BLEED_AMETHYST_BUD;
            } else if (blockState.isOf(InsanityBlocks.MEDIUM_BLEED_AMETHYST_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = InsanityBlocks.LARGE_BLEED_AMETHYST_BUD;
            } else if (blockState.isOf(InsanityBlocks.LARGE_BLEED_AMETHYST_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = InsanityBlocks.BLEED_AMETHYST_CLUSTER;
            }

            if (block != null) {
                BlockState blockState2 = (BlockState)((BlockState)block.getDefaultState().with(AmethystClusterBlock.FACING, direction)).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }

        }
    }

    public static boolean canGrowIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }

}
