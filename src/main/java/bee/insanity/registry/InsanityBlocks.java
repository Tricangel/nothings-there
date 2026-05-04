package bee.insanity.registry;

import bee.insanity.NothingsThere;
import bee.insanity.block.BleedAmethystBlock;
import bee.insanity.block.BleedingBlock;
import bee.insanity.block.DemoniteBlock;
import bee.insanity.block.DemonitePlatingBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class InsanityBlocks {


    public static final Block DEMONITE_BLOCK = registerBlock("demonite_block", DemoniteBlock::new);
    public static final Block FOOLS_REDSTONE_BLOCK = register("fools_redstone_block", BleedingBlock::new, AbstractBlock.Settings.create().ticksRandomly(), true);
    public static final Block BLEEDING_REDSTONE_BLOCK = register("bleeding_redstone_block", BleedingBlock::new, AbstractBlock.Settings.create().ticksRandomly(), true);

    public static final Block FOOLS_BLEED_AMETHYST = register("fools_bleed_amethyst", BleedAmethystBlock::new, AbstractBlock.Settings.create().ticksRandomly(), true);
    public static final Block BLEED_AMETHYST = register("bleed_amethyst", BleedAmethystBlock::new, AbstractBlock.Settings.create().ticksRandomly(), true);

    public static final Block BLEED_AMETHYST_CLUSTER = register("bleed_amethyst_cluster", (settings -> new AmethystClusterBlock(7.0F, 10.0F, settings)), AbstractBlock.Settings.create(), true);
    public static final Block SMALL_BLEED_AMETHYST_BUD = register("small_bleed_amethyst_bud", (settings -> new AmethystClusterBlock(5.0F, 10.0F, settings)), AbstractBlock.Settings.create(), true);
    public static final Block MEDIUM_BLEED_AMETHYST_BUD = register("medium_bleed_amethyst_bud", (settings -> new AmethystClusterBlock(4.0F, 10.0F, settings)), AbstractBlock.Settings.create(), true);
    public static final Block LARGE_BLEED_AMETHYST_BUD = register("large_bleed_amethyst_bud", (settings -> new AmethystClusterBlock(3.0F, 8.0F, settings)), AbstractBlock.Settings.create(), true);

    public static final Block DEMONITE_PLATING = registerBlock("demonite_plating", DemonitePlatingBlock::new);


    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(NothingsThere.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(NothingsThere.MOD_ID, name), toRegister);
    }


    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(NothingsThere.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NothingsThere.MOD_ID, name)))));
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(Registries.BLOCK.getKey(), Identifier.of(NothingsThere.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(NothingsThere.MOD_ID, name));
    }

    public static void init() {}

}
