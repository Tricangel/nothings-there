package bee.insanity.registry;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {
    public static final IntegerProperty EXPLOSIVE_RESISTANCE = IntegerProperty.create("explosive_resistance", 0, 20);
    public static final IntegerProperty SHARD_AMOUNT = IntegerProperty.create("shard_amount", 0, 8);
}
