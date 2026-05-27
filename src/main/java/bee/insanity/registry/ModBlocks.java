package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.block.AngryAirBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

public class ModBlocks {

public static final Block ANGRY_AIR = register("block", AngryAirBlock::new, BlockBehaviour.Properties.of().noCollision(), true );

    public static final Block DEMONITE_PANEL = register("panel", AngryAirBlock::new, BlockBehaviour.Properties.of().noCollision(), true );



    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFunction, BlockBehaviour.Properties properties, boolean itemRequired) {

        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, NothingsThere.id(name));
        Block block = blockFunction.apply(properties.setId(blockKey));

        if (itemRequired) {
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, NothingsThere.id(name));
            BlockItem item;
            item = new BlockItem(block, new Item.Properties().setId(itemKey));
            Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void init() {}

}
