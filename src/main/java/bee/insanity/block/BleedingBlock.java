package bee.insanity.block;

import bee.insanity.registry.InsanityBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jspecify.annotations.Nullable;

public class BleedingBlock extends Block {
    public static final IntProperty STAGE = IntProperty.of("stage", 0, 5);
    public BleedingBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(STAGE);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(STAGE, 5);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return state.get(STAGE) != 5;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        
        if (world.getBlockState(pos.down()).isOf(Blocks.POINTED_DRIPSTONE)) {

            world.setBlockState(pos, state.with(STAGE, state.get(STAGE) + 1));

            BlockState stateBelow = world.getBlockState(pos.down(3));
            if (stateBelow.isOf(Blocks.BUDDING_AMETHYST)) {
                world.setBlockState(pos.down(3), InsanityBlocks.BLEED_AMETHYST.getDefaultState());
            }
            if (stateBelow.isOf(InsanityBlocks.BLEED_AMETHYST)) {
                switch (state.get(STAGE)) {
                    case 2:
                        if (stateBelow.get(BleedAmethystBlock.STAGE) < 1)
                            world.setBlockState(pos.down(3), stateBelow.with(BleedAmethystBlock.STAGE, 1));
                    case 3:
                        if (stateBelow.get(BleedAmethystBlock.STAGE) < 2)
                            world.setBlockState(pos.down(3), stateBelow.with(BleedAmethystBlock.STAGE, 2));
                    case 4:
                        if (stateBelow.get(BleedAmethystBlock.STAGE) < 3)
                            world.setBlockState(pos.down(3), stateBelow.with(BleedAmethystBlock.STAGE, 3));
                }
            }
        }




    }
}
