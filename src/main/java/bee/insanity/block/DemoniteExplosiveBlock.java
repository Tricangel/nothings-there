package bee.insanity.block;

import bee.insanity.registry.ModBlockProperties;
import bee.insanity.registry.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DemoniteExplosiveBlock extends Block {
    private static final IntegerProperty EXPLOSIVE_RESISTANCE = ModBlockProperties.EXPLOSIVE_RESISTANCE;
    private static final IntegerProperty SHARD_AMOUNT = ModBlockProperties.SHARD_AMOUNT;
    public DemoniteExplosiveBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EXPLOSIVE_RESISTANCE);
        builder.add(SHARD_AMOUNT);
    }





}
